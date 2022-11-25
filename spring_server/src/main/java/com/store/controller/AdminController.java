package com.store.controller;

import com.store.entity.User;
import com.store.excaptions.UserNotFoundException;
import com.store.model.UserDTO;
import com.store.security.UserDetailsServiceImpl;
import com.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    UserDetailsServiceImpl userDetailsService;

        @GetMapping(value = "/users/{id}")
    public UserDetails getUser(@PathVariable User id) {
        return userDetailsService.loadUserByUsername(id.getEmail());
    }
//
    @DeleteMapping(value = "/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable User id) throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDTO>> getUsers() throws Exception {
        try {
            List<UserDTO> users = userService.getUsers();
            return ResponseEntity.ok().body(users);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
