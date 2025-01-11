package com.crudApplication.crudApp.Controller;


import com.crudApplication.crudApp.Entity.User;
import com.crudApplication.crudApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


//    User Controller
    @DeleteMapping()
    public ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User byUserName = userService.findByUserName(userName);
        userService.deleteUserById(byUserName.getId());
        return ResponseEntity.ok("User Has Been Deleted!");
    }

//    User Controller
    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User byUserName = userService.findByUserName(userName);
        byUserName.setUserName(user.getUserName());
        byUserName.setPassword(user.getPassword());
        userService.saveUser(byUserName);
        return ResponseEntity.ok("The User Details have been updated!");
    }


}
