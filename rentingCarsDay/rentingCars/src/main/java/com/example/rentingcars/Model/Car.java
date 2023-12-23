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
    private Integer id;
    @Column(columnDefinition = "varchar(35)")
    private String type;
    @Column(columnDefinition = "varchar(35)")
    private String brand;
    @Column(columnDefinition = "varchar(20)")
    private String color;
    @Column(columnDefinition = "varchar(4)")
    private String model;
    @Column(columnDefinition = "varchar(100)")
    private String location;
    @Column(columnDefinition = "float(4)")
    private double daily_price;
    @Column(columnDefinition = "float(3)")
    private double hourly_price;
    @Column(columnDefinition = "float(5)")
    private double monthly_price;
    @Column(columnDefinition = "float(4)")
    private double weekly_price;
    private Integer authorized;
    @Column(columnDefinition = "varchar(15)")
    private String currentStatus; // or >>>>>  private Boolean isAvailable;
    @Column(columnDefinition = "varchar(15)")
    private String conditions;
    @Column(columnDefinition = "Boolean ")
    private Boolean isChecked;
    @Column(columnDefinition = "int")
    private Integer count;
    private  LocalDate localDate;
    private double rating;






    @ManyToOne
    @JoinColumn(name = "supplier_id" , referencedColumnName = "id")
    @JsonIgnore
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @JsonIgnore
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "car")
    private Set<Rental> rentals;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "car")
    private Set<Review> reviews;



}
