package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.DTO.RentalDTO;
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


    public void addRenTal(RentalDTO rentalDTO) {

        Car car = carRepository.findCarById(rentalDTO.getCar_id());
        User user = userRepository.findUserById(rentalDTO.getUser_id());
        if (car == null || user == null) {
            throw new ApiException("car id or user id not found");
        }
        Rental rental = new Rental(null, rentalDTO.getDuration(), rentalDTO.getDur(), rentalDTO.getTotal_price(), user, car);
        rentalRepository.save(rental);
        if (car.getCurrentStatus().equals("Available")) {
            if (rentalDTO.getDur().equals("hour")) {
                double num = car.getHourly_price() * rentalDTO.getDuration();
                rental.setTotal_price(num);
                rentalRepository.save(rental);

                if (user.getBrice() >= num) {
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    user.setBrice(user.getBrice() - num);
                    userRepository.save(user);

                } else throw new ApiException("brice not hour ");
            }
        }
        if (car.getCurrentStatus().equals("Available")) {
            if (rentalDTO.getDur().equals("day")) {
                double total = car.getDaily_price() * rentalDTO.getDuration();
                rental.setTotal_price(total);
                rentalRepository.save(rental);

                if (user.getBrice() >= total) {
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    user.setBrice(user.getBrice() - total);
                    userRepository.save(user);

                } else throw new ApiException("brice not the day ");
            }
        }
        if (car.getCurrentStatus().equals("Available")) {
            if (rentalDTO.getDur().equals("week")) {
                double tota = car.getWeekly_price() * rentalDTO.getDuration();
                rental.setTotal_price(tota);
                rentalRepository.save(rental);

                if (user.getBrice() >= tota) {
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    user.setBrice(user.getBrice() - tota);
                    userRepository.save(user);


                } else throw new ApiException("brice not the week ");
            }
        }

        if (car.getCurrentStatus().equals("Available")) {
            if (rentalDTO.getDur().equals("Month")) {
                double tot = car.getMonthly_price() * rentalDTO.getDuration();
                rental.setTotal_price(tot);
                rentalRepository.save(rental);

                if (user.getBrice() >= tot) {
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    user.setBrice(user.getBrice() - tot);
                    userRepository.save(user);

                } else throw new ApiException("brice not the month ");
            }


        }
        else throw new ApiException("the car  not Available ");
    }









    public void updateRental(Integer id,RentalDTO rentalDTO){
        Rental oldRental =rentalRepository.findRentalById(id);
        if (oldRental == null) {
            throw new ApiException("the Rental not found");
        }

        oldRental.setDuration(rentalDTO.getDuration());
        oldRental.setTotal_price(rentalDTO.getTotal_price());
        rentalRepository.save(oldRental);
    }
    public void deleteRental(Integer id){
        Rental rental =rentalRepository.findRentalById(id);
        if (rental == null) {
            throw new ApiException("the Rental not found");
        }
        rentalRepository.delete(rental);
    }

    public void delivery(Integer car_id,Integer user_id) {
        User user = userRepository.findUserById(car_id);
        if (user == null) {
            throw new ApiException("the User id not Found");
        }
        Car car = carRepository.findCarById(car_id);

        if (car == null) {
            throw new ApiException("the Car id not Found");
        }
        if (car.getId().equals(car_id) && car.getAuthorized().equals(user_id)) {
            car.setCurrentStatus("Refundable");
            car.setAuthorized(null);

        }
        
    }

    }



