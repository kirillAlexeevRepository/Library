package com.kirillalekseev.spring.security.service;

import com.kirillalekseev.spring.security.dao.UserDAO;
import com.kirillalekseev.spring.security.entity.User;
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
}
