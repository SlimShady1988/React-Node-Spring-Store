package com.store.entity;

import com.store.model.Role;
import com.store.model.Status;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User {
    public User () {
        this.status = Status.ACTIVE;
        this.role = Role.USER;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

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