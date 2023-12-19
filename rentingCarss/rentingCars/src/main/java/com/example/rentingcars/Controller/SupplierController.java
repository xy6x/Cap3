package com.example.rentingcars.Controller;

import com.example.rentingcars.Model.Supplier;
import com.example.rentingcars.Service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/supplier")
public class SupplierController {
    private final SupplierService supplierService;
    @GetMapping("/get")
    public ResponseEntity getAllSupplier(){
        return ResponseEntity.status(HttpStatus.OK).body(supplierService.getAllSupplier());
    }
    @PostMapping("/add")
    public ResponseEntity addSupplier(@RequestBody@Valid Supplier supplier){
        supplierService.addSupplier(supplier);
        return ResponseEntity.status(HttpStatus.OK).body("added Supplier");
    }
    @PutMapping("/put/{id}")
    public ResponseEntity updateSupplier(@PathVariable Integer id , @RequestBody @Valid Supplier supplier){
        supplierService.updateSupplier(id, supplier);
        return ResponseEntity.status(HttpStatus.OK).body("update Supplier");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSupplier(@PathVariable Integer id){
        supplierService.deleteSupplier(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete Supplier");
    }




    @PutMapping("/{supplier_id}/assign/{car_id}")
    public ResponseEntity assignSupplierToCar(@PathVariable Integer supplier_id, @PathVariable Integer car_id){
        supplierService.assignSupplierToCar(supplier_id,car_id);
        return ResponseEntity.status(200).body("DONE");

    }


}