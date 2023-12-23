package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.DTO.CarDTO;
import com.example.rentingcars.Model.*;
import com.example.rentingcars.Repository.*;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final RentalRepository rentalRepository;
    private final SupplierRepository supplierRepository;

private final ReviewRepository reviewRepository;
    public List<Car> getAllCar() {

        return carRepository.findAll();

    }

    public void addCar(CarDTO carDTO) {
        Supplier supplier = supplierRepository.findSupplierById(carDTO.getSupplier_id());
        if (supplier == null) {
            throw new ApiException("id supplier not found");
        }

        Car car = new Car(null, carDTO.getType(), carDTO.getBrand(), carDTO.getColor(), carDTO.getModel(), carDTO.getLocation(),
                carDTO.getDaily_price(), carDTO.getHourly_price(), carDTO.getMonthly_price(), carDTO.getWeekly_price(), carDTO.getAuthorized(),
                carDTO.getCurrentStatus(), carDTO.getConditions(), carDTO.getIsChecked(), carDTO.getCount(),carDTO.getLocalDate(),0, supplier,null, null, null, null);
        carRepository.save(car);
    }

    public void updateCar(Integer id, CarDTO carDTO) {
        Car oldCar = carRepository.findCarById(id);
        if (oldCar == null) {
            throw new ApiException("the id of car not found ");
        }

        oldCar.setType(carDTO.getType());
        oldCar.setBrand(carDTO.getBrand());
        oldCar.setColor(carDTO.getColor());
        oldCar.setModel(carDTO.getModel());
        oldCar.setDaily_price(carDTO.getDaily_price());
        oldCar.setHourly_price(carDTO.getHourly_price());
        oldCar.setHourly_price(carDTO.getHourly_price());
        oldCar.setWeekly_price(carDTO.getWeekly_price());
        oldCar.setCurrentStatus(carDTO.getCurrentStatus());
        oldCar.setConditions(carDTO.getConditions());
        oldCar.setIsChecked(carDTO.getIsChecked());
        oldCar.setCount(carDTO.getCount());
        carRepository.save(oldCar);
    }


    public void deleteCar(Integer car_id,Integer sup_id) {
        Car deletCar = carRepository.findCarById(car_id);
        Supplier supplier=supplierRepository.findSupplierById(sup_id);
        if (deletCar == null) {
            throw new ApiException("the id of car not found ");
        }
        if (deletCar.getSupplier().getId().equals(sup_id)) {


            supplier.getCars().remove(deletCar);
            carRepository.delete(deletCar);
        }else throw new ApiException("you are not the owner");

    }


    public List<Car> getRentableCars() {
        List<Car> cars = carRepository.getRentableCars();
        if (cars == null) {
            throw new ApiException("no cars available and Checked same time");
        }
        return cars;
    }

    public Car getCarDetails(Integer id){

        Car car=carRepository.findCarById(id);
        if(car==null){
            throw new ApiException("no car");
        }
        return car;
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


//    public void assignCarToRental(Integer car_id, Integer rental_id) {
//        Car car = carRepository.findCarById(car_id);
//        Rental rental = rentalRepository.findRentalById(rental_id);
//
//        if (car == null || rental == null) {
//            throw new ApiException("car or rental not found");
//        }
//        rental.setCar(car);
//        rentalRepository.save(rental);
//    }

    public void carCheck(Integer car_id, Integer employee_id) {
        Car car = carRepository.findCarById(car_id);
        Employee employee = employeeRepository.findEmployeeById(employee_id);

        if (car == null || employee == null) {
            throw new ApiException("car id or employee id incorrect");
        }
        if (employee.getIsApproved().equals(true)) {
            car.setIsChecked(true);
            employee.setCountOfCarsInspected(employee.getCountOfCarsInspected() + 1);
            carRepository.save(car);
            employeeRepository.save(employee);
        }else throw new ApiException("the employee not Approved");

    }




    public void getAverageRatingForCar(Integer car_id) {
        Car car = carRepository.findCarById(car_id);
        List<Review> reviews = reviewRepository.findAllByCarId(car_id);

        if(car == null){
            throw new ApiException("id not found");
        }
        double totalRating = reviews.stream().mapToInt(Review::getRating).sum();
        car.setRating(totalRating / reviews.size());
        carRepository.save(car);
    }
    public List<Car> findAllByOrderByRating(){

        List<Car> cars = carRepository.findAllByOrderByRatingDesc();
        if(cars.isEmpty()){
            throw new ApiException("no cars");
        }
        return cars;
    }


    public Car mostRented() {
        List<Car> cars=carRepository.findAll();
        if(cars.isEmpty()){
            throw new ApiException("no cars");
        }
        Car car = cars.get(0);
         for(int i = 0; i < cars.size(); i++) {
            if(cars.get(i).getCount()>car.getCount()){
                car=cars.get(i);

            }
        }
         return car;
    }

    public List<Car> discount(){
        List<Car> cars =carRepository.findAll();
        if(cars.isEmpty()){
            throw new ApiException("no cars");
        }
        List<Car> cars1 = new ArrayList<>();
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).getCount()==0){
                cars.get(i).setDaily_price(cars.get(i).getDaily_price()*.90);
                cars.get(i).setHourly_price(cars.get(i).getHourly_price()*.90);
                cars.get(i).setWeekly_price(cars.get(i).getWeekly_price()*.90);
                cars.get(i).setMonthly_price(cars.get(i).getMonthly_price()*.90);
                cars1.add(cars.get(i));
                carRepository.saveAll(cars);
            }
        }
               return cars1;
    }


    public List<Car> briceCar(Integer min , Integer max ,String duration){

        if (duration.equals("day")) {
            List<Car> cars =carRepository.findCarByDay(min,max);
            return cars;
        }else if (duration.equals("week")) {
            List<Car> carWeek =carRepository.findCarByWeek(min,max);
            return carWeek;

        }
        List<Car> carMonth =carRepository.findCarByMonth(min,max);
        return carMonth;

    }


    }






