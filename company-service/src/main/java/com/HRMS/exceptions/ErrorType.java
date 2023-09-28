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
    COMPANY_ALREADY_EXISTS(1007,"This company already exist",HttpStatus.BAD_REQUEST),
    COMPANY_NOT_FOUND(1007,"This company is not found at our database",HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_USER(1008,"UNAUTHORIZED_USER",HttpStatus.BAD_REQUEST),
    COMMENT_NOT_FOUND(1009,"Comment not found",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1010,"User not found",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1011,"Invalid Token",HttpStatus.BAD_REQUEST),
    EXPENSE_NOT_FOUND(1012,"Böyle bir harcama kaydı bulunmamaktadır",HttpStatus.BAD_REQUEST);


    
    
    
    int code;
    String message;
    HttpStatus httpStatus;
}
