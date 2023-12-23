package com.example.rentingcars.Repository;


import com.example.rentingcars.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findAdminById(Integer id);

}
