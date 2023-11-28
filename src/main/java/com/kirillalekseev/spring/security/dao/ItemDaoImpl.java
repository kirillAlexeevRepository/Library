package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.dao.util.ItemDAO;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.technicalClasses.ItemStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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

    public List <Item> getReturnsRequestsItems(String Username){
        Session session = sessionFactory.getCurrentSession();
        List<Item> itemsList ;
        String Status1 = ItemStatus.REQUESTED_TO_RETURN.getDisplayName();
        String Status2 = ItemStatus.REQUESTED_TO_TAKE.getDisplayName();
        if(Username != null){
            itemsList = session.createQuery("from Item where user.username =:username and (itemStatus = :Status1 " +
                    " or itemStatus = :Status2)" , Item.class).setParameter("username" ,Username)
                    .setParameter("Status1" ,Status1)
                    .setParameter("Status2",Status2)
                    .getResultList();
        }else {
             itemsList = session.createQuery("from Item where itemStatus = :Status1 " +
                    " or itemStatus =:Status2 " , Item.class)
                     .setParameter("Status1" ,Status1)
                     .setParameter("Status2",Status2)
                     .getResultList();
        }

        return itemsList;
    }

    @Override
    public void acceptRequest(Integer ItemId, String itemStatus) {
        Session session = sessionFactory.getCurrentSession();
        String sql;
        String Status;
        if(!itemStatus.equals(ItemStatus.REQUESTED_TO_TAKE.getDisplayName())){
            Status = ItemStatus.IN_LIBRARY.getDisplayName();
            sql = "UPDATE item set item_status =:Status , username = null where item_id = :Item_Id";
        }else {
            Status = ItemStatus.ALREADY_TAKEN.getDisplayName();
            sql = "UPDATE item set item_status =:Status where item_id = :Item_Id";
        }
        session.createNativeQuery(sql)
                .setParameter("Status",Status)
                .setParameter("Item_Id" ,ItemId )
                .executeUpdate();
    }

    @Override
    public void declineRequest(Integer itemId, String itemStatus) {
        Session sesion = sessionFactory.getCurrentSession();
        String sql;
        String Status;
        if(!itemStatus.equals(ItemStatus.REQUESTED_TO_TAKE.getDisplayName())){
            Status =ItemStatus.ALREADY_TAKEN.getDisplayName();
            sql = "UPDATE item set item_status =:Status where item_id = :Item_id";
        }else {
            Status =ItemStatus.IN_LIBRARY.getDisplayName();
            sql ="UPDATE item set item_status =:Status , username = null  where item_id = :Item_id ";
        }
        sesion.createNativeQuery(sql)
                .setParameter("Status",Status)
                .setParameter("Item_id",itemId)
                .executeUpdate();
    }
}
