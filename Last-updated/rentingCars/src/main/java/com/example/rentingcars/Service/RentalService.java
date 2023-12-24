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

import java.util.ArrayList;
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
        double tot=0;
        Car car = carRepository.findCarById(rentalDTO.getCar_id());
        User user = userRepository.findUserById(rentalDTO.getUser_id());
        if (car == null || user == null) {
            throw new ApiException("car id or user id not found");}
        if (car.getCurrentStatus().equals("Available") && car.getIsChecked().equals(true) && car.getConditions().equals("Intact")) {
            if (rentalDTO.getDur().equals("hour")) {
                tot= car.getHourly_price() * rentalDTO.getDuration();
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                car.setCount(car.getCount()+1);
                carRepository.save(car);
                    userRepository.save(user);}
            if (rentalDTO.getDur().equals("day")) {
                tot = car.getDaily_price() * rentalDTO.getDuration();
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                car.setCount(car.getCount()+1);
                carRepository.save(car);
                    userRepository.save(user);
            }
            if (rentalDTO.getDur().equals("week")) {
                tot = car.getWeekly_price() * rentalDTO.getDuration();
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                car.setCount(car.getCount()+1);
                carRepository.save(car);
                    userRepository.save(user);
            }
            if (rentalDTO.getDur().equals("Month")) {
                tot = car.getMonthly_price() * rentalDTO.getDuration();
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    car.setCount(car.getCount()+1);
                    carRepository.save(car);
                    userRepository.save(user);
            }

        } else throw new ApiException("the car not Available or car not checked ");
        Rental rental = new Rental(null, rentalDTO.getDuration(), rentalDTO.getDur(), tot, user, car);
        rentalRepository.save(rental);

    }
    public void updateRental(Integer id,RentalDTO rentalDTO){
        Rental oldRental =rentalRepository.findRentalById(id);
        if (oldRental == null) {
            throw new ApiException("Rental ID incorrect");
        }

        oldRental.setDuration(rentalDTO.getDuration());
        oldRental.setTotal_price(rentalDTO.getTotal_price());
        rentalRepository.save(oldRental);
    }
    public void deleteRental(Integer id){
        Rental rental =rentalRepository.findRentalById(id);
        if (rental == null) {
            throw new ApiException("Rental ID incorrect");
        }
        rentalRepository.delete(rental);
    }

    public String rentalInvoice(Integer rental_id){
        Rental rental =rentalRepository.findRentalById(rental_id);

        if (rental == null) {
            throw new ApiException("Rental ID incorrect");
        }
        return "Invoice details " +'\n'+ "User Name: " + rental.getUser().getName() +'\n'+
                "Location: " + rental.getCar().getLocation()  +'\n'+ "Car model " +
                rental.getCar().getModel()  +'\n'+  "Total price: " + rental.getTotal_price()
                + '\n' + "Duration: " + rental.getDuration() + " " + rental.getDur() ;
    }


    public void returnCar(Integer rental_id, Integer car_id) {
        Rental rental =rentalRepository.findRentalById(rental_id);

        if (rental == null) {
            throw new ApiException("Rental ID incorrect");
        }
        Car car = carRepository.findCarById(car_id);
        if (car == null) {
            throw new ApiException("Car ID incorrect");
        }
        if (car.getId().equals(car_id) && rental.getId().equals(rental_id)) {
            car.setCurrentStatus("Refundable");
            car.setAuthorized(null);
            carRepository.save(car);
        }

    }

    public Rental rentalDetails(Integer id){
        Rental rental =rentalRepository.findRentalById(id);

        if (rental == null) {
            throw new ApiException("Rental ID incorrect");
        }
        return rental;
    }



    public List<Rental> rentalsHistoryForUser(Integer user_id){
        List<Rental> rentals=new ArrayList<>();
        User user = userRepository.findUserById(user_id);
        if(user==null){
            throw new ApiException("user ID incorrect");
        }
        for (int i = 0; i <getAllRental().size() ; i++) {
            if(getAllRental().get(i).getUser().getId().equals(user_id)){
                rentals.add(getAllRental().get(i));
            }
        }
        return rentals;
    }


    public void  defectCar(Integer rental_id ,Integer car_id,String location, String conditions){
        Rental rental =rentalRepository.findRentalById(rental_id);
        if (rental == null) {
            throw  new ApiException("Rental ID incorrect");
        }
        if (rental.getCar().getId().equals(car_id)) {
            Car oldCar =carRepository.findCarById(car_id);
            oldCar.setLocation(location);
            oldCar.setConditions(conditions);
            carRepository.save(oldCar);
        }
    }


    public  void  accidentCar(Integer rental_id ,Integer car_id,String location, String conditions, Integer newCar_id){
        Rental rental =rentalRepository.findRentalById(rental_id);
        if (rental == null) {
            throw  new ApiException("Rental ID incorrect");
        }

        if (rental.getCar().getId().equals(car_id)) {
            Car oldCar =carRepository.findCarById(car_id);
            oldCar.setLocation(location);
            oldCar.setConditions(conditions);
            oldCar.setCurrentStatus("Refundable");
            carRepository.save(oldCar);

            Car newCar=carRepository.findCarById(newCar_id);

            newCar.setAuthorized(rental.getUser().getId());
            newCar.setCurrentStatus("Rented");
            carRepository.save(newCar);
            rental.setCar(newCar);
            rentalRepository.save(rental);
        }
    }

    }






