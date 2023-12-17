package com.example.rentingcars.Service;

import com.example.rentingcars.ApiException.ApiException;
import com.example.rentingcars.Model.Rental;
import com.example.rentingcars.Repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    public List<Rental> getAllRental(){
        return  rentalRepository.findAll();
    }
    public void addRenTal(Rental rental){
        rentalRepository.save(rental);
    }
    public void updateRental(Integer id,Rental rental){
        Rental oldRental =rentalRepository.findRentalById(id);
        if (oldRental == null) {
            throw new ApiException("the Rental not found");
        }
        oldRental.setDuration(rental.getDuration());
        oldRental.setTotal_price(rental.getTotal_price());
        rentalRepository.save(oldRental);
    }
    public void deleteRental(Integer id){
        Rental rental =rentalRepository.findRentalById(id);
        if (rental == null) {
            throw new ApiException("the Rental not found");
        }
        rentalRepository.delete(rental);
    }
}
