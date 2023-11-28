package com.kirillalekseev.spring.security.service.util;



import com.kirillalekseev.spring.security.entity.User;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public interface UserService {
    public User getOneUser(String Username);

    public List<User> getAllUsers();

    public void putNewPassword(String password, String username);

    void putOneUser(User user);

    void deleteOneUser(String username);

    List<User> getUsersWithItems();

    List<User> getUsersWithItems(Integer bookId);

    List<User> getUsersWithItemsForMagazines(Integer magazineId);
}
