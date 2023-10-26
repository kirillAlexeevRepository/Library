package com.kirillalekseev.spring.security.entity;

import javax.persistence.*;

@Entity
@Table(name = "magazine")
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="magazine_id")
    private Integer magazineId;

    @Column(name = "magazine_name")
    private String magazineName;

    @Column(name = "author")
    private String author;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    private String status;

    @Column(name = "photo_data")
    private byte[] photoData;

    public Magazine() {
    }

    public Integer getMagazineId() {
        return magazineId;
    }

    public String getMagazineName() {
        return magazineName;
    }

    public void setMagazineName(String magazineName) {
        this.magazineName = magazineName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "magazineName='" + magazineName + '\'' +
                ", author='" + author + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
