package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.DTO.ReviewDTO;
import com.example.rentingcars.Model.*;
import com.example.rentingcars.Repository.CarRepository;
import com.example.rentingcars.Repository.RentalRepository;
import com.example.rentingcars.Repository.ReviewRepository;
import com.example.rentingcars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RentalService rentalService;
    private final RentalRepository rentalRepository;
    private final CarService carService;



    public List<Review> getALLReview() {

        return reviewRepository.findAll();
    }





    public void addReview(ReviewDTO reviewDTO) {
        Car car = carRepository.findCarById(reviewDTO.getCar_id());
        User user = userRepository.findUserById(reviewDTO.getUser_id());
        Rental rental = rentalRepository.findRentalByUserIdAAndCarId(reviewDTO.getUser_id(), reviewDTO.getCar_id());
        if (car == null || user == null) {
            throw new ApiException("car id or user id not found");
        }
        if(rental==null){
            throw new ApiException("rental not found");
            }
        Review review = new Review(null, reviewDTO.getFeedback(),reviewDTO.getRating(), user, car);
        reviewRepository.save(review);
        carService.getAverageRatingForCar(reviewDTO.getCar_id());
        carRepository.save(car);
    }
    public void  updateReview(Integer rev_id ,Integer user_id, ReviewDTO reviewDTO){
        Review review= reviewRepository.findReviewById(rev_id);
        if (review == null) {
            throw new ApiException("review not found");
        }
        if (review.getUser().getId().equals(user_id)){
          review.setFeedback(reviewDTO.getFeedback());
          review.setRating(reviewDTO.getRating());
            reviewRepository.save(review);
        }

    }
    public void  deleteReview(Integer rev_id ,Integer user_id){
        Review review =reviewRepository.findReviewById(rev_id);
        if (review.getUser().getId().equals(user_id)) {
            review.setRating(null);
            review.setFeedback(null);
            reviewRepository.delete(review);
        }

        }



        public List<Car> findAllByFeedback(String feedback) {
              List<Car> cars = new ArrayList<>();
            for (int i = 0; i <getALLReview().size() ; i++) {
                if(getALLReview().get(i).getFeedback().contains(feedback)){
                    cars.add(getALLReview().get(i).getCar());
            }
            }
        return cars;
    }


}
