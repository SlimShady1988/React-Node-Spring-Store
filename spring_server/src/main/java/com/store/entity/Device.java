package com.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "img", nullable = false)
    private String img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", foreignKey = @ForeignKey(name = "device_type_fk"))
    @JsonIgnore
    @ToString.Exclude
    private Type type;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "device_brand_fk"))
    @JsonIgnore
    @ToString.Exclude
    private Brand brand;

    @OneToMany(mappedBy = "deviceId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<DeviceInfo> info;


    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Rating> ratings;

    @OneToOne(mappedBy = "device", cascade = CascadeType.ALL)
    @ToString.Exclude
    private BasketDevice basketDevice;

    public void setBasketDevice(BasketDevice basketDevice) {
        this.basketDevice = basketDevice;
        basketDevice.setDevice(this);
    }

    public void setInfo(List<DeviceInfo> deviceInfo) {
        deviceInfo.forEach(info -> info.setDeviceId(this));
        this.info = deviceInfo;
    }



}