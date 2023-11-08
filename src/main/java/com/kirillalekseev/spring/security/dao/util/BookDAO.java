package com.kirillalekseev.spring.security.dao.util;

import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.User;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBook();

    void putBook(Book book);

    void setBookItemRequest(Integer book_id , User user);

    List<Integer> getBookIdFromItems(String username);

    void setBookItemReturn(Integer bookId ,String username ,String bookStatus);
}
