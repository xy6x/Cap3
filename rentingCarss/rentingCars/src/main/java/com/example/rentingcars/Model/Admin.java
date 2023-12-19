package com.example.rentingcars.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Admin {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;
    private String username;
    private String password;




    @OneToMany(cascade = CascadeType.ALL,mappedBy = "admin")
    private Set<Employee> employees;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "admin")
    private Set<Supplier> suppliers;
}
