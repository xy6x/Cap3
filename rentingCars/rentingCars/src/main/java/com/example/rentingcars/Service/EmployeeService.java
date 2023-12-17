package com.example.rentingcars.Service;

import com.example.rentingcars.ApiException.ApiException;
import com.example.rentingcars.Model.Employee;
import com.example.rentingcars.Repository.EmployeeRepository;
import com.example.rentingcars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
        Boolean tcheck =false;
        employee.setIsApproved(tcheck);
        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer id, Employee employee) {
        Employee oldEmployee =employeeRepository.findEmployeeById(id);
        if (oldEmployee == null) {
            throw new ApiException("the Employee not found");
        }
        oldEmployee.setName(employee.getName());
        oldEmployee.setAge(employee.getAge());
        oldEmployee.setCountOfCarsInspected(employee.getCountOfCarsInspected());
        oldEmployee.setQualification(employee.getQualification());
        oldEmployee.setBonus(employee.getBonus());
        employeeRepository.save(oldEmployee);

    }
    public void deleteEmployee(Integer id){
        Employee employee =employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("the Employee not found");
        }
        employeeRepository.delete(employee);
    }
}
