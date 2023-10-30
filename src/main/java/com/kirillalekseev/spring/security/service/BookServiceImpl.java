package com.kirillalekseev.spring.security.service;

import com.kirillalekseev.spring.security.dao.util.BookDAO;
import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.service.util.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public List<Book> getAllBook(){
        return bookDAO.getAllBook();
    }
    @Override
    @Transactional
    public void putBook(Book book){
        bookDAO.putBook(book);
    }
}
