package com.example.rentingcars.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String type;
    private String brand;
    private String color;
    private Integer year;
    private String model;
    private String location;
    private double daily_price;
    private double hourly_price;
    private double monthly_price;
    private double weekly_price;
    @Pattern(regexp = "^(day|week|Month)$")
    private Integer dur;
    private Integer authorized;
    @Pattern(regexp = "^(Available|Rented)$")
    private String currentStatus;  // or >>>>>  private Boolean isAvailable;
    private Boolean isChecked;
    @DateTimeFormat( pattern= "yyyy-MM-dd")
    private Date day;







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

}
