package com.example.rentingcars.Model;

import jakarta.persistence.*;
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
    private String name;
    private Integer age;
    private Integer countOfCarsInspected;
    private Boolean isApproved;
    private String qualification;
    private double bonus;
    private Integer totalCar;
    private LocalDate day=LocalDate.now();




    @OneToMany(cascade = CascadeType.ALL,mappedBy = "id")
    private Set<Car> cars;

}
