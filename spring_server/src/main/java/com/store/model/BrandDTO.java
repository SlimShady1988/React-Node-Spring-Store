package com.store.model;

import com.store.entity.Brand;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class BrandDTO {
    public static BrandDTO toDTO(Brand brand) {
        BrandDTO model = new BrandDTO();
        model.setName(brand.getName());
        model.setId(brand.getId());

        return model;
    }

    private Long id;
    private String name;
}
