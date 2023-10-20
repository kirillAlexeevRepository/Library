package com.kirillalekseev.spring.security.entity;

import com.kirillalekseev.spring.security.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Entity
    @Table(name = "users")
    public class User  //implements UserDetails {
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username")
    private String username;

    @Column(name= "password")
    private String Password;

    @Column(name = "enabled")
    private byte enabled;

    @Column(name = "salary")
    private int salary;


    public User() {
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }



    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", Password='" + Password + '\'' +
                ", enabled=" + enabled +
                ", salary=" + salary +
                '}';
    }
}
