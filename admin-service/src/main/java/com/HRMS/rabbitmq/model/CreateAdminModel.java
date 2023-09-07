package com.HRMS.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateAdminModel implements Serializable {
    String email;
}
