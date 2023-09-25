package com.HRMS.controller;

import com.HRMS.dto.request.CreatExpenseRequestDto;
import com.HRMS.dto.request.ApprovalExpenseRequestDto;
import com.HRMS.dto.response.CreateExpenseResponseDto;
import com.HRMS.dto.response.GetAllExpenseResponseDto;
import com.HRMS.services.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.HRMS.constants.RestApiList.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EXPENSE)
@CrossOrigin("*")
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/createexpense")
    public ResponseEntity<Boolean> createExpense(CreatExpenseRequestDto dto){
        return ResponseEntity.ok(expenseService.createExpense(dto));
    }

    @PutMapping("/approvalExpense")
    public ResponseEntity<Boolean> approvalExpense(ApprovalExpenseRequestDto dto){
        return ResponseEntity.ok(expenseService.approvalExpense(dto));
    }

    @GetMapping("/getAllExpense")
    public ResponseEntity<Optional<List<GetAllExpenseResponseDto>>> getAllExpense(){
        List<GetAllExpenseResponseDto> list=expenseService.getAllExpense();
        return ResponseEntity.ok(Optional.of(list));
    }




}
