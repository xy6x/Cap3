package com.example.rentingcars.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Supplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String phone_number;
    private Integer numberOfCrs;
    private LocalDate date=LocalDate.now();




//
//    @OneToMany(cascade = CascadeType.ALL,mappedBy = "supplier")
//    private Set<Rental> rentals;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "supplier")
    private Set<Car> cars;



    @ManyToOne
    @JoinColumn(name = "admin_id" , referencedColumnName = "id")
    @JsonIgnore
    private Admin admin;


}
