package com.example.rentingcars.Repository;


import com.example.rentingcars.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Integer> {

    Rental findRentalById(Integer id);
}
