package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.dao.util.MagazineDAO;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.exception_handling.NotAvailableStatusException;
import com.kirillalekseev.spring.security.technicalClasses.ItemStatus;
import com.kirillalekseev.spring.security.technicalClasses.abstractStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class MagazineDaoImpl implements MagazineDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Magazine> getAllMagazine(){
        Session session = sessionFactory.getCurrentSession();
        List<Magazine>  rawlistMagazine;
        rawlistMagazine = session.createQuery("from Magazine" , Magazine.class).getResultList();
        List<Magazine>  listMagazine = new ArrayList<>();
        int magazineId;
        for(Magazine magazine :rawlistMagazine){
           magazineId = magazine.getMagazineId();
           String sql ="SELECT COUNT(*) FROM item where magazine_id =:magazineId and username is null ";
          Object count = session.createNativeQuery(sql)
                  .setParameter("magazineId" ,magazineId)
                  .getSingleResult();
          if(((BigInteger)count).intValue() != 0){
              magazine.setStatus(abstractStatus.AVAILABLE.getDisplayName());
          }else {
              magazine.setStatus(abstractStatus.NOT_AVAILABLE.getDisplayName());
          }
          magazine.setAmount(((BigInteger) count).intValue());
          listMagazine.add(magazine);
        }
        return listMagazine;
    }
    public void putMagazine(Magazine magazine){
        Session sesion = sessionFactory.getCurrentSession();
        for(int i = 0; i < magazine.getAmount(); i++){
            Item item = new Item();
            item.setItemStatus(ItemStatus.IN_LIBRARY.getDisplayName());
            magazine.addItemtoMagazine(item);
        }
        sesion.save(magazine);
    }
    @Override
    public List<Integer> getMagazineIdFromItems(String Username) {
        List<Integer> magazineIdFromItems;
        Session session = sessionFactory.getCurrentSession();
        magazineIdFromItems = session.createQuery("SELECT magazine.magazineId FROM Item WHERE user.username = :username ", Integer.class)
                .setParameter("username",Username)
                .getResultList();
        return magazineIdFromItems;
    }
    @Override
    public void setMagazineItemRequest(Integer magazineId, User user) {
        Session session =sessionFactory.getCurrentSession();
        List<Item> itemList;
        String Status = ItemStatus.IN_LIBRARY.getDisplayName();
        itemList = session.createQuery("from Item where magazine.magazineId = :magazine_id " +
                        "and itemStatus = :Status", Item.class)
                .setParameter("magazine_id",magazineId).setParameter("Status",Status).getResultList();

        Collections.sort(itemList);
        Item firstAvalibleItem = itemList.get(0);

         firstAvalibleItem.setUser(user);
         firstAvalibleItem.setItemStatus(ItemStatus.REQUESTED_TO_TAKE.getDisplayName());
         user.addItemtoUser(firstAvalibleItem);

         session.update(firstAvalibleItem);
    }
    @Override
    public void setMagazineItemReturn(Integer magazineId, String username ,String magazineStatus) {
        Session session = sessionFactory.getCurrentSession();
        String sql;
        String Status;
        if(!magazineStatus.equals(ItemStatus.REQUESTED_TO_TAKE.getDisplayName())){
            Status = ItemStatus.REQUESTED_TO_RETURN.getDisplayName();
            sql= "UPDATE item SET item_status = :Status WHERE magazine_id = :magazineId" +
                    " and username = :username" ;
        }else {
            Status = ItemStatus.IN_LIBRARY.getDisplayName();
            sql= "UPDATE item SET item_status = :Status , username = null  WHERE magazine_id = :magazineId" +
                    " and username = :username" ;
        }
        session.createNativeQuery(sql)
                .setParameter("Status",Status)
                .setParameter("magazineId",magazineId)
                .setParameter("username",username)
                .executeUpdate();
    }
    @Override
    public void addMoreMagazine(Integer magazineId) {
        Session session = sessionFactory.getCurrentSession();
        Magazine magazine = session.get(Magazine.class,magazineId);
        Item item = new Item();
        item.setItemStatus(ItemStatus.IN_LIBRARY.getDisplayName());

        magazine.addItemtoMagazine(item);
        session.saveOrUpdate(magazine);

    }
    @Override
    public void delMagazine(Integer magazineId) {
    Session session = sessionFactory.getCurrentSession();
    Magazine magazine = session.get(Magazine.class ,magazineId);

    if(!(magazine.getStatus().equals(abstractStatus.AVAILABLE.getDisplayName()))){
        List<Item> itemList;
        Query<Item> query = session.createQuery("from Item where magazine.magazineId=:magazineId " +
                "and user.username IS NOT NULL");
        itemList = query.setParameter("magazineId",magazineId).getResultList();

        if(itemList.isEmpty()){
            session.delete(magazine);
        }else{
           throw new NotAvailableStatusException("can't delete this magazine some people have it");
        }
    }else{
        String sql = "DELETE FROM item where item_id IN " +
                "(SELECT item_id FROM item WHERE magazine_id = :magazineId AND username IS NULL ORDER BY item_id LIMIT 1);";
        session.createNativeQuery(sql)
                .setParameter("magazineId",magazineId)
                .executeUpdate();

    }

    }
}
