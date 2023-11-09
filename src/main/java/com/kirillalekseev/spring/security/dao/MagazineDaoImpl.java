package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.dao.util.MagazineDAO;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
              magazine.setStatus("available");
          }else {
              magazine.setStatus("not available");
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
            item.setItemStatus("In Library");
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
        itemList = session.createQuery("from Item where magazine.magazineId = :magazine_id " +
                        "and itemStatus = 'In Library'", Item.class)
                .setParameter("magazine_id",magazineId).getResultList();

        Collections.sort(itemList);
        Item firstAvalibleItem = itemList.get(0);

         firstAvalibleItem.setUser(user);
         firstAvalibleItem.setItemStatus("requested to take");
         user.addItemtoUser(firstAvalibleItem);

         session.update(firstAvalibleItem);
    }
    @Override
    public void setMagazineItemReturn(Integer magazineId, String username ,String magazineStatus) {
        Session session = sessionFactory.getCurrentSession();
        String sql;
        if(!magazineStatus.equals("requested to take")){
            sql= "UPDATE item SET item_status = 'requested to return' WHERE magazine_id = :magazineId" +
                    " and username = :username" ;
        }else {
            sql= "UPDATE item SET item_status = 'In Library' , username = null  WHERE magazine_id = :magazineId" +
                    " and username = :username" ;
        }
        session.createNativeQuery(sql)
                .setParameter("magazineId",magazineId)
                .setParameter("username",username)
                .executeUpdate();
    }
}
