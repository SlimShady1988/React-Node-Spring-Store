package com.store.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "basket_user_fk"))
//    @JsonIgnore
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<BasketDevice> basketDevices;

    public void setUser(User user) {
        this.user = user;
        user.setBasket(this);
    }
}