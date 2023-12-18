package com.example.rentingcars.Repository;


import com.example.rentingcars.Model.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<Date,Integer> {

    Date findDateById(Integer id);
}
