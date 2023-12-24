package com.example.rentingcars.Controller;

import com.example.rentingcars.Model.User;
import com.example.rentingcars.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUser());
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user){
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("added User");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody @Valid User user){
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body("update User");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete User");
    }


//
//    @PutMapping("/{user_id}/assign/{car_id}")
//    public ResponseEntity assignUserToCar(@PathVariable Integer user_id, @PathVariable Integer car_id){
//        userService.assignUserToCar(user_id,car_id);
//        return ResponseEntity.status(200).body("DONE");
//
//    }
//
//    @PutMapping("/{user_id}/assignUserToRental/{rental_id}")
//    public ResponseEntity assignUserToRental(@PathVariable Integer user_id, @PathVariable Integer rental_id){
//        userService.assignUserToRental(user_id,rental_id);
//        return ResponseEntity.status(200).body("DONE");
//
//    }


    @GetMapping("/login/{username}/{password}")
    private ResponseEntity logIn(@PathVariable String username, @PathVariable String password){
        String logIn = userService.logIn(username,password);
        return ResponseEntity.status(200).body(logIn);
    }

}