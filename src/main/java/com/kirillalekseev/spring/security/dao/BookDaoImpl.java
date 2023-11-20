package com.kirillalekseev.spring.security.dao;

import com.kirillalekseev.spring.security.dao.util.BookDAO;
import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.exception_handling.NotAvailableStatusException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.*;

@Repository
public class BookDaoImpl implements BookDAO {
    @Autowired
    SessionFactory sessionFactory;

    public List<Book> getAllBook(){
        Session session = sessionFactory.getCurrentSession();
        List<Book>  rawlistBook ;
        rawlistBook = session.createQuery("from Book" , Book.class).getResultList();

        List<Book> listBook = new ArrayList<>();
        Integer bookId;
        for (Book book: rawlistBook) {
            bookId = book.getBookId();
            String sql = "SELECT COUNT(*) FROM item WHERE book_id =:bookId and item_status ='In Library' and username is null;";
            Object count  =  session.createNativeQuery(sql).setParameter("bookId" ,bookId).getSingleResult();
            if(((BigInteger)count).intValue() != 0){
                book.setBookStatus("available");
            }else {
                book.setBookStatus("not available");
            }
            book.setAmount(((BigInteger)count).intValue());
            listBook.add(book) ;
        }
        return listBook;
    }
    
    public void putBook(Book book){
        Session sesion = sessionFactory.getCurrentSession();
        for(int i = 0; i < book.getAmount(); i++){
            Item item = new Item();
            item.setItemStatus("In Library");
            book.addItemstoBook(item);
        }
        sesion.save(book);
    }
    public List<Integer> getBookIdFromItems(String username){
        List<Integer> BookIdListfromItems ;
        Session session = sessionFactory.getCurrentSession();
        BookIdListfromItems =  session.createQuery("select book.bookId from Item where user.username = : Username", Integer.class)
                .setParameter("Username", username).getResultList();
        System.out.println(BookIdListfromItems);
        return BookIdListfromItems;
    }

    public void setBookItemRequest(Integer book_id , User user){
        Session session = sessionFactory.getCurrentSession();
        List<Item> itemlist;
        itemlist  = session.createQuery("FROM Item WHERE book.bookId = :bookId AND itemStatus = 'In Library'", Item.class)
                .setParameter("bookId", book_id)
                .getResultList();
        if(!(itemlist.isEmpty())){
        Collections.sort(itemlist);
        Item firstAvalibleItem = itemlist.get(0);
        firstAvalibleItem.setUser(user);
        firstAvalibleItem.setItemStatus("requested to take");
        user.addItemtoUser(firstAvalibleItem);
        session.update(firstAvalibleItem);
        }else{
            throw new NotAvailableStatusException("This book has Not Available Status " +
                    "Try when status will be available");
        }
    }

    @Override
    public void setBookItemReturn(Integer bookId, String username ,String bookStatus ) {
        Session session = sessionFactory.getCurrentSession();
        String sql;
        if(!bookStatus.equals("requested to take")){
            sql = "update item SET item_status = 'requested to return' where book_id = :book_id and username = :Username";
        }else{
            sql = "update item SET item_status = 'In Library' ,username = null where book_id = :book_id and username = :Username";
        }
        session.createNativeQuery(sql)
                .setParameter("book_id" ,bookId )
                .setParameter("Username" ,username)
                .executeUpdate();
    }
    @Override
    public void updateAmount(Integer bookId) {
        Session session = sessionFactory.getCurrentSession();
       Book book = session.get(Book.class ,bookId);
       Item item = new Item();
       item.setItemStatus("In Library");
       book.addItemstoBook(item);
       session.saveOrUpdate(book);
    }

    @Override
    public void delBook(Integer bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book book =session.get(Book.class ,bookId);

        if(!(book.getBookStatus().equals("available"))) {
            List <Item> itemlist ;
            Query<Item> query = session.createQuery("from Item where book.bookId =:bookId and user.username is not null ");
            itemlist= query.setParameter("bookId",bookId).getResultList();
            if(itemlist.isEmpty()){
                session.delete(book);
            }else{
                //выбрасываем сообщение что удалить не возможно книга на руках у людей
            }
        }else{
            String sql ="DELETE FROM item " +
                    "WHERE item_id " +
                    "IN ( SELECT item_id FROM item" +
                    " WHERE book_id = :bookId " +
                    "AND username IS NULL ORDER BY item_id LIMIT 1);";
            session.createNativeQuery(sql).setParameter("bookId", bookId)
                    .executeUpdate();
        }
    }
}
