package com.HRMS.dto.response;

import com.HRMS.repository.enums.EStatus;
import com.HRMS.repository.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetAllExpenseResponseDto {
    String name;
    String surName;
    String department;
    ExpenditureType expenditureType;
    BigDecimal amountOfExpenditure;
    String currency;
    EStatus approvalStatus;
    LocalDate requestDate;
    LocalDate replyDate;
    List<String> file;
}
