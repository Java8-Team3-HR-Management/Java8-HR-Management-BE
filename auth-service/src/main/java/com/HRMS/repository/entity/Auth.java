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
    @Column(length = 64,nullable = false)
    String password;
    @Column(unique = true)
    String email;
    @Enumerated(EnumType.STRING)
    ERole roles;
}
