package com.example.rentingcars.Repository;


import com.example.rentingcars.Model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental,Integer> {

    Rental findRentalById(Integer id);

    @Query("select c from Rental c where c.user.id=?1 and c.car.id=?2")
    Rental findRentalByUserIdAAndCarId(Integer user_id, Integer cra_id);
}
