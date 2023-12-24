package com.example.rentingcars.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
public class CarDTO {


    @Positive(message = "supplier id must be number")
    @NotNull(message = "supplier id must be not null")
    private Integer supplier_id;
    @NotNull(message = "type must be not null")
    private String type;
    @NotNull(message = "brand must be not null")
    private String brand;
    @NotNull(message = "color must be not null")
    private String color;
    @NotNull(message = "model must be not null")
    private String model;
    @NotNull(message = "location must be not null")
    private String location;
    @Positive(message = "daily price must be number")
    @NotNull(message = "daily price must be not null")
    private double daily_price;
    @Positive(message = "hourly price must be number")
    @NotNull(message = "hourly price must be not null")
    private double hourly_price;
    @Positive(message = "monthly price must be number")
    @NotNull(message = "monthly price must be not null")
    private double monthly_price;
    @Positive(message = "weekly price must be number")
    @NotNull(message = "weekly price must be not null")
    private double weekly_price;
    private Integer authorized;
    @Pattern(regexp = "^(Available|Rented|Refundable)$")
    private String currentStatus;
    @Pattern(regexp = "^(Intact|defect|accident)$")
    private String conditions;
    @AssertFalse(message = "must be false")
    private Boolean isChecked;
    @PositiveOrZero
    private Integer count;


}
