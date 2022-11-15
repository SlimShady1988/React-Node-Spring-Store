package com.store.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "brand" , cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Device> devices;

    @OneToMany(mappedBy = "brand")
    @ToString.Exclude
    private List<TypeBrand> typeBrands = new ArrayList<>();
}