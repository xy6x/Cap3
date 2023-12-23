package com.example.rentingcars.Repository;

import com.example.rentingcars.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee findEmployeeById(Integer id);

    @Query("select sum(e.countOfCarsInspected) from Employee e")
    Employee findEmployeeByCon(Integer id );
}
