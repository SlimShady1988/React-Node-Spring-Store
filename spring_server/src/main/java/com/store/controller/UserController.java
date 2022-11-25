package com.store.controller;

import com.store.entity.User;
import com.store.excaptions.UserAlreadyExistException;
import com.store.excaptions.UserNotFoundException;
import com.store.model.UserDTO;
//import com.store.security.UserDetailsServiceImpl;
import com.store.repository.UserRepository;
import com.store.security.JwtProvider;
import com.store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
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
    @Autowired
    private final AuthenticationManager authenticationManager;
    public UserController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
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

//            authenticationManager
//                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            return created(location).body(resp);

        } catch (UserAlreadyExistException e) {
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        } catch (Exception e) {
            resp.put("message", "Error");
            return ResponseEntity.badRequest().body(resp);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user) {
        Map<String, String> resp = new HashMap<>();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            String token = userService.getUserToken(user.getEmail());
            resp.put("token", token);

            return ResponseEntity.ok(resp);

        } catch (UserNotFoundException e) {
            resp.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(resp);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(req, resp, null);
    }

    @GetMapping("/auth")
    public ResponseEntity<Map<String, String>> auth(Principal principal) throws Exception {
        try {
            String token= userService.getUserToken(principal.getName());
            Map<String, String> jsonToken = new HashMap<>();
            jsonToken.put("token", token);
            return ResponseEntity.ok(jsonToken);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
