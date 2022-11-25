package com.store.model;

import com.store.entity.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@Component
public class DeviceDTO {
    public static DeviceDTO toDTO(Device device) {
        DeviceDTO model = new DeviceDTO();
        model.setId(device.getId());
        model.setTypeId(device.getType().getName());
        model.setBrandId(device.getBrand().getName());
        model.setName(device.getName());
        model.setImg(device.getImg());
        model.setRating(5);
        model.setPrice(device.getPrice());
        model.setInfo(device.getInfo());

        return model;
    }

    private Long id;
    private String typeId;
    private String name;
    private List<DeviceInfo> info;
    private String brandId;
    private String img;
    private Integer price;
    private Integer rating;
}
