package com.kirillalekseev.spring.security.service;

import com.kirillalekseev.spring.security.dao.util.MagazineDAO;
import com.kirillalekseev.spring.security.dao.util.UserDAO;
import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.service.util.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MagazineServiceImpl  implements MagazineService {
    @Autowired
    private MagazineDAO magazineDAO;

    @Override
    @Transactional
    public List<Magazine> getAllMagazine(){
        return magazineDAO.getAllMagazine();
    }
    @Override
    @Transactional
    public void putMagazine(Magazine magazine){
        magazineDAO.putMagazine(magazine);
    }
}