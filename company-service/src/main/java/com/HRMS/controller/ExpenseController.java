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

    @PostMapping("/create-expense/{token}")
    public ResponseEntity<Boolean> createExpense(@RequestBody CreatExpenseRequestDto dto,@PathVariable String token){
        return ResponseEntity.ok(expenseService.createExpense(dto,token));
    }

    @PutMapping("/approval-expense/{token}")
    public ResponseEntity<Boolean> approvalExpense(@RequestBody ApprovalExpenseRequestDto dto,@PathVariable String token){
        return ResponseEntity.ok(expenseService.approvalExpense(dto,token));
    }

    @GetMapping("/get-all-expense/{companyId}")
    public ResponseEntity<Optional<List<GetAllExpenseResponseDto>>> getAllExpense(@RequestParam String companyId){
        List<GetAllExpenseResponseDto> list=expenseService.getAllExpense(companyId);
        return ResponseEntity.ok(Optional.of(list));
    }
    @GetMapping("/get-all-pending-expense/{companyId}")
    public ResponseEntity<Optional<List<GetAllExpenseResponseDto>>> getAllPendingExpense(@RequestParam String companyId){
        List<GetAllExpenseResponseDto> list=expenseService.getAllPendingExpense(companyId);
        return ResponseEntity.ok(Optional.of(list));
    }





}
