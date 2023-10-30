package com.kirillalekseev.spring.security.entity;


import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Integer itemId ;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "magazine_id")
    private Magazine magazine;

    @Column(name ="item_status")
    private String  itemStatus;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "photo_data")
    private byte[] photoData;

    public Item() {
    }

    public Integer getItemId() {
        return itemId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", book=" + book +
                ", magazine=" + magazine +
                ", itemStatus='" + itemStatus + '\'' +
                ", user=" + user +
                '}';
    }
}
