package com.kirillalekseev.spring.security.service.util;

import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.User;

import java.util.List;

public interface BookService {

    List<Book> getAllBook();
    void putBook(Book book);

    void setBookItemRequest(Integer book_id , User user);
    List<Integer> getBookIdFromItems(String username);

    void setBookItemReturn(Integer BookId , String username );
}
