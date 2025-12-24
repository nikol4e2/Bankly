package com.webapp.bankly.repository;

import com.webapp.bankly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

    User findByUsernameIgnoreCase(String username);

}
