package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Model.Rental;
import com.example.rentingcars.Model.Supplier;
import com.example.rentingcars.Model.User;
import com.example.rentingcars.Repository.CarRepository;
import com.example.rentingcars.Repository.RentalRepository;
import com.example.rentingcars.Repository.SupplierRepository;
import com.example.rentingcars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final SupplierRepository supplierRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

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
    public void rental(Integer supplier_id,Integer user_id,Integer car_id,Integer num) {
        Supplier supplier = supplierRepository.findSupplierById(supplier_id);
        if (supplier == null) {
            throw new ApiException("the Supplier not found");
        }
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("the User not found");
        }
        Car car = carRepository.findCarById(car_id);
        if (car == null) {
            throw new ApiException("the Car not found");
        }

        if (user.getId().equals(user_id)) {
            if (car.getDur().equals("day")) {
                if (car.getCurrentStatus().equals("Available")) {
                    double total = car.getDaily_price() * num;
                    if (user.getBrice() >= total) {
                        car.setCurrentStatus("Rented");
                        car.setAuthorized(user_id);
                    }

            }

            }
            if (car.getDur().equals("week")) {
                if (car.getCurrentStatus().equals("Available")) {
                    double total = car.getWeekly_price() * num;
                    if (user.getBrice() >= total) {
                        car.setCurrentStatus("Rented");
                        car.setAuthorized(user_id);
                    }

                }

            }
            if (car.getDur().equals("Month")) {
                if (car.getCurrentStatus().equals("Available")) {
                    double total = car.getMonthly_price() * num;
                    if (user.getBrice() >= total) {
                        car.setCurrentStatus("Rented");
                        car.setAuthorized(user_id);
                    }

                }

            }
        }
    }
}

