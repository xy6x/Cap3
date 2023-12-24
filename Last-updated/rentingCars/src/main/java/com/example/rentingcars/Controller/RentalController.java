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
        return ResponseEntity.status(HttpStatus.OK).body("Car rental has been successfully ");
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
//استرجاع السيارة
    @PutMapping("/returnCar/{renetal_id}/{car_id}")
    public ResponseEntity returnCar(@PathVariable Integer renetal_id,@PathVariable Integer car_id ) {
        rentalService.returnCar(renetal_id, car_id);
        return ResponseEntity.status(HttpStatus.OK).body("Return car");
    }

    @GetMapping("/Invoice/{id}")
    public ResponseEntity rentalInvoice(@PathVariable Integer id){

        String rentalInvoice = rentalService.rentalInvoice(id);
        return ResponseEntity.status(HttpStatus.OK).body(rentalInvoice);
    }

    @GetMapping("/Details/{id}")
    public ResponseEntity rentalDetails(@PathVariable Integer id){

        Rental rentalDetails = rentalService.rentalDetails(id);
        return ResponseEntity.status(HttpStatus.OK).body(rentalDetails);
    }

    @GetMapping("/rentalsHistoryForUser/{user_id}")
    public ResponseEntity rentalsHistoryForUser(@PathVariable Integer user_id){

        List<Rental> rentals = rentalService.rentalsHistoryForUser(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }

    @PutMapping("/defectCar/{rental_id}/{car_id}/{location}/{conditions}")
public ResponseEntity defectCar(@PathVariable Integer rental_id ,@PathVariable Integer car_id, @PathVariable String location, @PathVariable String conditions){

        rentalService.defectCar(rental_id,car_id,location,conditions);
        return ResponseEntity.status(200).body("Done");

}
    @PutMapping("/accidentCar/{rental_id}/{car_id}/{location}/{conditions}/{newCare_id}")
    public ResponseEntity accidentCar(@PathVariable Integer rental_id ,@PathVariable Integer car_id, @PathVariable String location, @PathVariable String conditions,@PathVariable Integer newCare_id){
        rentalService.accidentCar(rental_id,car_id,location,conditions,newCare_id);
        return ResponseEntity.status(200).body("Done");

    }

}
