package com.example.rentingcars.Repository;

import com.example.rentingcars.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    Car findCarById(Integer id);


    List<Car> findAllByBrand(String brand);
    List<Car> findAllByColor(String color);

    List<Car> findAllByLocation(String location);


    @Query("select c from Car c where c.currentStatus='Available'")
    List<Car> getAllCarsAvailable();
    @Query("select e from Car e where e.day between  ?1 and  ?2")
    List<Car> findCarByDay(Date date, Date dat);



    @Query("select s from Car s where s.isChecked=true")
    List<Car> getAllCarsIsChecked();




}
