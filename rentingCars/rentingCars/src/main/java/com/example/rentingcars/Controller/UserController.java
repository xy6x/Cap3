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
}
