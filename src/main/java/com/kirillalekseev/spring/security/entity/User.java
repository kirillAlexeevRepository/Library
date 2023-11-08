package com.kirillalekseev.spring.security.entity;

import com.kirillalekseev.spring.security.validations.CheckEmail;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;


    @Entity
    @Table(name = "users")
    public class User {
    @Id
    @Column(name = "username")
    @CheckEmail
    private String username;
    @NotBlank(message = "its required field must not be empty")
    @Column(name= "password")
    private String password;

    @Column(name = "enabled")
    private byte enabled;

    @NotBlank(message = "its required field must not be empty")
    @Pattern(regexp = "^[A-Za-z\\s]{2,45}$" ,message = "must be like Jhon ")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "its required field must not be empty")
    @Pattern(regexp = "^[A-Za-z\\s]{2,45}$" ,message = "must be like Smith ")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "its required field must not be empty")
    @Pattern(regexp = "^0[1-9][0-9][0-9]{3}[0-9]{2}[0-9]{2}$" ,message = "must be like 0671234567")
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
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
