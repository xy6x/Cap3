package com.example.rentingcars.Repository;

import com.example.rentingcars.Model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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




    @Query("select s from Car s where s.isChecked=false")
    List<Car> getAllCarsNotChecked();



    @Query("select c from Car c where c.supplier=?1")
    List<Car> getAllCarSameSupplier(Integer id);




    List<Car> findAllByOrderByRatingDesc();

    @Query("select c from Car c where c.isChecked=true and c.currentStatus='Available'")
    List<Car> getRentableCars();


    @Query("select e from Car e where e.daily_price between  ?1 and  ?2")
    List<Car> findCarByDay(Integer min, Integer Max);
    @Query("select e from Car e where e.weekly_price between  ?1 and  ?2")
    List<Car> findCarByWeek(Integer min, Integer Max);
    @Query("select e from Car e where e.monthly_price between  ?1 and  ?2")
    List<Car> findCarByMonth(Integer min, Integer Max);

}
