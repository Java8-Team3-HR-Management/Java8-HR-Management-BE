package com.HRMS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/admin-service")
    public ResponseEntity<String> fallbackAdminService(){
        return ResponseEntity.ok("Admin servisi şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz");
    }
    @GetMapping("/auth-service")
    public ResponseEntity<String> fallbackAuthService(){
        return ResponseEntity.ok("Auth servisi şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz");
    }
    @GetMapping("/company-service")
    public ResponseEntity<String> fallbackCompanyService(){
        return ResponseEntity.ok("Company servisi şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz");
    }
    @GetMapping("/employee-service")
    public ResponseEntity<String> fallbackEmployeeService(){
        return ResponseEntity.ok("Employee servisi şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz");
    }
    @GetMapping("/mail-service")
    public ResponseEntity<String> fallbackMailService(){
        return ResponseEntity.ok("Mail servisi şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz");
    }
    @GetMapping("/user-service")
    public ResponseEntity<String> fallbackUserService(){
        return ResponseEntity.ok("User servisi şu anda hizmet verememektedir. Lütfen daha sonra tekrar deneyiniz");
    }
}
