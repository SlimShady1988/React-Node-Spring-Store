package com.store.security;

import com.store.entity.User;
import com.store.excaptions.UserNotFoundException;
import com.store.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new UserNotFoundException("Has not found the User with this Email"));
            return SecurityUser.fromUser(user);

        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
