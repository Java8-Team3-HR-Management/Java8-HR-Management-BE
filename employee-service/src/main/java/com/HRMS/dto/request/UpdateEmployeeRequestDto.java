package com.HRMS.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEmployeeRequestDto {

    private String id;
    private String name;
    private String surname;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Geçerli bir e-posta adresi giriniz.")
    private String email;
    @Pattern(regexp = "^+(?:[0-9] ?){6,14}[0-9]$", message = "Geçerli bir telefon numarası giriniz.")
    private String phone;

}
