package com.kirillalekseev.spring.security.service.util;


import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.entity.User;


import java.util.List;

public interface UserService {
    public User getOneUser(String Username);

    public List<User> getAllUsers();

    public void putNewPassword(String password, String username);

    void putOneUser(User user);



}
