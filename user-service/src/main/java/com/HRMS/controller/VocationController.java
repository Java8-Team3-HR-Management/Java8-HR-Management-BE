package com.HRMS.controller;

import com.HRMS.dto.requests.CreateVocationRequestDto;
import com.HRMS.dto.requests.UpdateVacationRequestDto;
import com.HRMS.repository.entity.Vocation;
import com.HRMS.services.VocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.HRMS.constants.RestApiList.*;

@RestController
@RequestMapping(VACATION)
@RequiredArgsConstructor
@CrossOrigin("*")
public class VocationController {
    private final VocationService vocationService;

    @PostMapping("/create-vacation/{token}")
    public ResponseEntity<Boolean> createVocation(@RequestBody @Valid CreateVocationRequestDto dto,@PathVariable String token) {
        return ResponseEntity.ok(vocationService.createVocation(dto,token));

    }

    @GetMapping("/find-all-vacation")
    public ResponseEntity<List<Vocation>> findAllVocation() {
        return ResponseEntity.ok(vocationService.findAll());

    }

    @GetMapping("/find-all-vacation-pending")
    public ResponseEntity<List<Vocation>> findAllVocationPending() {
        return ResponseEntity.ok(vocationService.findAllVocationPending());
    }

    @PutMapping("/update-vacation/{token}")
    public ResponseEntity<Boolean> approveVocationRequest(@RequestBody UpdateVacationRequestDto dto,@PathVariable String token){
        return ResponseEntity.ok(vocationService.updateVocationRequest(dto,token));
    }


    @GetMapping(FINDALLBYSORT)
    public ResponseEntity<List<Vocation>> sortedList(){
        return ResponseEntity.ok(vocationService.sortingList());
    }




}
