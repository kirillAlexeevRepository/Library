package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.dao.util.ItemDAO;
import com.kirillalekseev.spring.security.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Item> getUserItems(String username) {
        List<Item> items;
        Session session  = sessionFactory.getCurrentSession();
        items = session.createQuery("from Item where user.username = : Username" ,Item.class)
                .setParameter("Username", username).getResultList();
        return items;
    }

    public List <Item> getReturnsRequestsItems(){
        Session session = sessionFactory.getCurrentSession();
        List<Item> itemsList = session.createQuery("from Item where itemStatus = 'requested to return' " +
                "or itemStatus = 'requested to take' " , Item.class).getResultList();
        return itemsList;
    }

    @Override
    public void acceptRequest(Integer ItemId, String ItemStatus) {
        Session session = sessionFactory.getCurrentSession();
        String sql;
        if(!ItemStatus.equals("requested to take")){
            sql = "UPDATE item set item_status ='In Library' , username = null where item_id = :Item_Id";
        }else {
            sql = "UPDATE item set item_status ='already taken' where item_id = :Item_Id";
        }
        session.createNativeQuery(sql)
                .setParameter("Item_Id" ,ItemId )
                .executeUpdate();
    }

    @Override
    public void declineRequest(Integer itemId, String itemStatus) {
        Session sesion = sessionFactory.getCurrentSession();
        String sql;
        if(!itemStatus.equals("requested to take")){
            sql = "UPDATE item set item_status = 'already taken' where item_id = :Item_id";
        }else {
            sql ="UPDATE item set item_status ='In Library' , username = null  where item_id = :Item_id ";
        }
        sesion.createNativeQuery(sql)
                .setParameter("Item_id",itemId)
                .executeUpdate();
    }
}
