/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.HRMS.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Ugur
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(1000,"Sunucuda Bilinmeyen bir hata oluştu", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"İstek formatı hatalı",HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICIADI_KAYITLI(1002,"Kullanıcı adı kayıtlı",HttpStatus.BAD_REQUEST),
    REGISTER_PASSWORD_NOT_MATCH(1003,"Girmiş olduğunuz şifreler uyuşmamaktadır",HttpStatus.BAD_REQUEST),
    DOLOGIN_INVALID_USERNAME_PASSWORD(1004,"Kullanıcı adı veya şifre hatalı",HttpStatus.BAD_REQUEST),
    NAME_IS_NULL(1005,"Name alanı boş geçilemez",HttpStatus.BAD_REQUEST),
    ID_NOT_FOUND(1006,"Aradığınız id ye ait kayıt bulunamadı",HttpStatus.BAD_REQUEST),
    EMPLOYEE_ALREADY_EXIST(1007,"This employee already exist",HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_FOUND(1008,"Çalışan bulunamadı!",HttpStatus.BAD_REQUEST),

    POST_ALREADY_EXISTS(1009, "Post zaten eklendi.", HttpStatus.BAD_REQUEST),
    POST_NOT_FOUND(1010,"Post bulunamadı.",HttpStatus.BAD_REQUEST);
    
    
    
    int code;
    String message;
    HttpStatus httpStatus;
}
