package com.kirillalekseev.spring.security.dao.util;

import com.kirillalekseev.spring.security.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBook();

    void putBook(Book book);
}
