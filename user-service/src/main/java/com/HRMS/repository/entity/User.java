package com.HRMS.repository.entity;

import com.HRMS.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "guests")
public class User extends BaseEntity
{
    @Id
    Long id;
    Long authid;
    String nameSurname;
    String email;
    String password;
    @Builder.Default
    EStatus status= EStatus.ACTIVE;

}
