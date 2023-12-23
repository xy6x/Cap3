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
            throw new ApiException("car id or user id not found");
        }
        if (car.getCurrentStatus().equals("Available")) {
            if (rentalDTO.getDur().equals("hour")) {
                tot= car.getHourly_price() * rentalDTO.getDuration();

                if (user.getBalance() >= tot) {
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    user.setBalance(user.getBalance() - tot);
                    userRepository.save(user);
                }else throw new ApiException("brice not hour ");
            }


            if (rentalDTO.getDur().equals("day")) {
                tot = car.getDaily_price() * rentalDTO.getDuration();

                if (user.getBalance() >= tot) {
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    user.setBalance(user.getBalance() - tot);
                    userRepository.save(user);


                } else throw new ApiException("brice not the day ");

            }


            if (rentalDTO.getDur().equals("week")) {
                tot = car.getWeekly_price() * rentalDTO.getDuration();


                if (user.getBalance() >= tot) {
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    user.setBalance(user.getBalance() - tot);
                    userRepository.save(user);

                } else throw new ApiException("brice not the week ");
            }

            if (rentalDTO.getDur().equals("Month")) {
                tot = car.getMonthly_price() * rentalDTO.getDuration();

                if (user.getBalance() >= tot) {
                    car.setCurrentStatus("Rented");
                    car.setAuthorized(rentalDTO.getCar_id());
                    user.setBalance(user.getBalance() - tot);
                    userRepository.save(user);

                } else throw new ApiException("brice not the month ");
            }

        } else throw new ApiException("the car  not Available ");


        Rental rental = new Rental(null, rentalDTO.getDuration(), rentalDTO.getDur(), tot, user, car);
        rentalRepository.save(rental);

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
    public void deleteRental(Integer rent_id,Integer user_id){
        Rental rental =rentalRepository.findRentalById(rent_id);
        if (rental == null) {
            throw new ApiException("the Rental not found");
        }
        if (rental.getUser().getId().equals(user_id)) {
            rentalRepository.delete(rental);
        }else throw new ApiException("you are not the owner of the bill");

    }

    public String rentalDetails(Integer rental_id){
        Rental rental =rentalRepository.findRentalById(rental_id);

        if (rental == null) {
            throw new ApiException("rental id not Found");
        }
        return "Total price: " + rental.getTotal_price() + " Duration: " + rental.getDuration();
    }


    public void delivery(Integer rental_id, Integer car_id) {
        Rental rental =rentalRepository.findRentalById(rental_id);

        if (rental == null) {
            throw new ApiException("the rental id not Found");
        }
        Car car = carRepository.findCarById(car_id);
        if (car == null) {
            throw new ApiException("the Car id not Found");
        }
        if (car.getId().equals(car_id) && rental.getId().equals(rental_id)) {
            car.setCurrentStatus("Refundable");
            car.setAuthorized(null);
            carRepository.save(car);
        }

    }

    public Rental rentalDetailss(Integer id){
        Rental rental =rentalRepository.findRentalById(id);

        if (rental == null) {
            throw new ApiException("the rental id not Found");
        }
        return rental;
    }

//    public List<Supplier> getAllCarSameSupplier(Integer id_Supplier){
//        List<Supplier> cars = new ArrayList<>();
//        Supplier supplier =supplierRepository.findSupplierById(id_Supplier);
//
//        if(supplier==null){
//            throw new ApiException("supplier id not found");
//        }
//        for (int i = 0; i <getAllSupplier().size() ; i++) {
//            if (getAllSupplier().get(i).getId().equals(id_Supplier)) {
//                cars.add(getAllSupplier().get(i));
//
//            }
//        }
//        return cars;
//    }

    public List<Rental> rentalsHistoryForUser(Integer user_id){
        List<Rental> rentals=new ArrayList<>();
        User user = userRepository.findUserById(user_id);
        if(user==null){
            throw new ApiException("user id not found");
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
            throw  new ApiException("the rental id  not found ");
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
            throw  new ApiException("the rental id  not found ");
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






