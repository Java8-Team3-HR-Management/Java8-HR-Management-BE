package com.HRMS.services;

import com.HRMS.dto.request.CreatExpenseRequestDto;

import com.HRMS.dto.request.ApprovalExpenseRequestDto;
import com.HRMS.dto.response.GetAllExpenseResponseDto;
import com.HRMS.exceptions.CompanyException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IExpenseMapper;
import com.HRMS.repository.IExpenseRepository;
import com.HRMS.repository.entity.Expense;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.ServiceManager;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService extends ServiceManager<Expense, String> {

    private final IExpenseRepository repository;
    public ExpenseService(IExpenseRepository repository) {
        super(repository);
        this.repository=repository;
    }

    public Boolean createExpense(CreatExpenseRequestDto dto){
        if(dto.getFile().isEmpty()){
            Expense expense = Expense.builder()
                    .name(dto.getName())
                    .surName(dto.getSurName())
                    .department(dto.getDepartment())
                    .expenditureType(dto.getExpenditureType())
                    .amountOfExpenditure(dto.getAmountOfExpenditure())
                    .currency(dto.getCurrency())
                    .approvalStatus(EStatus.PENDING)
                    .requestDate(LocalDate.now())
                    .build();
            save(expense);
           }
        Expense expense = Expense.builder()
                .name(dto.getName())
                .surName(dto.getSurName())
                .department(dto.getDepartment())
                .expenditureType(dto.getExpenditureType())
                .amountOfExpenditure(dto.getAmountOfExpenditure())
                .currency(dto.getCurrency())
                .approvalStatus(EStatus.PENDING)
                .requestDate(LocalDate.now())
                .file(dto.getFile())
                .build();
        save(expense);
        return true;
        }


    public List<GetAllExpenseResponseDto> getAllExpense() {
        List<Expense> optExpenses= repository.findAll();
        List<GetAllExpenseResponseDto> expenses= new ArrayList<>();
        for(Expense expense: optExpenses){
            expenses.add(IExpenseMapper.INSTANCE.toGetAllExpense(expense));
        }
        return expenses;
    }

    public Boolean approvalExpense(ApprovalExpenseRequestDto dto){
        Optional<Expense> optApproval= repository.findById(dto.getId());
        if(optApproval.isEmpty()){
            throw new CompanyException(ErrorType.EXPENSE_NOT_FOUND);
        }
        optApproval.get().setApprovalStatus(dto.getApprovalStatus());
        optApproval.get().setReplyDate(LocalDate.now());
        update(optApproval.get());
        return true;
    }


}
