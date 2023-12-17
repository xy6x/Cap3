package com.example.rentingcars.Controller;

import com.example.rentingcars.Model.Date;
import com.example.rentingcars.Service.DateService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/date")
public class DateController {
    private final DateService dateService;
    @GetMapping("/get")
    public ResponseEntity getAllDate(){
        return ResponseEntity.status(HttpStatus.OK).body(dateService.getAllDAte());
    }
    @PostMapping("/add")
    public ResponseEntity addDate(@RequestBody @Valid Date date){
        dateService.addDate(date);
        return ResponseEntity.status(HttpStatus.OK).body("added date");

    }
    @PutMapping("put/{id}")
    public ResponseEntity updateDate(@PathVariable Integer id ,@RequestBody @Valid Date date){
        dateService.updateDate(id, date);
        return ResponseEntity.status(HttpStatus.OK).body("update date");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDate(@PathVariable Integer id){
        dateService.deleteDate(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete date");
    }
}
