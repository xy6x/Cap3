package com.example.rentingcars.Controller;

import com.example.rentingcars.DTO.CarDTO;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Model.Date;
import com.example.rentingcars.Service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public ResponseEntity addCar(@RequestBody @Valid CarDTO carDTO) {
        carService.addCar(carDTO);
        return ResponseEntity.status(HttpStatus.OK).body("added car");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity UpdateCar(@PathVariable Integer id, @RequestBody @Valid CarDTO carDTO) {
        carService.updateCar(id, carDTO);
        return ResponseEntity.status(HttpStatus.OK).body("update car");
    }

    @DeleteMapping("/delete/{id}/{sup_id}")
    public ResponseEntity deleteCar(@PathVariable Integer id,@PathVariable Integer sup_id) {
        carService.deleteCar(id,sup_id);
        return ResponseEntity.status(HttpStatus.OK).body("delete car");

    }


    @GetMapping("/getCarDetails/{id}")
    public ResponseEntity getCarDetails(@PathVariable Integer id){

        Car car = carService.getCarDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

//    @PutMapping("/{car_id}/assignCarToRental/{rental_id}")
//    public ResponseEntity assignCarToRental(@PathVariable Integer car_id, @PathVariable Integer rental_id){
//        carService.assignCarToRental(car_id,rental_id);
//        return ResponseEntity.status(200).body("DONE");
//
//    }

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

//    @GetMapping("/get/{date}/{dat}")
//    public ResponseEntity returnCar(@PathVariable LocalDate date, @PathVariable LocalDate dat){
//        return ResponseEntity.status(HttpStatus.OK).body(carService.listCar(date, dat));
//
//    }

    @GetMapping("/mostRented")
public ResponseEntity mostRented(){
        Car car = carService.mostRented();
    return ResponseEntity.status(HttpStatus.OK).body(car);
}


@GetMapping("/getRentableCars")
public ResponseEntity getRentableCars(){
    return ResponseEntity.status(HttpStatus.OK).body(carService.getRentableCars());
}

    @GetMapping("/discount")
public ResponseEntity discount(){
    return ResponseEntity.status(HttpStatus.OK).body(carService.discount());

}


@GetMapping("/findAllByOrderByRating")
public ResponseEntity findAllByOrderByRating(){
    return ResponseEntity.status(HttpStatus.OK).body(carService.findAllByOrderByRating());
}
    @GetMapping("/brice/{min}/{max}/{duration}")
    public ResponseEntity briceCar(@PathVariable Integer min ,@PathVariable Integer max ,@PathVariable String duration){
        return ResponseEntity.status(HttpStatus.OK).body(carService.briceCar(min, max, duration));
    }



}
