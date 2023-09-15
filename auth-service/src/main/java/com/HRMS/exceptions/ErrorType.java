package com.HRMS.exceptions;

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
    REGISTER_KULLANICIADI_KAYITLI(1002,"Kullanıcı adı kayıtlı",HttpStatus.BAD_REQUEST),
    REGISTER_PASSWORDS_NOT_MATCH(1003,"Girmiş olduğunuz şifreler uyuşmuyor",HttpStatus.BAD_REQUEST),
    DOLOGIN_INVALID_USERNAME_PASSWORD(1004,"Kullanıcı adı ya da şifre hatalıdır",HttpStatus.BAD_REQUEST),
    NAME_IS_NULL(1005,"Name alanı boş geçilemez",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(1006,"Geçersiz token",HttpStatus.BAD_REQUEST),
    MAIL_ALREADY_HAS_BEEN(1007,"Mail addrees alreadly has been ",HttpStatus.BAD_REQUEST),
    REQUIRED_FIELD(1008,"Zorunlu alanlar hatalı gelmekte",HttpStatus.BAD_REQUEST);



    int code;
    String message;
    HttpStatus httpStatus;
}
// TODO: 2.09.2023 İleri tarihlerde ihtiyaca yönelik type'lar eklenmelidir! 
