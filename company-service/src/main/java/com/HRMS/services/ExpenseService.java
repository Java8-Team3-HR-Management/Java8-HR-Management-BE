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
import com.HRMS.utils.JwtTokenManager;
import com.HRMS.utils.ServiceManager;

import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService extends ServiceManager<Expense, String> {

    private final IExpenseRepository repository;
    private final JwtTokenManager tokenManager;
    public ExpenseService(IExpenseRepository repository,JwtTokenManager tokenManager) {
        super(repository);
        this.repository=repository;
        this.tokenManager=tokenManager;
    }

    public Boolean createExpense(CreatExpenseRequestDto dto,String token){
        Optional<Long> id=tokenManager.getByIdFromToken(token);
        if (id.isEmpty()) {
            throw new CompanyException(ErrorType.USER_NOT_FOUND);
        }
        Optional<String> role = tokenManager.getRoleFromToken(token.toString());
        if (role.isEmpty()) {
            throw new CompanyException(ErrorType.UNAUTHORIZED_USER);
        }
        if(role.get().equals("EMPLOYEE")){
            Expense expense = Expense.builder()
                    .name(dto.getName())
                    .surName(dto.getSurName())
                    .department(dto.getDepartment())
                    .expenditureType(dto.getExpenditureType())
                    .amountOfExpenditure(dto.getAmountOfExpenditure())
                    .approvalStatus(EStatus.PENDING)
                    .requestDate(LocalDate.now())
                    .build();
            save(expense);
            return true;
           }
        throw new CompanyException(ErrorType.UNAUTHORIZED_USER);
        }


    public List<GetAllExpenseResponseDto> getAllExpense() {
        List<Expense> optExpenses= repository.findAll();
        List<GetAllExpenseResponseDto> expenses= new ArrayList<>();
        for(Expense expense: optExpenses){
            expenses.add(IExpenseMapper.INSTANCE.toGetAllExpense(expense));
        }
        return expenses;
    }
    public List<GetAllExpenseResponseDto> getAllPendingExpense() {
        List<Expense> optExpenses= repository.findAll();
        List<GetAllExpenseResponseDto> expenses= new ArrayList<>();
        for(Expense expense: optExpenses){
            if (expense.getApprovalStatus()==EStatus.PENDING)
            expenses.add(IExpenseMapper.INSTANCE.toGetAllExpense(expense));
        }
        return expenses;
    }

    public Boolean approvalExpense(ApprovalExpenseRequestDto dto,String token){
        Optional<Long> id=tokenManager.getByIdFromToken(token);
        if (id.isEmpty()) {
            throw new CompanyException(ErrorType.USER_NOT_FOUND);
        }
        Optional<String> role = tokenManager.getRoleFromToken(token.toString());
        if (role.isEmpty()) {
            throw new CompanyException(ErrorType.UNAUTHORIZED_USER);
        }
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
