package com.HRMS.repository.entity;

import com.HRMS.repository.enums.EStatus;
import com.HRMS.repository.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "expense")
public class Expense extends BaseEntity {
    @Id
    String id;
    String name;
    String surName;
    String department;
    ExpenditureType expenditureType;
    BigDecimal amountOfExpenditure;
    String currency;
    @Builder.Default
    EStatus approvalStatus = EStatus.PENDING;
    LocalDate requestDate;
    LocalDate replyDate;
    List<String> file;



}
