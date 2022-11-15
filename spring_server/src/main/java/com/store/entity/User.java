package com.store.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private Basket basket;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Rating> ratings;

    public void setBasket(Basket basket) {
        basket.setUser(this);
        this.basket = basket;


    }
}