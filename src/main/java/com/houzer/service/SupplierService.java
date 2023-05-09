package com.houzer.service;

import com.houzer.pojo.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierService {

    List<Supplier> getAllSuppliers();

    List<Supplier> getByName(String supplierName);

    Boolean addSuppliers(Supplier supplier);

    Supplier getById(int supplierId);

    Boolean updateSuppliers(Supplier supplier);

    Boolean deletesupplier(int supplierId) throws SQLException;

}
