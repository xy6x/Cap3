package com.example.rentingcars.Repository;

import com.example.rentingcars.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    Employee findEmployeeById(Integer id);

}
