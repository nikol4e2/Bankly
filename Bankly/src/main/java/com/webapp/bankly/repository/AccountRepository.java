package com.webapp.bankly.repository;

import com.webapp.bankly.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
