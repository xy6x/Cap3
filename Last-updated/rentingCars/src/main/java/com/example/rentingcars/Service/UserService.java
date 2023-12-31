package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Model.Rental;
import com.example.rentingcars.Model.Supplier;
import com.example.rentingcars.Model.User;
import com.example.rentingcars.Repository.CarRepository;
import com.example.rentingcars.Repository.RentalRepository;
import com.example.rentingcars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final RentalRepository rentalRepository;


    public List<User> getAllUser(){

        return userRepository.findAll();
    }
    public void addUser(User user){

        userRepository.save(user);
    }
    public void updateUser(Integer id,User user){
        User oldUser =userRepository.findUserById(id);
        if (oldUser == null) {
            throw new ApiException("the id user not found");
        }

        oldUser.setName(user.getName());
        oldUser.setPhone_number(user.getPhone_number());
        oldUser.setAge(user.getAge());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setLicense(user.getLicense());
        userRepository.save(oldUser);
    }
    public void deleteUser(Integer id){
        User user =userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("the id user not found");
        }
        userRepository.delete(user);
    }


    public String logIn(String username, String password){
        User user = userRepository.logIn(username,password);
        if(user==null){
            throw  new ApiException("username or password is incorrect");
        }
        return "Login successfully";
    }
}
