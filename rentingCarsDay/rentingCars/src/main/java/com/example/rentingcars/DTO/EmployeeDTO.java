package com.example.rentingcars.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "teacher name must not be empty")
    @Pattern(regexp = "[^0-9]*" , message = "name must not contain numbers")
    private String name;

    @Column(columnDefinition = "int not null")
    @Positive(message = "age must be number")
    @NotNull(message = "age must not be null")
    private Integer age;


    private Integer countOfCarsInspected;
    @AssertFalse
    private Boolean isApproved;

    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "qualification  must not be empty")
    private String qualification;

    private double bonus;
    private Integer totalCars;


    @Pattern(regexp = "^(beginner|advanced|expert)$")
    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "level must not be empty")
    private String level;
}
