package com.webapp.bankly.repository;

import com.webapp.bankly.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
