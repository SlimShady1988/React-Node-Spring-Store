package com.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "basket_devices")
public class BasketDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "basket_id", foreignKey = @ForeignKey(name = "basket_device_basket_fk"))
    @JsonIgnore
    @ToString.Exclude
    private Basket basket;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "device_id", foreignKey = @ForeignKey(name = "basket_device_device_fk"))
    @JsonIgnore
    @ToString.Exclude
    private Device device;

    public void setDevice(Device device) {
        this.device = device;
        device.setBasketDevice(this);
    }
}