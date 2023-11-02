package com.kirillalekseev.spring.security.service.util;

import com.kirillalekseev.spring.security.entity.Item;

import java.util.List;

public interface ItemService {

    List<Item> getUserItems(String username);
}
