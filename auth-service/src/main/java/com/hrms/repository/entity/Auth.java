package com.hrms.repository.entity;

import com.hrms.repository.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
    @Column(length = 64, unique = true,nullable = false)
    String username;
    @Column(length = 64,nullable = false)
    String password;
    @Column(unique = true)
    String email;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    ERole roles = ERole.ADMIN;
}
