package com.example.rentingcars.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(columnDefinition = "varchar(35) not null")
    private String type;
    @Column(columnDefinition = "varchar(35) not null")
    private String brand;
    @Column(columnDefinition = "varchar(35) not null")
    private String color;
    @Column(columnDefinition = "varchar(35) not null")
    private String model;
    @Column(columnDefinition = "varchar(35) not null")
    private String location;
    @Column(columnDefinition = "double not null")
    private double daily_price;
    @Column(columnDefinition = "double not null")
    private double hourly_price;
    @Column(columnDefinition = "double not null")
    private double monthly_price;
    @Column(columnDefinition = "double not null")
    private double weekly_price;
    private Integer authorized;
    @Column(columnDefinition = "varchar(35) not null")
    private String currentStatus; // or >>>>>  private Boolean isAvailable;
    @Column(columnDefinition = "varchar(35) not null")
    private String conditions;
    @Column(columnDefinition = "Boolean ")
    private Boolean isChecked;
    @Column(columnDefinition = "int")
    private Integer count;
    @Column(columnDefinition = "int not null")
    private Integer rating;







    @ManyToOne
    @JoinColumn(name = "supplier_id" , referencedColumnName = "id")
    @JsonIgnore
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;





    @OneToMany(cascade = CascadeType.ALL,mappedBy = "car")
    private Set<Rental> rentals;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "car")
    private Set<Review> reviews;



}
