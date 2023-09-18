package com.HRMS.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BaseEntity {
    boolean state;
    LocalDate createDate;
    LocalDate updateDate;
}
