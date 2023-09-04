package com.hrms.manager;

import com.hrms.dto.request.UserSaveRequestDto;
import com.hrms.dto.response.UserSaveResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.hrms.constants.RestApis.*;

@FeignClient(name = "user-micro-service-manager",
        url = "http://localhost:8880",
        dismiss404 = true)
public interface IUserManager {
    @PostMapping(SAVE)
    ResponseEntity<UserSaveResponseDto> save(@RequestBody UserSaveRequestDto dto);

}