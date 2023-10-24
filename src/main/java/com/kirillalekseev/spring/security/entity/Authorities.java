package com.kirillalekseev.spring.security.entity;

import javax.persistence.*;

@Entity
@Table(name ="authorities" )
public class Authorities {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User authUser;

    @Column(name = "authority")
    private String authority;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id")
    private Integer id;

    public Authorities() {
    }

    public User getAuthUser() {
        return authUser;
    }

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
