package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Model.Employee;
import com.example.rentingcars.Repository.CarRepository;
import com.example.rentingcars.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;


    public List<Car> getAllCar() {

        return carRepository.findAll();
    }

    public void addCar(Car car) {

        carRepository.save(car);
        Boolean rew = false;
        car.setIsChecked(rew);
        carRepository.save(car);
    }

    public void updateCar(Integer id, Car car) {
        Car oldCar = carRepository.findCarById(id);
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

    public void deleteCar(Integer id) {
        Car car = carRepository.findCarById(id);
        if (car == null) {
            throw new ApiException("the id of car not found ");

        }
        carRepository.delete(car);
    }


    public List<Car> getAllCarsByBrand(String brand) {

        List<Car> cars = carRepository.findAllByBrand(brand);
        if (cars.isEmpty()) {
            throw new ApiException("this brand is not available");
        }
        return cars;

    }

    public List<Car> getAllCarsByColor(String color) {
        List<Car> cars = carRepository.findAllByColor(color);
        if (cars.isEmpty()) {
            throw new ApiException("this color is not available");
        }
        return cars;

    }

    public List<Car> getAllCarsByLocation(String location) {
        List<Car> cars = carRepository.findAllByLocation(location);
        if (cars.isEmpty()) {
            throw new ApiException("no cars in specified location");
        }
        return cars;
    }

    public String currentStatus(Integer id) {
        Car car = carRepository.findCarById(id);
        if (car == null) {
            throw new ApiException("car id not found");

        }
        return car.getCurrentStatus();
    }

    public List<Car> getAllCarsAvailable() {
        List<Car> cars = carRepository.getAllCarsAvailable();
        if (cars.isEmpty()) {
            throw new ApiException("no cars available now");
        }

        return cars;
    }

    public List<Car> getAllCarsIsChecked() {
        List<Car> cars = carRepository.getAllCarsIsChecked();
        if (cars.isEmpty()) {
            throw new ApiException("no cars checked now");
        }

        return cars;
    }

    public void carCheck(Integer car_id, Integer employee_id) {
        Car car = carRepository.findCarById(car_id);
        Employee employee = employeeRepository.findEmployeeById(employee_id);

        if (car == null || employee == null) {
            throw new ApiException("car id or employee id incorrect");
        }

            if (employee.getQualification().equals("engineer")) {
                car.setIsChecked(true);
                employee.setCountOfCarsInspected(employee.getCountOfCarsInspected() + 1);
                carRepository.save(car);
                employeeRepository.save(employee);
            } else
                throw new ApiException("employee does not conform to conditions");

        }
        public List<Car> listCar(Date date,Date dat){
        List<Car> cars =carRepository.findCarByDay(date, dat);
            if (cars == null) {
                throw new ApiException("the month not add car ");
            }
            return cars;
        }

    }





