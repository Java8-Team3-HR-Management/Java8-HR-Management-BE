package com.HRMS.mapper;


import com.HRMS.dto.request.CreatExpenseRequestDto;
import com.HRMS.dto.response.GetAllExpenseResponseDto;
import com.HRMS.repository.entity.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IExpenseMapper {
    IExpenseMapper INSTANCE = Mappers.getMapper(IExpenseMapper.class);
    Expense toExpenseFromDto(final CreatExpenseRequestDto creatExpenseRequestDto);
    GetAllExpenseResponseDto toGetAllExpense(final Expense expense);

}
