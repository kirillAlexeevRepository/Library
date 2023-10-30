package com.kirillalekseev.spring.security.service.util;

import com.kirillalekseev.spring.security.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBook();
    void putBook(Book book);
}
