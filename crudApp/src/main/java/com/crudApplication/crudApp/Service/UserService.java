package com.crudApplication.crudApp.Service;

import com.crudApplication.crudApp.Config.EncoderConfig;
import com.crudApplication.crudApp.Entity.User;
import com.crudApplication.crudApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EncoderConfig encoderConfig;

//    public User (New User)
    public User saveUser(User user){
        user.setPassword(encoderConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles("USER");
        return userRepository.save(user);
    }

//    Admin User

    public User saveAdminUser(User user){
        user.setPassword(encoderConfig.passwordEncoder().encode(user.getPassword()));
        user.setRoles("ADMIN");
        return userRepository.save(user);
    }



    public List<User> getUser(){

        return userRepository.findAll();
    }

    


    public User findByUserName(String user){
        return userRepository.findByUserName(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

}
