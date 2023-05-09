package com.houzer.service;

import com.houzer.dao.SupplierDao;
import com.houzer.dao.SupplierDaoImpl;
import com.houzer.pojo.Supplier;

import java.sql.SQLException;
import java.util.List;

public class SupplierServiceImpl implements SupplierService{
    private SupplierDao supplierDao;
    public SupplierServiceImpl(){
        supplierDao = new SupplierDaoImpl();
    }
    public List<Supplier> getAllSuppliers() {
        return supplierDao.getSupplierList();
    }

    public List<Supplier> getByName(String supplierName) {
        return supplierDao.getSupplierListByName(supplierName);
    }

    public Boolean addSuppliers(Supplier supplier) {
        int i=supplierDao.addSupplier(supplier);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }

    public Supplier getById(int supplierId) {
        return supplierDao.getSupplierById(supplierId);
    }

    public Boolean updateSuppliers(Supplier supplier) {
        int i=supplierDao.updateSupplier(supplier);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }

    public Boolean deletesupplier(int supplierId) throws SQLException {
        int i=0;
        boolean flag=false;
        i=supplierDao.deletesupplier(supplierId);
        if(i!=0){
            flag=true;
        }
        return flag;
    }
}
