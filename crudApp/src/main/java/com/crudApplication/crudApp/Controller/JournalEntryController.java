package com.crudApplication.crudApp.Controller;

import com.crudApplication.crudApp.Entity.JournalEntity;
import com.crudApplication.crudApp.Entity.User;
import com.crudApplication.crudApp.Repository.UserRepository;
import com.crudApplication.crudApp.Service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserRepository userRepository;

//    Journal Controller
    @PostMapping()
    public ResponseEntity<?> postJournalEntry(@RequestBody JournalEntity entry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            journalEntryService.saveEntry(userName,entry); // Assuming save logic here
            return ResponseEntity.ok("Entry saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }



//    Journal Controller
    @GetMapping()
    public ResponseEntity<?> getEntryByUserName(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            List<JournalEntity> entryByUserName = journalEntryService.getEntryByUserName(userName);
            return new ResponseEntity<>(entryByUserName, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






    @PutMapping("{id}")
    public ResponseEntity<?> updateEntry(@RequestBody JournalEntity entity, @PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<JournalEntity> entryById = journalEntryService.getEntryById(userName,id);
        JournalEntity journalEntity = entryById.get();
        if(journalEntity != null){
            journalEntity.setTitle(entity.getTitle());
            journalEntity.setContent(entity.getContent());
            journalEntryService.saveEntry(journalEntity);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryService.deleteEntryById(userName,id);
        return ResponseEntity.ok("Entity has been Deleted!");
    }


}
