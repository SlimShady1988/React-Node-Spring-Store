package com.store.controller;

import com.store.entity.User;
import com.store.excaptions.UserAlreadyExistException;
import com.store.excaptions.UserNotFoundException;
import com.store.model.UserDTO;
import com.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
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

    @PostMapping(value = "/registration" )
    public ResponseEntity<Map<String, String>> registration(@RequestBody User user) {
        Map<String, String> resp = new HashMap<>();
        try {
            String token =userService.registration(user);

            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/api/user/registration")
                    .buildAndExpand()
                    .toUri();
            resp.put("message", "User successfully created");
            resp.put("token", token);
            return created(location).body(resp);

        } catch (UserAlreadyExistException e) {
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        } catch (Exception e) {
            resp.put("message", "error");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> resp = new HashMap<>();
        try {
            String token = userService.getUser(user);
            resp.put("token", token);

            var location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/api/user/login").replacePath("/admin")
                    .buildAndExpand()
                    .toUri();
            return created(location).body(resp);

        } catch (UserNotFoundException e) {
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        } catch (Exception e) {
            resp.put("message", "error");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @GetMapping("/auth")
    public ResponseEntity<Map<String, String>> auth(@RequestParam(name = "user") User user) throws Exception {
        try {
            String token = userService.auth(user);
            Map<String, String> jsonToken = new HashMap<>();
            jsonToken.put("token", token);
            return ResponseEntity.ok(jsonToken);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
