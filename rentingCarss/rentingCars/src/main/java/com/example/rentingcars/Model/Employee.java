package com.example.rentingcars.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @Pattern(regexp = "^(junior|advanced|expert)$")
    private String Rank;
    private LocalDate day=LocalDate.now();



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
    private Set<Car> cars;

    @ManyToOne
    @JoinColumn(name = "admin_id" , referencedColumnName = "id")
    @JsonIgnore
    private Admin admin;

}
