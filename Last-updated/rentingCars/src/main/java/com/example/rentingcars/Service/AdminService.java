package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.Model.*;

import com.example.rentingcars.Repository.AdminRepository;
import com.example.rentingcars.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final EmployeeRepository employeeRepository;

 public List<Admin> getAllAdmins(){

     return adminRepository.findAll();
 }

 public void addAdmin(Admin admin){


     adminRepository.save(admin);
 }

 public void updateAmin(Integer id, Admin admin){
     Admin oldAdmin =adminRepository.findAdminById(id);
     if (oldAdmin == null) {
         throw new ApiException("the id admin not found");
     }
     oldAdmin.setName(admin.getName());
     oldAdmin.setUsername(admin.getUsername());
     oldAdmin.setPassword(admin.getPassword());
   adminRepository.save(oldAdmin);

 }
 public void deleteAmin(Integer id){
     Admin admin =adminRepository.findAdminById(id);
     if (admin == null) {
         throw new ApiException("the id admin not found");
     }
     adminRepository.delete(admin);
 }


 public void verifyEmployee(Integer admin_id, Integer employee_id){
     Admin admin =adminRepository.findAdminById(admin_id);
     if (admin == null) {
         throw new ApiException("Admin ID incorrect");
     }
     Employee employee =employeeRepository.findEmployeeById(employee_id);
         if(employee==null){
             throw new ApiException("employee ID incorrect");
         }
         if(employee.getQualification().equals("engineer")){
             employee.setIsApproved(true);
             employeeRepository.save(employee);
         }
         else
     throw new ApiException("the employee qualification not suitable");
     }


    public void assignAdminToEmployee(Integer admin_id, Integer employee_id ){
        Admin admin=adminRepository.findAdminById(admin_id);
        Employee employee=employeeRepository.findEmployeeById(employee_id);

        if(admin==null || employee == null){
            throw  new ApiException("supplier or rental not found");
        }
        employee.setAdmin(admin);
        employeeRepository.save(employee);
    }
 }


