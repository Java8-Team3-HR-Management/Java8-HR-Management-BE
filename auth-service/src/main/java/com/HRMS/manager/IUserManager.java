package com.HRMS.manager;

import com.HRMS.dto.request.UserSaveRequestDto;
import com.HRMS.dto.response.UserSaveResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.HRMS.constants.RestApis.*;

@FeignClient(name = "user-micro-service-manager",
        url = "http://localhost:8880",
        dismiss404 = true)
public interface IUserManager {
    @PostMapping(SAVE)
    ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto dto);

}