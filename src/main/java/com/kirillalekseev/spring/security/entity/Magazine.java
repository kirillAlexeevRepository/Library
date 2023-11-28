package com.kirillalekseev.spring.security.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "magazine")
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="magazine_id")
    private Integer magazineId;

    @NotBlank(message = "it's required field")
    @Pattern(regexp = "^[A-Za-zА-Яа-я\\s\\-]{2,20}$",message = "2-20 characters,letters,space,hyphen.")
    @Column(name = "magazine_name")
    private String magazineName;

    @NotBlank(message = "it's required field")
    @Pattern(regexp = "^[A-Za-zА-Яа-я\\s\\-]{2,20}$", message  = "2-20 characters,letters,space,hyphen." )
    @Column(name = "author")
    private String author;

    @NotNull(message = "it's required field")
    @Min(value = -1 ,message = "must be greater then 0")
    @Max(value = 71 , message = "must be less then 21")
    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    private String status;

    @Column(name = "photo_data")
    private byte[] photoData;

    @OneToMany(cascade = CascadeType.ALL
        ,mappedBy = "magazine",
        fetch = FetchType.LAZY)
    private List<Item> itemlist;

    public Magazine() {
    }
    public void addItemtoMagazine(Item item){
        if(itemlist == null){
            itemlist = new ArrayList<>();
        }
        itemlist.add(item);
        item.setMagazine(this);
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
