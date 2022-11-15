package com.store.model;

import com.store.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class UserDTO {
    public static UserDTO toDTO(User user) {
        UserDTO model = new UserDTO();
        model.setEmail(user.getEmail());
        model.setId(user.getId());
        model.setRole(user.getRole());

        return model;
    }

    private Long id;
    private String email;
    private String role;
}
