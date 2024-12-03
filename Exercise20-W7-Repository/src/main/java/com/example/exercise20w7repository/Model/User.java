package com.example.exercise20w7repository.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "not valid name")
    @Size(min = 5,message = "name Length should be more than 4")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;
    @NotEmpty(message ="not valid user name")
    @Size(min = 5,message = "username Length should be more than 4")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String userName;
    @NotEmpty(message = "not valid password")
    @Column(columnDefinition = "varchar(18) not null")
    private String password;
    @NotEmpty(message = "not valid email")
    @Email(message = "must be valid email")
    @Column(columnDefinition = "varchar(45) not null unique")
    private String email;
    @NotEmpty(message = "not valid role")
    @Pattern(regexp = "(user|admin)$",message = "role must be user or admin only")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;
    @NotNull(message = "not valid age")
    @Positive(message = "age must be positive")
    private Integer age;

}
