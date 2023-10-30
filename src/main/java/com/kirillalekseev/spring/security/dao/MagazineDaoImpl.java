package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.dao.util.MagazineDAO;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.Magazine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MagazineDaoImpl implements MagazineDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Magazine> getAllMagazine(){
        Session session = sessionFactory.getCurrentSession();
        List<Magazine>  listMagazine;
        listMagazine = session.createQuery("from Magazine" , Magazine.class).getResultList();
        return listMagazine;
    }
    public void putMagazine(Magazine magazine){
        Session sesion = sessionFactory.getCurrentSession();
        for(int i = 0; i < magazine.getAmount(); i++){
            Item item = new Item();
            item.setItemStatus("IN_LIBRARY");
            magazine.addItemtoMagazine(item);
        }
        sesion.save(magazine);
    }

}
