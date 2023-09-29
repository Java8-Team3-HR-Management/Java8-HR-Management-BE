package com.HRMS.controller;


import com.HRMS.dto.request.AddUserRequestDto;
import com.HRMS.dto.request.DoLoginRequestDto;
import com.HRMS.dto.request.DoRegisterRequestDto;
import com.HRMS.dto.response.DoLoginResponseDto;
import com.HRMS.dto.response.DoRegisterResponseDto;
import com.HRMS.dto.response.UserSaveResponseDto;
import com.HRMS.rabbitmq.model.CreateProfile;
import com.HRMS.rabbitmq.producer.CreateProfileProducer;
import com.HRMS.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

import static com.HRMS.constants.RestApis.AUTH;
import static com.HRMS.constants.RestApis.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
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

    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<DoLoginResponseDto> doLogin(@RequestBody  DoLoginRequestDto dto){
      return ResponseEntity.ok(authService.login(dto));

    }

    @PostMapping("/createEmployee")
    public ResponseEntity<UserSaveResponseDto> createEmployee(@RequestBody @Valid AddUserRequestDto dto){
        Boolean check=authService.createEmployee(dto);
        if(check){
         return ResponseEntity.ok(UserSaveResponseDto.builder()
                    .status(200)
                    .result("Kayıt Başarılı.")
                    .build());
        }
        return ResponseEntity.badRequest().body(UserSaveResponseDto.builder()
                .status(100)
                .result("Bilgileri tekrar kontrol ediniz.")
                .build());

    }
    @PostMapping("/createAdmin")
    public ResponseEntity<UserSaveResponseDto> createAdmin(@RequestBody @Valid AddUserRequestDto dto){
        Boolean check=authService.addAdmin(dto);
        if(check){
            return ResponseEntity.ok(UserSaveResponseDto.builder()
                    .status(200)
                    .result("Kayıt Başarılı.")
                    .build());
        }
        return ResponseEntity.badRequest().body(UserSaveResponseDto.builder()
                .status(100)
                .result("Bilgileri tekrar kontrol ediniz.")
                .build());

    }
    @PostMapping("/createManager")
    public ResponseEntity<UserSaveResponseDto> createManager(@RequestBody @Valid AddUserRequestDto dto){
        Boolean check=authService.addManager(dto);
        if(check){
            return ResponseEntity.ok(UserSaveResponseDto.builder()
                    .status(200)
                    .result("Kayıt Başarılı.")
                    .build());
        }
        return ResponseEntity.badRequest().body(UserSaveResponseDto.builder()
                .status(100)
                .result("Bilgileri tekrar kontrol ediniz.")
                .build());

    }

@PostMapping("/forgot-password/{email}")
public ResponseEntity<Boolean> forgotPassword(@PathVariable String email){
        return ResponseEntity.ok(authService.forgotPassword(email));
}




}


