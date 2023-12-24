package com.example.rentingcars.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Column(columnDefinition = "varchar(225) not null")
    private String feedback;
    @Column(columnDefinition = "int not null")
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
