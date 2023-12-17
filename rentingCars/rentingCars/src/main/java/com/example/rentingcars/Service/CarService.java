package com.example.rentingcars.Service;

import com.example.rentingcars.ApiException.ApiException;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    public List<Car> getAllCar(){
        return carRepository.findAll();
    }
    public void addCar(Car car){
        carRepository.save(car);
    }
    public void updateCar(Integer id,Car car){
        Car oldCar =carRepository.findCarById(id);
        if (oldCar == null) {
            throw new ApiException("the id of car not found ");
        }
        oldCar.setType(car.getType());
        oldCar.setBrand(car.getBrand());
        oldCar.setColor(car.getColor());
        oldCar.setYear(car.getYear());
        oldCar.setModel(car.getModel());
        oldCar.setDaily_price(car.getDaily_price());
        oldCar.setHourly_price(car.getHourly_price());
        oldCar.setHourly_price(car.getHourly_price());
        oldCar.setWeekly_price(car.getWeekly_price());
        oldCar.setCurrentStatus(car.getCurrentStatus());
        oldCar.setIsChecked(car.getIsChecked());
        carRepository.save(oldCar);
    }
    public void deleteCar(Integer id){
        Car car =carRepository.findCarById(id);
        if (car == null) {
            throw new ApiException("the id of car not found ");

        }
        carRepository.delete(car);
    }
}
