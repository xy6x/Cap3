package com.example.rentingcars.Service;


import com.example.rentingcars.API.ApiException;
import com.example.rentingcars.Model.Car;
import com.example.rentingcars.Model.Rental;
import com.example.rentingcars.Model.Supplier;
import com.example.rentingcars.Repository.CarRepository;
import com.example.rentingcars.Repository.RentalRepository;
import com.example.rentingcars.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;


    public List<Supplier> getAllSupplier(){

        return supplierRepository.findAll();
    }
    public void addSupplier(Supplier supplier){

        supplierRepository.save(supplier);
    }

    public void updateSupplier(Integer id,Supplier supplier){
        Supplier oldSupplier =supplierRepository.findSupplierById(id);
        if (oldSupplier == null) {
            throw new ApiException("Supplier ID incorrect");
        }
        oldSupplier.setName(supplier.getName());
        oldSupplier.setPhone_number(supplier.getPhone_number());
        supplierRepository.save(oldSupplier);
    }

    public  void deleteSupplier(Integer id){
        Supplier supplier =supplierRepository.findSupplierById(id);
        if (supplier == null) {
            throw new ApiException("Supplier ID incorrect");
        }
        supplierRepository.delete(supplier);
    }


    public List<Supplier> getAllCarSameSupplier(Integer id_Supplier){
        List<Supplier> cars = new ArrayList<>();
        Supplier supplier =supplierRepository.findSupplierById(id_Supplier);

        if(supplier==null){
            throw new ApiException("Supplier ID incorrect");
        }
        for (int i = 0; i <getAllSupplier().size() ; i++) {
            if (getAllSupplier().get(i).getId().equals(id_Supplier)) {
                cars.add(getAllSupplier().get(i));

            }
        }
        return cars;
    }
}
