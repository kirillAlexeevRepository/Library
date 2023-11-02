package com.kirillalekseev.spring.security.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "book_status")
    private String bookStatus;

    @Column(name = "photo_data")
    private byte[] photoData;

    @OneToMany(cascade = CascadeType.ALL ,
            mappedBy =  "book",
            fetch = FetchType.LAZY)
    private List<Item> items;

    public Book() {
    }

    public void addItemstoBook(Item item){
        if (items == null){
            items = new ArrayList<>();
        }
        items.add(item);
        item.setBook(this);
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", amount=" + amount +
                ", bookStatus='" + bookStatus + '\'' +
                '}';
    }
}
