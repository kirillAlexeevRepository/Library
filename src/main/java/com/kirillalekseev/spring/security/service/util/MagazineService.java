package com.kirillalekseev.spring.security.service.util;

import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.entity.User;

import java.util.List;

public interface MagazineService {
    List<Magazine> getAllMagazine();
    void putMagazine(Magazine magazine);

    List<Integer>getMagazineIdFromItems(String username);

    void setMagazineItemRequest(Integer MagazineId, User user);

    void setMagazineItemReturn(Integer magazineId, String username, String magazineStatus );
}
