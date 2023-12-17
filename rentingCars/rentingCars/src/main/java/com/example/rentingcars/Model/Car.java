package com.example.rentingcars.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Pattern(regexp = "^(Available|Rented)$")
    private String currentStatus;  // or >>>>>  private Boolean isAvailable;
    private Boolean isChecked;
}
