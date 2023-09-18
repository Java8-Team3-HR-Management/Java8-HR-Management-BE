package com.HRMS.repository.entity;

import com.HRMS.repository.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Table(name = "tblauth")
public class Auth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String password;
    @Column(unique = true)
    String email;
    @Column(unique = true)
    String companyEmail;
    @Enumerated(EnumType.STRING)
    ERole roles;
}
