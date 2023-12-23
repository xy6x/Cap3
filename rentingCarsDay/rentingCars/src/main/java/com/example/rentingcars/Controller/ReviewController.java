package com.example.rentingcars.Controller;


import com.example.rentingcars.DTO.ReviewDTO;
import com.example.rentingcars.Service.ReviewService;
import org.springframework.stereotype.Controller;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        @PutMapping("/put/{rev_id}/{user_id}")
        public  ResponseEntity updateReview(@PathVariable Integer rev_id, @PathVariable Integer user_id,@RequestBody @Valid ReviewDTO reviewDTO){
            reviewService.updateReview(rev_id, user_id, reviewDTO);
            return ResponseEntity.status(200).body(" update Review ");
        }
        @DeleteMapping("delete/{rev_id}/{user_id}")
        public  ResponseEntity deleteReview(@PathVariable Integer rev_id, @PathVariable Integer user_id ){
            reviewService.deleteReview(rev_id, user_id);
            return ResponseEntity.status(200).body("delete Review");
        }


//        @GetMapping("/get/{id}")
//        public ResponseEntity getAverageRatingForMovie(@PathVariable Integer id){
//            return ResponseEntity.status(200).body(reviewService.getAverageRatingForMovie(id));
//        }



        @GetMapping("/findAllByFeedback/{c}")
        public ResponseEntity findAllByFeedback(@PathVariable String c){
            return ResponseEntity.status(200).body(reviewService.findAllByFeedback(c));
        }

    }

