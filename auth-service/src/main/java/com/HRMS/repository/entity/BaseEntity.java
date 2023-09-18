package com.HRMS.repository.entity;


import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
@SuperBuilder
public class BaseEntity {
    @Builder.Default
    boolean state = true;

    LocalDate createDate;

    LocalDate updateDate;
}