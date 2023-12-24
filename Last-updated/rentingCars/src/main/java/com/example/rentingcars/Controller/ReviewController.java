package com.example.rentingcars.Controller;


import com.example.rentingcars.DTO.ReviewDTO;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Service.ReviewService;
import org.springframework.stereotype.Controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/review")
@AllArgsConstructor
public class ReviewController {




        private final ReviewService reviewService;
        @GetMapping("/get")
        public ResponseEntity getAllAddress(){
            return ResponseEntity.status(200).body(reviewService.getALLReview());
        }


        @PostMapping("/add")
        public ResponseEntity addReview(@RequestBody @Valid ReviewDTO reviewDTO){
            reviewService.addReview(reviewDTO);
            return ResponseEntity.status(200).body("Feedback submitted successfully");

        }


        @PutMapping("/update/{id}/{user_id}")
        public ResponseEntity updateReview(@PathVariable Integer id, @PathVariable Integer user_id, @RequestBody @Valid ReviewDTO reviewDTO){
            reviewService.updateReview(id,user_id,reviewDTO);
            return ResponseEntity.status(200).body("Review updated");

        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity deleteReview(@PathVariable Integer id){
            reviewService.deleteReview(id);
            return ResponseEntity.status(200).body("Review deleted");
        }

        @GetMapping("/findAllByFeedback/{c}")
        public ResponseEntity findAllByFeedback(@PathVariable String c){
            List<Car> cars = reviewService.findAllByFeedback(c);
            return ResponseEntity.status(200).body(cars);
        }

    }

