package com.example.rentingcars.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDTO {




    private Integer id;

    @NotEmpty(message = "feedback must be not empty")
    private String feedback;
    @NotNull(message = "rating must be not empty")
    @Positive(message = "rating must be number")
    private Integer rating;
    @Positive(message = "car id must be number")
    @NotNull(message = "car id must be not null")
    private Integer car_id;
    @Positive(message = "user id must be number")
    @NotNull(message = "user id must be not null")
    private Integer user_id;

}
