package com.example.rentingcars.Controller;

import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car")
public class CarController {
    private final CarService carService;

    @GetMapping("/get")
    public ResponseEntity getAllCar() {

        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCar());
    }

    @PostMapping("/add")
    public ResponseEntity addCar(@RequestBody @Valid Car car) {
        carService.addCar(car);
        return ResponseEntity.status(HttpStatus.OK).body("added car");
    }

    @PutMapping("/put/{id}")
    public ResponseEntity UpdateCar(@PathVariable Integer id, @RequestBody @Valid Car car) {
        carService.updateCar(id, car);
        return ResponseEntity.status(HttpStatus.OK).body("update car");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCar(@PathVariable Integer id) {
        carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete car");

    }

    @GetMapping("/getAllCarsByBrand/{brand}")
    public ResponseEntity getAllCarsByBrand(@PathVariable String brand) {
        List<Car> cars = carService.getAllCarsByBrand(brand);
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }


    @GetMapping("/getAllCarsByColor/{color}")
    public ResponseEntity getAllCarsByColor(@PathVariable String color) {
        List<Car> cars = carService.getAllCarsByColor(color);
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }


    @GetMapping("/getAllCarsByLocation/{location}")
    public ResponseEntity getAllCarsByLocation(@PathVariable String location) {
        List<Car> cars = carService.getAllCarsByLocation(location);
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }



    @GetMapping("/getCurrentStatus/{id}")
    public ResponseEntity getCurrentStatus(@PathVariable Integer id) {
       String currentStatus = carService.currentStatus(id);
        return ResponseEntity.status(HttpStatus.OK).body(currentStatus);
    }


    @GetMapping("/getAllCarsAvailable")
    public ResponseEntity getAllCarsAvailable() {
        List<Car> cars = carService.getAllCarsAvailable();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }


    @GetMapping("/getAllCarsIsChecked")
    public ResponseEntity getAllCarsIsChecked() {
        List<Car> cars = carService.getAllCarsIsChecked();
        return ResponseEntity.status(HttpStatus.OK).body(cars);
    }


    @PutMapping("/carCheck/{car_id}/{employee_id}")
    public ResponseEntity carCheck(@PathVariable  Integer car_id, @PathVariable  Integer employee_id){
        carService.carCheck(car_id,employee_id);
        return ResponseEntity.status(HttpStatus.OK).body("the car has been successfully inspected");
    }
    @GetMapping("/get/{date}/{dat}")
    public ResponseEntity returnCar(@PathVariable Date date,@PathVariable Date dat){
        return ResponseEntity.status(HttpStatus.OK).body(carService.listCar(date, dat));

    }
}
