package com.webapp.bankly.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long uid;

    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    private String username;
    private LocalDate dob;
    private String tel;
    private String tag;
    private String password;
    private String gender;
    @CreationTimestamp
    private LocalDateTime craetedAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


   // private List<String> roles;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "owner")
    private Card card;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;


    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Account> accounts;

}
