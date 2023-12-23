package com.example.rentingcars.DTO;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {




    private Integer id;
    private String feedback;

    private Integer rating;

    @Positive(message = "car id must be number")
    @NotNull(message = "car id must be not null")
    private Integer car_id;


    @Positive(message = "user id must be number")
    @NotNull(message = "user id must be not null")
    private Integer user_id;

}
