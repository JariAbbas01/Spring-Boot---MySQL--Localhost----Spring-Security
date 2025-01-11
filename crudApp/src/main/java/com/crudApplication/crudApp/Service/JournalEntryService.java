package com.crudApplication.crudApp.Service;

import com.crudApplication.crudApp.Entity.JournalEntity;
import com.crudApplication.crudApp.Entity.User;
import com.crudApplication.crudApp.Repository.JournalEntryRepository;
import com.crudApplication.crudApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;

    public JournalEntity saveEntry(String userName,JournalEntity journal){
        User byUserName = userRepository.findByUserName(userName);
        journal.setUser(byUserName);
        byUserName.setJournalEntities(journal.getUser().getJournalEntities());
        return journalEntryRepository.save(journal);
    }

    public JournalEntity saveEntry(JournalEntity journal){
        return journalEntryRepository.save(journal);
    }

    public List<JournalEntity> getAllEntry(){
        return journalEntryRepository.findAll();
    }

//    Testing
    public List<JournalEntity> getEntryByUserName(String userName){
        User byUserName = userRepository.findByUserName(userName);
        List<JournalEntity> journalEntities = byUserName.getJournalEntities();
        return journalEntities;
    }

    public Optional<JournalEntity> getEntryById(String userName, Long id){
        User byUserName = userRepository.findByUserName(userName);

        for (JournalEntity ids : byUserName.getJournalEntities()){
            if(id == ids.getId()){
                Optional<JournalEntity> byId = journalEntryRepository.findById(id);
                return byId;
            }
        }
        return Optional.empty();
    }

    public void deleteEntryById(String userName, Long id){
        User byUserName = userRepository.findByUserName(userName);
        byUserName.getJournalEntities().removeIf(x->x.getId().equals(id));
        userRepository.save(byUserName);
    }

}
