package com.example.rentingcars.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String feedback;

    private Integer rating;


    @ManyToOne
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    @JsonIgnore
    private User user;



    @ManyToOne
    @JoinColumn(name = "car_id" ,referencedColumnName = "id")
    @JsonIgnore
    private Car car;

}
