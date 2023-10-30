package com.kirillalekseev.spring.security.service.util;

import com.kirillalekseev.spring.security.entity.Magazine;

import java.util.List;

public interface MagazineService {
    List<Magazine> getAllMagazine();
    void putMagazine(Magazine magazine);
}
