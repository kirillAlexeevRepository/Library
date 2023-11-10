package com.kirillalekseev.spring.security.dao.util;

import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.entity.User;

import java.util.List;

public interface MagazineDAO {
    List<Magazine> getAllMagazine();
    void putMagazine(Magazine magazine);

    List<Integer>getMagazineIdFromItems(String Username);

    void setMagazineItemRequest(Integer magazineId, User user);

    void setMagazineItemReturn(Integer magazineId, String username ,String magazineStatus);

    void addMoreMagazine(Integer magazineId);

    void delMagazine(Integer magazineId);
}
