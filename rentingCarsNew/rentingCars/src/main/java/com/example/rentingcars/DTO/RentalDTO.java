package com.example.rentingcars.DTO;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalDTO {


    private Integer id;

    @NotNull(message = "duration must be not null")
    private Integer duration;

    @NotNull(message = "dur must be not null")
    @Pattern(regexp = "^(day|week|Month)$")
    private String dur;


    private double total_price;

    @Positive(message = "car id must be number")
    @NotNull(message = "car id must be not null")
    private Integer car_id;


    @Positive(message = "user id must be number")
    @NotNull(message = "user id must be not null")
    private Integer user_id;





}
