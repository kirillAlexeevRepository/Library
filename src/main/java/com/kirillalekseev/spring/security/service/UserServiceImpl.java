package com.kirillalekseev.spring.security.service;

import com.kirillalekseev.spring.security.dao.util.UserDAO;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.service.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public User getOneUser(String Username) {
        return userDAO.getUser(Username);
    }

    @Override
    @Transactional
    public  List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void putNewPassword(String password, String username) {
        userDAO.putNewPassword(password, username);
    }

    @Override
    @Transactional
    public void putOneUser(User user){
        userDAO.putOneUser(user);
    }

    @Override
    @Transactional
    public void deleteOneUser(String username){
        userDAO.deleteOneUser(username);
    }

    @Override
    @Transactional
    public List<User> getUsersWithItems() {
        return userDAO.getUsersWithItems();
    }
    @Override
    @Transactional
    public List<User> getUsersWithItems(Integer bookid) {
        return userDAO.getUsersWithItems(bookid);
    }

    @Override
    @Transactional
    public List<User> getUsersWithItemsForMagazines(Integer magazineId) {
        return userDAO.getUsersWithItemsForMagazines(magazineId);
    }
}
