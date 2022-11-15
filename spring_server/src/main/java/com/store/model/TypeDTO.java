package com.store.model;

import com.store.entity.Type;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class TypeDTO {
    public static TypeDTO toDTO(Type type) {
        TypeDTO model = new TypeDTO();
        model.setName(type.getName());
        model.setId(type.getId());
        return model;
    }

    private Long id;
    private String name;
}
