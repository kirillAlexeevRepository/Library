package com.kirillalekseev.spring.security.dao.util;

import com.kirillalekseev.spring.security.entity.Magazine;

import java.util.List;

public interface MagazineDAO {
    List<Magazine> getAllMagazine();
    void putMagazine(Magazine magazine);
}
