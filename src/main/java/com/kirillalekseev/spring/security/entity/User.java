package com.kirillalekseev.spring.security.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


    @Entity
    @Table(name = "users")
    public class User {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name= "password")
    private String Password;

    @Column(name = "enabled")
    private byte enabled;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "photo_data")
    private byte[] photoData;

    @OneToOne(mappedBy = "authUser",cascade = CascadeType.ALL)
    private Authorities userAuthorities;

    @OneToMany(cascade = CascadeType.ALL ,
            mappedBy = "user",
            fetch = FetchType.EAGER)
    private List<Item> itemlist;

    public User() {

    }
    public void addItemtoUser(Item item){
        if(itemlist ==null){
            itemlist = new ArrayList<>();
        }
        itemlist.add(item);
        item.setUser(this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

        public Authorities getUserAuthorities() {
            return userAuthorities;
        }

        public void setUserAuthorities(Authorities userAuthorities) {
            this.userAuthorities = userAuthorities;
        }

        @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
