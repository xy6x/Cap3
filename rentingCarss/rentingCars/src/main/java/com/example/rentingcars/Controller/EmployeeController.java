package com.example.rentingcars.Controller;


import com.example.rentingcars.Model.Employee;
import com.example.rentingcars.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getAllEmployee() {
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllEmployee());
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body("added Employee");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@PathVariable Integer id, @RequestBody @Valid Employee employee) {
        employeeService.updateEmployee(id, employee);
        return ResponseEntity.status(HttpStatus.OK).body("update Employee");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Employee");
    }


    @PutMapping("/{employee_id}/assign/{car_id}")
    public ResponseEntity assignEmployeeToCar(@PathVariable Integer employee_id, @PathVariable Integer car_id){
        employeeService.assignEmployeeToCar(employee_id,car_id);
        return ResponseEntity.status(200).body("DONE");

    }

    @GetMapping("/get/{id}")
    public ResponseEntity getAllCarUser(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(employeeService.getAllCar(id));

    }
    @PutMapping("/putt/{id}")
    public ResponseEntity subCar(@PathVariable Integer id){
        employeeService.subCar(id);
        return ResponseEntity.status(HttpStatus.OK).body("don");

    }
}

