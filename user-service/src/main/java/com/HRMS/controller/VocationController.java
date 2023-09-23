package com.HRMS.controller;

import com.HRMS.dto.requests.CreateVocationRequestDto;
import com.HRMS.repository.entity.Vocation;
import com.HRMS.services.VocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.HRMS.constants.RestApiList.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@CrossOrigin("*")
public class VocationController {
    private final VocationService vocationService;

    @PostMapping(CREATEVOCATION)
    public ResponseEntity<Boolean> createVocation(@RequestBody @Valid CreateVocationRequestDto dto) {
        return ResponseEntity.ok(vocationService.createVocation(dto));

    }

    @GetMapping(FINDALLVOCATION)
    public ResponseEntity<List<Vocation>> findAllVocation() {
        return ResponseEntity.ok(vocationService.findAll());

    }

    @GetMapping(FINDALLVOCATIONPENDING)
    public ResponseEntity<List<Vocation>> findAllVocationPending() {
        return ResponseEntity.ok(vocationService.findAllVocationPending());
    }

    @PutMapping(APPROVEVOCATIONREQUEST)
    public ResponseEntity<Boolean> aprroveVocationRequest(@RequestParam String id){
        return ResponseEntity.ok(vocationService.approveVocationRequest(id));
    }

    @PutMapping(REJECTVOCATIONREQUEST)
    public ResponseEntity<Boolean> rejectVocationRequest(@RequestParam String id){
        return ResponseEntity.ok(vocationService.rejectVocationRequest(id));
    }

    @GetMapping(FINDALLBYSORT)
    public ResponseEntity<List<Vocation>> sortedList(){
        return ResponseEntity.ok(vocationService.sortingList());
    }




}
