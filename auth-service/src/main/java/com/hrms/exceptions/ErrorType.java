package com.hrms.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(1000,"Sunucuda Bilinmeyen bir hata oluştu", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(1001,"İstek formatı hatalı",HttpStatus.BAD_REQUEST),
    REGISTER_KULLANICIADI_KAYITLI(1001,"Kullanıcı adı kayıtlı",HttpStatus.BAD_REQUEST),
    REGISTER_PASSWORDS_NOT_MATCH(1002,"Girmiş olduğunuz şifreler uyuşmuyor",HttpStatus.BAD_REQUEST),
    DOLOGIN_INVALID_USERNAME_PASSWORD(1005,"Kullanıcı adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
    NAME_IS_NULL(1001,"Name alanı boş geçilemez",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1002,"Geçersiz token",HttpStatus.BAD_REQUEST);
    int code;
    String message;
    HttpStatus httpStatus;
}
// TODO: 2.09.2023 İleri tarihlerde ihtiyaca yönelik type'lar eklenmelidir! 
