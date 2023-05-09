package com.houzer.dao;

import com.houzer.pojo.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDao {

    List<Supplier> getSupplierList();

    List<Supplier> getSupplierListByName(String supplierName);

    int addSupplier(Supplier supplier);

    Supplier getSupplierById(int supplierId);

    int updateSupplier(Supplier supplier);

    int deletesupplier(int supplierId);


}
