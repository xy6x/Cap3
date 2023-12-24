package com.example.rentingcars.Repository;


import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    Review findReviewById(Integer id);

    List<Review> findAllByCarId(Integer id);


}

