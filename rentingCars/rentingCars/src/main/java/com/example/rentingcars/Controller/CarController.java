package com.example.rentingcars.Controller;

import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car")
public class CarController {
    private final CarService carService;
    @GetMapping("/get")
    public ResponseEntity getAllCar(){
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAllCar());
    }
    @PostMapping("add")
    public ResponseEntity addCar(@RequestBody @Valid Car car){
        carService.addCar(car);
        return ResponseEntity.status(HttpStatus.OK).body("added car");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity UpdateCar(@PathVariable Integer id, @RequestBody @Valid Car car){
        carService.updateCar(id, car);
        return ResponseEntity.status(HttpStatus.OK).body("update car");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCar(@PathVariable Integer id){
        carService.deleteCar(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete car");

    }

}
