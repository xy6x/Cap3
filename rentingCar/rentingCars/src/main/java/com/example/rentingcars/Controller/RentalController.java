package com.example.rentingcars.Controller;

import com.example.rentingcars.Model.Rental;
import com.example.rentingcars.Service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rental")
public class RentalController {
    private final RentalService rentalService;
    @GetMapping("/get")
    public ResponseEntity getAllRental(){
        return ResponseEntity.status(HttpStatus.OK).body(rentalService.getAllRental());
    }
    @PostMapping("/add")
    public ResponseEntity addRental(@RequestBody @Valid Rental rental){
        rentalService.addRenTal(rental);
        return ResponseEntity.status(HttpStatus.OK).body("added Rental");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity updateRental(@PathVariable Integer id ,@RequestBody @Valid Rental rental){
        rentalService.updateRental(id, rental);
        return ResponseEntity.status(HttpStatus.OK).body("update Rental");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRental(@PathVariable Integer id){
        rentalService.deleteRental(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Rental");

    }
    @PutMapping("/put/{supplier_id}/{user_id}/{car_id}/{num}")
        public ResponseEntity rent(@PathVariable Integer supplier_id,@PathVariable Integer user_id,@PathVariable Integer car_id,@PathVariable Integer num){
        rentalService.rental(supplier_id,user_id,car_id,num);
        return ResponseEntity.status(HttpStatus.OK).body("rental");

    }
}
