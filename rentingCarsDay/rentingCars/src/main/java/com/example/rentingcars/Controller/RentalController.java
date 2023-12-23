package com.example.rentingcars.Controller;

import com.example.rentingcars.DTO.RentalDTO;
import com.example.rentingcars.Model.Rental;
import com.example.rentingcars.Service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @DeleteMapping("/delete/{rent_id}/{user_id}")
    public ResponseEntity deleteRental(@PathVariable Integer rent_id,@PathVariable Integer user_id){
        rentalService.deleteRental(rent_id, user_id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Rental");

    }

    @PutMapping("/ren/{renetal_id}/{car_id}")
    public ResponseEntity delivery(@PathVariable Integer renetal_id,@PathVariable Integer car_id ) {
        rentalService.delivery(renetal_id, car_id);
        return ResponseEntity.status(HttpStatus.OK).body("rental");
    }

    @GetMapping("/Details/{id}")
    public ResponseEntity rentalDetails(@PathVariable Integer id){

        String rentalDetails = rentalService.rentalDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(rentalDetails);
    }

    @GetMapping("/Detailss/{id}")
    public ResponseEntity rentalDetailss(@PathVariable Integer id){

        Rental rentalDetails = rentalService.rentalDetailss(id);
        return ResponseEntity.status(HttpStatus.OK).body(rentalDetails);
    }

    @GetMapping("/rentalsHistoryForUser/{user_id}")
    public ResponseEntity rentalsHistoryForUser(@PathVariable Integer user_id){

        List<Rental> rentals = rentalService.rentalsHistoryForUser(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }
    @PutMapping("defCar/{rental_id}/{car_id}/{location}/{conditions}")
    public ResponseEntity def_Car(@PathVariable Integer rental_id ,@PathVariable Integer car_id,@PathVariable String location,@PathVariable String conditions){
        rentalService.defectCar(rental_id, car_id, location, conditions);
        return ResponseEntity.status(HttpStatus.OK).body("rental");
    }
    @PutMapping("accident/{rental_id}/{car_id}/{location}/{conditions}/{newCar_id}")
    public ResponseEntity accidentCar(@PathVariable Integer rental_id ,@PathVariable Integer car_id,@PathVariable String location,@PathVariable String conditions,@PathVariable Integer newCar_id){
        rentalService.accidentCar(rental_id, car_id, location, conditions, newCar_id);
        return ResponseEntity.status(HttpStatus.OK).body("don");
    }
//
//    @PutMapping("/put/{R}/{user_id}/{car_id}")
//    public ResponseEntity rent(@PathVariable Integer R,@PathVariable Integer user_id,@PathVariable Integer car_id ){
//        rentalService.rental(R,user_id,car_id);
//        return ResponseEntity.status(HttpStatus.OK).body("rental");
//
//    }
}
