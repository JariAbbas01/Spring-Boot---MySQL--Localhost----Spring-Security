//package com.crudApplication.crudApp.Service;
//
//
//import com.crudApplication.crudApp.Entity.JournalEntity;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//@SpringBootTest
//public class EntryByIdServiceFunctionTest {
//
//
//    @Autowired
//    private JournalEntryService journalEntryService;
//
//
//    @ParameterizedTest
//    @CsvSource({
//            "5","4","6"
//    })
//    public void checkEntryByIDFunc(Long id){
//        Optional<JournalEntity> entryById = journalEntryService.getEntryById(id);
//        Assertions.assertNotNull(entryById, "Failed For " + entryById.get().getTitle());
//    }
//
//

//}
