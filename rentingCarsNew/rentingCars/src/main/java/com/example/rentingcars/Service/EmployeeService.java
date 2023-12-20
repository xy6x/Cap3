package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Model.Employee;
import com.example.rentingcars.Model.Supplier;
import com.example.rentingcars.Model.User;
import com.example.rentingcars.Repository.CarRepository;
import com.example.rentingcars.Repository.EmployeeRepository;
import com.example.rentingcars.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final CarRepository carRepository;
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

    public void assignEmployeeToCar(Integer employee_id, Integer car_id ){
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        Car car = carRepository.findCarById(car_id);

        if(employee==null || car == null){
            throw  new ApiException("employee or car not found");
        }

        car.setEmployee(employee);
        carRepository.save(car);
    }

    public Set<Car> getAllCar(Integer  id){
        Employee employee =employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("the id not found ");
        }
        carRepository.findAll();
        return employee.getCars();
    }


    public void subCar(Integer id){
        Employee employee =employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("the id not found ");
        }
        if (employee.getCountOfCarsInspected()>29){
            employee.setTotalCars(employee.getTotalCars()+employee.getCountOfCarsInspected());
            employee.setBonus(employee.getBonus()+50);
            employee.setCountOfCarsInspected(0);
            employeeRepository.save(employee);
        }
    }

    public void  CechOCar(Integer emp_id ,Integer car_id,String loc,String con){

        Employee employee=employeeRepository.findEmployeeById(emp_id);
        if (employee == null) {
            throw new ApiException("the id employee not found");
        }
        Car car =carRepository.findCarById(car_id);
        if (car == null) {
            throw new ApiException("the Car id not Found");
        }
        car.setConditions(con);
        if (car.getConditions().equals(car.getConditions())){
            car.setCurrentStatus("Available");
            car.setLocation(loc);
            carRepository.save(car);
            employee.setCountOfCarsInspected(employee.getCountOfCarsInspected()+1);
            employeeRepository.save(employee);
        }
        throw new ApiException("the car");


    }
    public void  promotionEmployee(Integer id){
        Employee employee =employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("the id employee not found");
        }

        if (employee.getTotalCars()>=90 && employee.getTotalCars()<=180) {
            employee.setLevel("advanced");
        }
        if (employee.getTotalCars()>=181&& employee.getTotalCars()<=280) {
            employee.setLevel("expert");
        }
    }

    public void defectCar(Integer emp_id,Integer car_id){
        Employee employee =employeeRepository.findEmployeeById(emp_id);
        if (employee == null) {
            throw new ApiException("the id employee not found");
        }
        Car car =carRepository.findCarById(car_id);
        if (car == null) {
            throw new ApiException("the id car not found");
        }
        if (car.getConditions().equals("defect")){
            car.setConditions("Intact");
            carRepository.save(car);
        }
    }

}


