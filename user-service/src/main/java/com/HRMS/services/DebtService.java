package com.HRMS.services;

import com.HRMS.dto.requests.DebtRequestDto;
import com.HRMS.dto.response.DebtResponseDto;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.exceptions.UserException;
import com.HRMS.mapper.IUserMapper;
import com.HRMS.repository.IDebtRepository;
import com.HRMS.repository.IUserRepository;
import com.HRMS.repository.entity.Debt;
import com.HRMS.repository.entity.User;
import com.HRMS.utils.JwtTokenManager;
import com.HRMS.utils.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DebtService extends ServiceManager<Debt, String> {

    private final IUserRepository userRepository;
    private final IDebtRepository repository;
    private final IUserMapper userMapper;
    private final JwtTokenManager tokenManager;

    public DebtService(IUserRepository userRepository,IDebtRepository repository, IUserMapper userMapper, JwtTokenManager tokenManager) {
        super(repository);
        this.userRepository = userRepository;
        this.repository = repository;
        this.userMapper = userMapper;
        this.tokenManager = tokenManager;
    }

    public Boolean requestAdvance(DebtRequestDto requestDTO, String token) {
        Optional<Long> id = tokenManager.getByIdFromToken(token);
        if (id.isEmpty()) {
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        LocalDate currentDate = LocalDate.now();
        Optional<Debt> optionalDebt = repository.findByUserId(requestDTO.getUserId());
        if (!optionalDebt.isEmpty() && !(optionalDebt.get().getLastAdvanceDate() == null)
                && optionalDebt.get().getLastAdvanceDate().getMonth().equals(currentDate.getMonth())) {
            throw new UserException(ErrorType.DEBT_ONE_TIME);
        }
        Optional<User> optionalUser = userRepository.findById(requestDTO.getUserId());

        double maxAllowedAmount = optionalUser.get().getSalary() / 2;
        if (requestDTO.getRequestedAmount() > maxAllowedAmount) {
            throw new UserException(ErrorType.DEBT_OVER_LIMIT);
        }


        Debt debt = userMapper.toDebtFromAdvanceRequestDTO(requestDTO);

        double remainingSalary = debt.getSalary() - requestDTO.getRequestedAmount();
        debt.setSalary(remainingSalary);
        debt.setLastAdvanceDate(LocalDate.now());
        save(debt);

        return true;
    }

    public List<DebtResponseDto> getAllDebts() {
        List<Debt> debts = repository.findAll();
        List<DebtResponseDto> advanceResponseDTOs = new ArrayList<>();
        for (Debt debt : debts) {
            advanceResponseDTOs.add(userMapper.toResponseDto(debt));
        }
        return advanceResponseDTOs;
    }


}







