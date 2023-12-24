package com.example.rentingcars.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Admin {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Pattern(regexp = "[^0-9]*" , message = "name must not contain numbers")
    @NotNull(message = "name must be not null")
    private String name;
    @NotNull(message = "username must be not null")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;
    @Column(columnDefinition = "varchar(255)not null")
    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" , message = "password must contain at least one number and one uppercase, lowercase letter and special character, and at least 8 or more characters")
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "admin")
    private Set<Employee> employees;


}
