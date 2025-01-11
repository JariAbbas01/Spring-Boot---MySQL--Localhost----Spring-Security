package com.crudApplication.crudApp.Service;

import com.crudApplication.crudApp.Entity.User;
import com.crudApplication.crudApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);

        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword()) // Ensure password is encoded
                    .roles(user.getRoles()) // Ensure roles are correctly formatted
                    .build();
        }

        throw new UsernameNotFoundException(username + " is not found!");
    }
}
