package com.store.services;

import com.store.entity.User;
import com.store.excaptions.UserAlreadyExistException;
import com.store.excaptions.UserNotFoundException;
import com.store.model.UserDTO;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public List<UserDTO> getUsers() throws UserNotFoundException {
        List<User> users = userRepository.findAll().stream().toList();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Has not been found any users");
        }
        List<UserDTO> model = users.stream().map(UserDTO::toDTO).toList();
        return model;
    }

    public String getUser(User user) throws UserNotFoundException {
        User foundUser = userRepository.findByEmail(user.getEmail());

        if (foundUser != null) {
            if (user.getEmail().equals(foundUser.getEmail())) {
                return  auth(foundUser);
            } else {
                throw new UserNotFoundException("Password Wrong");
            }
        }
        throw new UserNotFoundException("Have not found the User with this Email");

    }

    public String registration(User user) throws UserAlreadyExistException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("User with this Email already exist");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);

        return  auth(user);
    }

    public String auth(User user) {
        return jwtService.generateJWT(user.getId(), user.getEmail(), user.getRole());
    }
}
