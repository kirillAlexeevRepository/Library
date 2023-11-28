package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.dao.util.UserDAO;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.technicalClasses.ItemStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

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

    @Override
    public void deleteOneUser(String username) {
       Session session = sessionFactory.getCurrentSession();
       session.createQuery("delete from User where username = :username")
               .setParameter("username", username)
               .executeUpdate();
    }
    @Override
    public List<User> getUsersWithItems() {
        List<String> UserNamesWithItems ;
        List<User> UsersWithItems = new ArrayList<>();
        Session session = sessionFactory.getCurrentSession();
        UserNamesWithItems = session.createNativeQuery("SELECT DISTINCT username FROM item WHERE item_status LIKE '%requested%' and username is not null;").getResultList();
        if (!UserNamesWithItems.isEmpty()) {
            for (String Username : UserNamesWithItems) {
                User user = session.get(User.class, Username);
                UsersWithItems.add(user);
            }
        }
        return UsersWithItems;
    }
    @Override
    public List<User> getUsersWithItems(Integer bookId) {
        Session session =sessionFactory.getCurrentSession();
        String Status1 = ItemStatus.ALREADY_TAKEN.getDisplayName();
        String Status2 = ItemStatus.REQUESTED_TO_RETURN.getDisplayName();
        List<User> userlist = session.createQuery("select i.user FROM Item i  where i.book.bookId = :bookId and ( i.itemStatus = :Status1 or i.itemStatus =: Status2)", User.class)
                .setParameter("bookId",bookId)
                .setParameter("Status1",Status1)
                .setParameter("Status2",Status2)
                .getResultList();
        return userlist;
    }
    @Override
    public List<User> getUsersWithItemsForMagazines(Integer magazineId) {
        Session session =sessionFactory.getCurrentSession();
        String Status1 = ItemStatus.ALREADY_TAKEN.getDisplayName();
        String Status2 = ItemStatus.REQUESTED_TO_RETURN.getDisplayName();
        List<User> userlist = session.createQuery("select i.user FROM Item i  where i.magazine.magazineId = :magazineId and ( i.itemStatus =:Status1 or i.itemStatus =:Status2)", User.class)
                .setParameter("magazineId",magazineId)
                .setParameter("Status1",Status1)
                .setParameter("Status2",Status2)
                .getResultList();
        return userlist;
    }
}
