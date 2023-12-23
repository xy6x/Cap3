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
    @NotEmpty(message = "teacher name must not be empty")
    @Pattern(regexp = "[^0-9]*" , message = "name must not contain numbers")
    private String name;

    @Column(columnDefinition = "int not null")
    @Positive(message = "age must be number")
    @NotNull(message = "age must not be null")
    private Integer age;
    private String city;


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
    private LocalDate day=LocalDate.now();



    @OneToMany(mappedBy ="superF")
    private Set<Employee> employee;

    @ManyToOne
    @JoinColumn(name = "super",referencedColumnName = "id")
    @JsonIgnore
    private Employee superF;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
    private Set<Car> cars;

    @ManyToOne
    @JoinColumn(name = "admin_id" , referencedColumnName = "id")
    @JsonIgnore
    private Admin admin;
}
