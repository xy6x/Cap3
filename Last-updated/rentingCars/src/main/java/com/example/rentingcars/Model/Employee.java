package com.example.rentingcars.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;
    @Column(columnDefinition = "varchar(255)not null")
    private String password;
    @Column(columnDefinition = "int not null")
    private Integer age;
    private Integer countOfCarsInspected;
    private Boolean isApproved;
    @Column(columnDefinition = "varchar(20) not null")
    private String qualification;
    private double bonus;
    private Integer totalCars;
    @Column(columnDefinition = "varchar(20) not null")
    private String level;


    @ManyToOne
    @JoinColumn(name = "admin_id" , referencedColumnName = "id")
    @JsonIgnore
    private Admin admin;

    @OneToMany(mappedBy ="superF")
    private Set<Employee> employee;

    @ManyToOne
    @JoinColumn(name = "super",referencedColumnName = "id")
    @JsonIgnore
    private Employee superF;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
    private Set<Car> cars;



}
