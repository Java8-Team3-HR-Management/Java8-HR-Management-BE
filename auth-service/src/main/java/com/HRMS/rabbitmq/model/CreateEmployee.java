package com.HRMS.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateEmployee implements Serializable {
    String email;
}
