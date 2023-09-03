package com.HRMS.repository.entity;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    String id;
    Long authid;
    String userName;
    String email;
    String password;
    @Builder.Default
    EStatus status= EStatus.PENDING;
}
