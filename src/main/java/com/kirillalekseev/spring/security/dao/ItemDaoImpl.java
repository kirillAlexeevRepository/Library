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
}
