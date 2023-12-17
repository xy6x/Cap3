package com.example.rentingcars.Repository;

import com.example.rentingcars.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    Car findCarById(Integer id);
}
