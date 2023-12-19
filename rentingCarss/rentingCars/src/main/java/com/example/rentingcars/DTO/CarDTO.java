package com.example.rentingcars.DTO;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
public class CarDTO {

    private Integer supplier_id;

    //private Integer id;
    private String type;
    private String brand;
    private String color;
    private String model;
    private String location;
    private double daily_price;
    private double hourly_price;
    private double monthly_price;
    private double weekly_price;
    private Integer authorized;
    @Pattern(regexp = "^(Available|Rented)$")
    private String currentStatus; // or >>>>>  private Boolean isAvailable;
    private String condit;
    private Boolean isChecked;
    private LocalDate localDate = LocalDate.now();


}
