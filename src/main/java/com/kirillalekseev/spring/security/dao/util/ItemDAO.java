package com.kirillalekseev.spring.security.dao.util;

import com.kirillalekseev.spring.security.entity.Item;

import java.util.List;

public interface ItemDAO {

    List<Item> getUserItems(String username);

    List<Item> getReturnsRequestsItems(String usename);

    void acceptRequest(Integer ItemId ,String ItemStatus);

    void declineRequest(Integer itemId, String itemStatus);
}
