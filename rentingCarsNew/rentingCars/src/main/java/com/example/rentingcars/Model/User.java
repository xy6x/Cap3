package com.example.rentingcars.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
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
    private String phone_number;
    @NotNull(message = "please enter your age")
    @Column(columnDefinition = "int")
    private Integer age;
    @NotEmpty(message = "please enter user name ")
    @Column(columnDefinition = "varchar(35) not null ")
    private String username;
    @NotEmpty(message = "please enter password")
    @Column(columnDefinition = "varchar(35) not null ")
    private String password;
    @NotEmpty(message = "please enter location the car")
    @Column(columnDefinition = "varchar(250) not null")
    private String license;
    @PositiveOrZero
    @Column(columnDefinition = "float")
    private double balance;
    @Column(columnDefinition = "date")
    private LocalDate day=LocalDate.now();


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Rental> rentals;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Car> cars;
}
