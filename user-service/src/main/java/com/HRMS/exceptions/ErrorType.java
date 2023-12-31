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
    GUEST_ALREADY_EXIST(1007,"This guest already exist",HttpStatus.BAD_REQUEST),
    EMPLOYEE_ALREADY_EXIST(1008,"This employee already exist",HttpStatus.BAD_REQUEST),
    EMPLOYEE_UNAUTHORIZED_UPDATE(1009,"Kimlik doğrulaması başarısız.",HttpStatus.BAD_REQUEST),
    EMPLOYEE_NOT_FOUND(1010,"Çalışan bulunamadı!",HttpStatus.BAD_REQUEST),
    VOCATION_NOT_CREATED(1011,"Oluşturulamadı!",HttpStatus.BAD_REQUEST),
    VOCATION_DURATION_NOT_BE_MINUS(1012,"Süre eksi olamaz!",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1017,"Unauthorized yoken!",HttpStatus.BAD_REQUEST),
    VOCATION_NOT_VALID(1013,"Geçerli değil!",HttpStatus.BAD_REQUEST),
    DEBT_OVER_LIMIT(1014,"Maaşınızın yarısından fazla avans çekemezsiniz.",HttpStatus.BAD_REQUEST),
    DEBT_ONE_TIME(1015,"Bir ay içinde birden fazla avans çekemezsiniz.",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1016,"User not found",HttpStatus.BAD_REQUEST),
    UNAUTHORIZED_USER(1017,"Yetkilendirilmemiş kullanıcı",HttpStatus.BAD_REQUEST),
    DEBT_NOT_FOUND(1018,"Çalışana dair avans kaydı bulunamadı.",HttpStatus.BAD_REQUEST);



    
    
    
    int code;
    String message;
    HttpStatus httpStatus;
}
