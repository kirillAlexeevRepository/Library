package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO{

    @Autowired
    SessionFactory sessionFactory;
    @Override
    public User getUser(String username) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class , username);
        return user;
    }

    @Override
    public List<User> getAllUsers(){
        Session session = sessionFactory.getCurrentSession();
        List<User> userList ;
//        Query<User> query = session.createQuery("from User " , User.class);
//        userList  = query.getResultList();
        userList = session.createQuery("from User" , User.class).getResultList();
        return userList;
    }

    @Override
    public void putNewPassword(String password, String username) {
       Session session =  sessionFactory.getCurrentSession();
       Query<User> query = session.createQuery("update User set Password = :password where username = :username");
        query.setParameter("password" , password);
        query.setParameter("username" , username);
        query.executeUpdate();
    }

    public void putOneUser(User user){
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
}
