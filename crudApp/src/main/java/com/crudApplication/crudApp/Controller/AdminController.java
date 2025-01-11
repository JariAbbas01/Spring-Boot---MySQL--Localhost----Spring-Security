package com.crudApplication.crudApp.Controller;


import com.crudApplication.crudApp.Entity.JournalEntity;
import com.crudApplication.crudApp.Entity.User;
import com.crudApplication.crudApp.Service.JournalEntryService;
import com.crudApplication.crudApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public ResponseEntity<?> createAdminUser(@RequestBody User user){
        try {
            userService.saveAdminUser(user);
            return ResponseEntity.ok(user.getUserName() + " is the admin now!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //    Admin Controller
    @GetMapping("/all-users")
    public ResponseEntity<?> getUser(){
        try {
            List<User> user = userService.getUser();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //    Admin Controller
    @GetMapping("/all-entries")
    public ResponseEntity<?> getAllEntries() {
        try {
            List<JournalEntity> allEntry = journalEntryService.getAllEntry();
            return new ResponseEntity<>(allEntry, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}
