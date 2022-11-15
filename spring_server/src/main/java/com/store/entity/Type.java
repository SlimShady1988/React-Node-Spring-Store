package com.store.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "types")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Device> devices = new ArrayList<>();

    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<TypeBrand> typeBrands = new ArrayList<>();

}