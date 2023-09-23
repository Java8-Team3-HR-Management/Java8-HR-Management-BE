package com.HRMS.controller;

import com.HRMS.dto.requests.DebtRequestDto;
import com.HRMS.dto.response.DebtResponseDto;
import com.HRMS.services.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.HRMS.constants.RestApiList.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@CrossOrigin("*")
public class DebtController {
    private final DebtService debtService;
    @PostMapping(ADVANCE)
    public DebtResponseDto requestAdvance(@RequestBody DebtRequestDto requestDTO) {
        return debtService.requestAdvance(requestDTO);
    }

    @GetMapping(ALLDEBTS)
    public List<DebtResponseDto> getAllDebts() {
        return debtService.getAllDebts();
    }








}
