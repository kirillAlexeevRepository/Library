package com.kirillalekseev.spring.security.dao.util;

import com.kirillalekseev.spring.security.entity.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getUserItems(String username);
}
