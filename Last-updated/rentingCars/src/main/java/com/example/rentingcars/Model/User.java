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
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "please enter name ")
    @Column(columnDefinition = "varchar(35) not null")
    private String name;
    @NotEmpty(message = "please enter phone number ")
    @Column(columnDefinition = "varchar(35) not null")
    private Integer phone_number;
    @NotNull(message = "please enter your age")
    @Column(columnDefinition = "int")
    private Integer age;

    @NotNull(message = "username must be not null")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;
    @Column(columnDefinition = "varchar(255)not null")
    @Pattern(regexp =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" , message = "password must contain at least one number and one uppercase, lowercase letter and special character, and at least 8 or more characters")
    private String password;
    @NotEmpty(message = "please enter location the car")
    @Column(columnDefinition = "varchar(250) not null")
    private String license;




    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Review> reviews;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Rental> rentals;



}
