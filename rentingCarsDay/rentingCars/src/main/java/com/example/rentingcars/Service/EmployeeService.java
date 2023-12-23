package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.DTO.EmployeeDTO;
import com.example.rentingcars.Model.Admin;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Model.Employee;
import com.example.rentingcars.Repository.AdminRepository;
import com.example.rentingcars.Repository.CarRepository;
import com.example.rentingcars.Repository.EmployeeRepository;
import com.example.rentingcars.Repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final EmployeeRepository employeeRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private  final AdminRepository adminRepository;

    public List<Employee> getAllEmployee() {

        return employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
        Boolean tcheck =false;
        employee.setIsApproved(tcheck);
        employeeRepository.save(employee);
    }

    public void updateEmployee(Integer id, EmployeeDTO employeeDTO) {
        Employee oldEmployee =employeeRepository.findEmployeeById(id);
        if (oldEmployee == null) {
            throw new ApiException("the Employee not found");
        }
        oldEmployee.setName(employeeDTO.getName());
        oldEmployee.setAge(employeeDTO.getAge());
        oldEmployee.setCountOfCarsInspected(employeeDTO.getCountOfCarsInspected());
        oldEmployee.setQualification(employeeDTO.getQualification());
        oldEmployee.setBonus(employeeDTO.getBonus());
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


    public void bonusEmployee(Integer id){
        Employee employee =employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new ApiException("the id not found ");
        }
        if (employee.getIsApproved().equals(true)) {

            if (employee.getLevel().equals("beginner")) {
                if (employee.getCountOfCarsInspected() > 29) {
                    employee.setTotalCars(employee.getTotalCars() + employee.getCountOfCarsInspected());
                    employee.setBonus(employee.getBonus() + 50);
                    employee.setCountOfCarsInspected(0);
                    employeeRepository.save(employee);
                }else throw new ApiException("the getCountOfCarsInspected >30 ");
            }
            if (employee.getLevel().equals("advanced")) {
                if (employee.getCountOfCarsInspected() > 29) {
                    employee.setTotalCars(employee.getTotalCars() + employee.getCountOfCarsInspected());
                    employee.setBonus(employee.getBonus() + 60);
                    employee.setCountOfCarsInspected(0);
                    employeeRepository.save(employee);
                }else throw new ApiException("the getCountOfCarsInspected >30 ");
        }
            if (employee.getLevel().equals("expert")) {
                if (employee.getCountOfCarsInspected() > 29) {
                    employee.setTotalCars(employee.getTotalCars() + employee.getCountOfCarsInspected());
                    employee.setBonus(employee.getBonus() + 70);
                    employee.setCountOfCarsInspected(0);
                    employeeRepository.save(employee);
                }else throw new ApiException("the getCountOfCarsInspected >30 ");

            }
        }else throw new ApiException("the employee not Approved");

    }



    public void  checkCarAfterRental(Integer employee_id ,Integer car_id,String location,String condition){
        Employee employee=employeeRepository.findEmployeeById(employee_id);
        if (employee == null) {
            throw new ApiException("the id employee not found");
        }
        Car car =carRepository.findCarById(car_id);
        if (car == null) {
            throw new ApiException("the Car id not Found");
        }
        car.setConditions(condition);
        if (car.getConditions().equals(car.getConditions())&&employee.getIsApproved().equals(true)){
            car.setCurrentStatus("Available");
            car.setLocation(location);
            carRepository.save(car);
            employee.setCountOfCarsInspected(employee.getCountOfCarsInspected()+1);
            employeeRepository.save(employee);
        } throw new ApiException("the employee not Approved");
    }

    public void  promotionEmployee(Integer id){
        Employee employee =employeeRepository.findEmployeeById(id);
       if (employee.getIsApproved().equals(true)) {
           if (employee == null) {
               throw new ApiException("the id employee not found");
           }
           if (employee.getTotalCars() >= 90 && employee.getTotalCars() <= 180) {
               employee.setLevel("advanced");
           }
           if (employee.getTotalCars() >= 181 && employee.getTotalCars() <= 280) {
               employee.setLevel("expert");
           }
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
        if (car.getConditions().equals("defect")&& employee.getIsApproved().equals(true)){
            car.setConditions("Intact");
            carRepository.save(car);
        } throw new ApiException("the employee not Approved");

    }

    public void assignSuper(Integer eId , Integer sId){
Employee employee =employeeRepository.findEmployeeById(eId);
Employee su =employeeRepository.findEmployeeById(sId);
        if (su == null|| employee ==null) {
            throw new ApiException("the id employee not found");
        }

        if (su.getIsApproved().equals(true)&&employee.getIsApproved().equals(true)) {

            employee.setSuperF(su);
            employeeRepository.save(employee);
        } throw new ApiException("the employee not Approved");

    }
  public Integer sumC(Integer id){
        Employee inte=employeeRepository.findEmployeeById(id);
       Integer num =0;
      for (Employee i:inte.getEmployee()) {
          num =i.getCountOfCarsInspected()+num;

      }
      return num;
  }


}


