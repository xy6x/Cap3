package com.example.rentingcars.Service;

import com.example.rentingcars.ApiException.ApiException;
import com.example.rentingcars.Model.Supplier;
import com.example.rentingcars.Repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SupplierService {
    private  final SupplierRepository supplierRepository;
    public List<Supplier> getAllSupplier(){
        return supplierRepository.findAll();
    }
    public void addSupplier(Supplier supplier){
        supplierRepository.save(supplier);
    }
    public void updateSupplier(Integer id,Supplier supplier){
        Supplier oldSupplier =supplierRepository.findSupplierById(id);
        if (oldSupplier == null) {
            throw new ApiException("the id Supplier not found");
        }
        oldSupplier.setName(supplier.getName());
        oldSupplier.setPhone_number(supplier.getPhone_number());
        oldSupplier.setNumberOfCrs(supplier.getNumberOfCrs());
        supplierRepository.save(oldSupplier);
    }
    public  void deleteSupplier(Integer id){
        Supplier supplier =supplierRepository.findSupplierById(id);
        if (supplier == null) {
            throw new ApiException("the id Supplier not found");
        }
        supplierRepository.delete(supplier);
    }
}
