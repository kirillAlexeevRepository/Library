package com.kirillalekseev.spring.security.service;


import com.kirillalekseev.spring.security.entity.User;

import java.util.List;

public interface UserService {
    public User getOneUser(String Username);

    public List<User> getAllUsers();

    public void putNewPassword(String password, String username);

    void putOneUser(User user);

}
