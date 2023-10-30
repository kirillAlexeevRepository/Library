package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.dao.util.BookDAO;
import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BookDaoImpl implements BookDAO {
    @Autowired
    SessionFactory sessionFactory;

    public List<Book> getAllBook(){
        Session session = sessionFactory.getCurrentSession();
        List<Book>  listBook;
        listBook = session.createQuery("from Book" , Book.class).getResultList();
        return listBook;
    }
    public void putBook(Book book){
        Session sesion = sessionFactory.getCurrentSession();
        for(int i = 0; i < book.getAmount(); i++){
            Item item = new Item();
            item.setItemStatus("IN_LIBRARY");
            book.addItemstoBook(item);
        }
        sesion.save(book);
    }

}
