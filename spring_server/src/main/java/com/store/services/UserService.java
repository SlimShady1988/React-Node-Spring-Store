package com.store.services;

import com.store.entity.Basket;
import com.store.entity.User;
import com.store.excaptions.UserAlreadyExistException;
import com.store.excaptions.UserNotFoundException;
import com.store.model.Role;
import com.store.model.Status;
import com.store.model.UserDTO;
import com.store.repository.UserRepository;
import com.store.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Autowired
    public UserService(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public List<UserDTO> getUsers() throws UserNotFoundException {
        List<User> users = userRepository.findAll().stream().toList();
        if (users.isEmpty()) {
            throw new UserNotFoundException("Has not been found any users");
        }
        return users.stream().map(UserDTO::toDTO).toList();
    }

    public String getUserToken(String email) throws UserNotFoundException {
        User foundUser = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User Doesn't exist"));
            if (email.equals(foundUser.getEmail())) {
                return  auth(UserDTO.toDTO(foundUser));
            } else {
                throw new UserNotFoundException("Password Wrong");
            }
    }

    public String registration(User user) throws UserAlreadyExistException {
//        Role roleUser = roleRepository.findByName("USER");
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("The user with this Email already exist");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        user.setStatus(Status.ACTIVE);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setBasket(new Basket());
        User savedUser = userRepository.save(user);

        return auth(UserDTO.toDTO(savedUser));
    }

    public String auth(UserDTO user) {
        return jwtProvider.generateJWT(user.getId(), user.getEmail(), user.getRole().name());
    }

    public String deleteUser(User user) throws UserNotFoundException {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            userRepository.delete(user);
            return  "The user has successfully deleted";
        } else {
            throw new UserNotFoundException("Has not found the User with this Email");
        }
    }
}
