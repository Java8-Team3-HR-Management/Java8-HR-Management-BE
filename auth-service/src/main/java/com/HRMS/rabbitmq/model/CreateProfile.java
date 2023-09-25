package com.HRMS.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateProfile implements Serializable {
        Long authId;
        String name;
        String surname;
        String email;
        String password;
    }

