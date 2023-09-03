package com.HRMS.rabbitmq.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailForgotPassModel implements Serializable {

    String username;
    String email;
    String activationCode;
    String decodedPassword;
}
