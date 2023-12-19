package com.example.rentingcars.DTO;


import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalDTO {


    private Integer id;
    private Integer duration;
    @Pattern(regexp = "^(hour|day|week|Month)$")
    private String dur;
    private double total_price;

    private Integer car_id;
    private Integer user_id;





}
