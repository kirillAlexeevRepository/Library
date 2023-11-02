package com.kirillalekseev.spring.security.service;

import com.kirillalekseev.spring.security.dao.util.BookDAO;
import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.User;
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
    @Override
    @Transactional
    public void setBookItemRequest(Integer book_id , User user){
        bookDAO.setBookItemRequest(book_id,user);
    }
    @Override
    @Transactional
    public  List<Integer> getBookIdFromItems(String username){return bookDAO.getBookIdFromItems(username);}

    @Override
    @Transactional
    public void setBookItemReturn(Integer bookId , String Username ){
        bookDAO.setBookItemReturn(bookId ,Username);
    }
}
