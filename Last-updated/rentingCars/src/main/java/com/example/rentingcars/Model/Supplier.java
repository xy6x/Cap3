package com.example.rentingcars.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name most be not empty")
    @Column(columnDefinition = "varchar(35) not null")
    private String name;
    @NotNull(message = "username must be not null")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;
    @Column(columnDefinition = "varchar(255)not null")
    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" , message = "password must contain at least one number and one uppercase, lowercase letter and special character, and at least 8 or more characters")
    private String password;

    @NotEmpty(message = "phone number most be not empty")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phone_number;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "supplier")
    private Set<Car> cars;

}
