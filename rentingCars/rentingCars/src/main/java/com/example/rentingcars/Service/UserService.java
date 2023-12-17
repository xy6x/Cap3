package com.example.rentingcars.Service;

import com.example.rentingcars.ApiException.ApiException;
import com.example.rentingcars.Model.User;
import com.example.rentingcars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class UserService {
    private final UserRepository userRepository;
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
        oldUser.setRole(user.getRole());
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
}
