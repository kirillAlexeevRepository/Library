package com.kirillalekseev.spring.security.dao.util;

import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.entity.User;

import java.util.List;

public interface UserDAO {

    User getUser(String username);

    List<User> getAllUsers();

    void  putNewPassword(String password , String username);

    void putOneUser(User user);

    void deleteOneUser(String username);

    List<User> getUsersWithItems();
    List<User> getUsersWithItems(Integer bookId);

    List<User> getUsersWithItemsForMagazines(Integer magazineId);
}

