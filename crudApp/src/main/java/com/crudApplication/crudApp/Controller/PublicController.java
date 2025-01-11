package com.crudApplication.crudApp.Controller;

import com.crudApplication.crudApp.Entity.User;
import com.crudApplication.crudApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    //    Public Controller

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody User user){
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
