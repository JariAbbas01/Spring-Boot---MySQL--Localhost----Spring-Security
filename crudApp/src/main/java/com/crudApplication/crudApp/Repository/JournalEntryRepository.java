package com.crudApplication.crudApp.Repository;

import com.crudApplication.crudApp.Entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface JournalEntryRepository extends JpaRepository<JournalEntity, Long> {

}
