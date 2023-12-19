package com.example.rentingcars.Controller;

import com.example.rentingcars.DTO.RentalDTO;
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
    public ResponseEntity addRental(@RequestBody @Valid RentalDTO rentalDTO){
        rentalService.addRenTal(rentalDTO);
        return ResponseEntity.status(HttpStatus.OK).body("added Rental");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity updateRental(@PathVariable Integer id ,@RequestBody @Valid RentalDTO rentalDTO){
        rentalService.updateRental(id, rentalDTO);
        return ResponseEntity.status(HttpStatus.OK).body("update Rental");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRental(@PathVariable Integer id){
        rentalService.deleteRental(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Rental");

    }
//
//    @PutMapping("/put/{R}/{user_id}/{car_id}")
//    public ResponseEntity rent(@PathVariable Integer R,@PathVariable Integer user_id,@PathVariable Integer car_id ){
//        rentalService.rental(R,user_id,car_id);
//        return ResponseEntity.status(HttpStatus.OK).body("rental");
//
//    }
}
