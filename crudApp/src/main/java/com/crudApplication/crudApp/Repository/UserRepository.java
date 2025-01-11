package com.crudApplication.crudApp.Repository;

import com.crudApplication.crudApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String user);
}
