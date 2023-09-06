package com.HRMS.controller;


import com.HRMS.dto.request.DoLoginRequestDto;
import com.HRMS.dto.request.DoRegisterRequestDto;
import com.HRMS.dto.response.DoLoginResponseDto;
import com.HRMS.dto.response.DoRegisterResponseDto;
import com.HRMS.rabbitmq.model.CreateProfile;
import com.HRMS.rabbitmq.producer.CreateProfileProducer;
import com.HRMS.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.HRMS.constants.RestApis.AUTH;
import static com.HRMS.constants.RestApis.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CreateProfileProducer createProfileProducer;


    @GetMapping("/testrabbit")
    public ResponseEntity<Void> testRabbitSendMessage(String username,String email,Long authid){
        createProfileProducer.sendCreateProfileMessage(
                CreateProfile.builder()
                        .authid(authid)
                        .email(email)
                        .username(username)
                        .build()
        );
        return ResponseEntity.ok().build();
    }

    @PostMapping(REGISTER)
    @CrossOrigin("*")
    public ResponseEntity<DoRegisterResponseDto> doRegister(@RequestBody @Valid DoRegisterRequestDto dto){
        Boolean isRegister = authService.register(dto);
        if(isRegister)
            return ResponseEntity.ok(DoRegisterResponseDto.builder()
                    .status(200)
                    .result("Kayıt İşlemi Başarılı.")
                    .build());
        return ResponseEntity.badRequest().body(
                DoRegisterResponseDto.builder()
                        .status(400)
                        .result("Kayıt İşlemi Başarısız oldu. Lütfen tekrar deneyiniz!")
                        .build()
        );
    }

    @PostMapping(LOGIN)
    @CrossOrigin("*")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody @Valid DoLoginRequestDto dto){
        String token = authService.;
        return ResponseEntity.ok(DoLoginResponseDto.builder()
                .status(200)
                .result("Giriş İşlemi Başarılı")
                .token(token)
                .build());
    }






}


