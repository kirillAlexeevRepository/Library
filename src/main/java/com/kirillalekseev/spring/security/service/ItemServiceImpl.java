package com.kirillalekseev.spring.security.service;

import com.kirillalekseev.spring.security.dao.util.ItemDAO;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.service.util.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl  implements ItemService {

    @Autowired
    ItemDAO itemDAO;

    @Override
    @Transactional
    public List<Item> getUserItems(String username){
        return itemDAO.getUserItems(username);
    }
}
