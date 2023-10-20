package com.kirillalekseev.spring.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name ="authorities" )
public class Authorities {
    @Id
    @Column(name = "username")
    private String Username;

    @Column(name = "authority")
    private String authority;

    public Authorities() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
