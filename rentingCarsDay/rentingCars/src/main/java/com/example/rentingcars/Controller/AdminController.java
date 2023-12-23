package com.example.rentingcars.Controller;


import com.example.rentingcars.DTO.CarDTO;
import com.example.rentingcars.Model.Admin;
import com.example.rentingcars.Service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    @GetMapping("/get")
    public ResponseEntity getAllAdmins() {

        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAdmins());
    }

    @PostMapping("/add")
    public ResponseEntity addAdmin(@RequestBody @Valid Admin admin) {
        adminService.addAdmin(admin);
        return ResponseEntity.status(HttpStatus.OK).body("added admin");
    }


    @PutMapping("/put/{id}")
    public ResponseEntity UpdateAdmin(@PathVariable Integer id, @RequestBody @Valid Admin admin) {
        adminService.updateAmin(id, admin);
        return ResponseEntity.status(HttpStatus.OK).body("update Admin");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAmin(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Admin");

    }


    @PutMapping("/verifyEmployee/{admin_id}/{employee_id}")
    public ResponseEntity verifyEmployee(@PathVariable Integer admin_id, @PathVariable Integer employee_id){
        adminService.verifyEmployee(admin_id,employee_id);
        return ResponseEntity.status(HttpStatus.OK).body("employee has been verified successfully");
    }
    @PutMapping("/assign/{admin_id}/{emp_id}")
    public ResponseEntity assign(@PathVariable Integer admin_id,@PathVariable Integer emp_id){
        adminService.assignAdminToEmployee(admin_id, emp_id);
        return ResponseEntity.status(HttpStatus.OK).body("employee assign");
    }
}
