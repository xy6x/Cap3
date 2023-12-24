package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.DTO.EmployeeDTO;
import com.example.rentingcars.Model.*;
import com.example.rentingcars.Repository.AdminRepository;
import com.example.rentingcars.Repository.CarRepository;
import com.example.rentingcars.Repository.EmployeeRepository;
import com.example.rentingcars.Repository.UserRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
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
    private final AdminService adminService;
    private final AdminRepository adminRepository;

    public List<Employee> getAllEmployee() {

        return employeeRepository.findAll();
    }

    public void addEmployee(EmployeeDTO employeeDTO) {

        Admin admin = adminRepository.findAdminById(employeeDTO.getAdmin_id());

        if (admin == null) {
            throw new ApiException("id admin not found");
        }
//

        Employee employee = new Employee(null,employeeDTO.getName(),employeeDTO.getUsername(),employeeDTO.getPassword(),employeeDTO.getAge(),employeeDTO.getCountOfCarsInspected(),employeeDTO.getIsApproved(),employeeDTO.getQualification(),employeeDTO.getBonus(),employeeDTO.getTotalCars(),employeeDTO.getLevel(),admin,null,null,null);
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

    public Set<Car> getAllCarsCheckedByEmployee(Integer  id){
        Employee employee =employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("Employee ID incorrect");
        }
        carRepository.findAll();
        return employee.getCars();
    }



    public void bonusEmployee(Integer id){
        Employee employee =employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("Employee ID incorrect");
        }
        if(employee.getIsApproved().equals(true)) {
            if (employee.getLevel().equals("beginner")) {
                if (employee.getCountOfCarsInspected() > 29) {
                    employee.setTotalCars(employee.getTotalCars() + employee.getCountOfCarsInspected());
                    employee.setBonus(employee.getBonus() + 50);
                    employee.setCountOfCarsInspected(0);
                    employeeRepository.save(employee);
                }else throw new ApiException("the countOfCarsInspected >30");
            }
            if (employee.getLevel().equals("advanced")) {
                if (employee.getCountOfCarsInspected() > 29) {
                    employee.setTotalCars(employee.getTotalCars() + employee.getCountOfCarsInspected());
                    employee.setBonus(employee.getBonus() + 60);
                    employee.setCountOfCarsInspected(0);
                    employeeRepository.save(employee);

                }else throw new ApiException("the countOfCarsInspected >30");
            }
            if (employee.getLevel().equals("expert")) {
                if (employee.getCountOfCarsInspected() > 29) {
                    employee.setTotalCars(employee.getTotalCars() + employee.getCountOfCarsInspected());
                    employee.setBonus(employee.getBonus() + 70);
                    employee.setCountOfCarsInspected(0);
                    employeeRepository.save(employee);
                }else throw new ApiException("the countOfCarsInspected >30");
            }
        } else {
            throw new ApiException("employee has not been approved");}
    }




    public void  checkCarAfterRental(Integer employee_id ,Integer car_id,String location,String condition) {
        Employee employee = employeeRepository.findEmployeeById(employee_id);
        if (employee == null) {
            throw new ApiException("employee ID incorrect");
        }
        Car car = carRepository.findCarById(car_id);
        if (car == null) {
            throw new ApiException("Car ID incorrect");
        }
        if(employee.getIsApproved().equals(true)) {
            car.setConditions(condition);
            if (car.getConditions().equals("Intact")) {
                car.setCurrentStatus("Available");
                car.setLocation(location);
                carRepository.save(car);
                employee.setCountOfCarsInspected(employee.getCountOfCarsInspected() + 1);
                employeeRepository.save(employee);
            } else {
                throw new ApiException("the car not Intact");
            }
        }
        else throw new ApiException("the employee not Approved");
    }



    public void  promotionEmployee(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("Employee ID incorrect");
        }
        if (employee.getIsApproved().equals(true)) {
            if (employee.getTotalCars() >= 90 && employee.getTotalCars() <= 180) {
                employee.setLevel("advanced");
                employeeRepository.save(employee);
            }
            if (employee.getTotalCars() >= 181) {
                employee.setLevel("expert");
                employeeRepository.save(employee);
            }
        } else {
            throw new ApiException("employee has not been approved");
        }
    }
    public void carMaintenance(Integer emp_id,Integer car_id){
        Employee employee =employeeRepository.findEmployeeById(emp_id);

        if (employee == null) {
            throw new ApiException("Employee ID incorrect");
        }
        if(employee.getIsApproved().equals(true)) {
            Car car = carRepository.findCarById(car_id);
            if (car == null) {
                throw new ApiException("Car ID incorrect");
            }
            if (car.getConditions().equals("defect")) {
                car.setConditions("Intact");
                carRepository.save(car);
            }
        }
        else
            throw new ApiException("employee has not been approved");
    }

    public void assignSuper(Integer eId , Integer sId){
        Employee employee =employeeRepository.findEmployeeById(eId);
        Employee su =employeeRepository.findEmployeeById(sId);
        if (su == null|| employee ==null) {
            throw new ApiException("the id employee not found");
        }

        employee.setSuperF(su);
        employeeRepository.save(employee);


    }

    public Integer sumCarsInspected(Integer id){
        Employee employee = employeeRepository.findEmployeeById(id);
        Integer sum=0;
        for (Employee i:employee.getEmployee()){
            sum=i.getCountOfCarsInspected()+sum;
                  }
        return sum;

        }

    }




