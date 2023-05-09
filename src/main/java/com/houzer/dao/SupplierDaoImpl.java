package com.houzer.dao;

import com.houzer.pojo.Good;
import com.houzer.pojo.Supplier;
import com.houzer.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDao{

    public List<Supplier> getSupplierList() {
        String sql="select * from supplier";
        List<Supplier> supplierList=new ArrayList<Supplier>();
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        try {
            while(rs.next()){
                Supplier supplier=new Supplier();
                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setContacts(rs.getString("contacts"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierList;
    }

    public List<Supplier> getSupplierListByName(String supplierName) {
        String sql="select * from supplier where supplier_name like '%"+supplierName+"%'";
        List<Supplier> supplierList=new ArrayList<Supplier>();
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        try {
            while(rs.next()){
                Supplier supplier = new Supplier();
                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setContacts(rs.getString("contacts"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
                supplierList.add(supplier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplierList;
    }

    public int addSupplier(Supplier supplier) {
        String sql="insert into supplier values(null,?,?,?,?)";
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContacts());
            ps.setString(3, supplier.getPhone());
            ps.setString(4, supplier.getAddress());
            System.out.println();
            i=ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public Supplier getSupplierById(int supplierId) {
        String sql="select * from supplier where supplier_id="+supplierId;
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        Supplier supplier=new Supplier();
        try {
            if(rs.next()){
                supplier.setSupplierId(rs.getInt("supplier_id"));
                supplier.setSupplierName(rs.getString("supplier_name"));
                supplier.setContacts(rs.getString("contacts"));
                supplier.setPhone(rs.getString("phone"));
                supplier.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplier;
    }

    public int updateSupplier(Supplier supplier) {
        String sql="update supplier set supplier_name=?,contacts=?,phone=?,address=? where supplier_id=?";
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, supplier.getSupplierName());
            ps.setString(2, supplier.getContacts());
            ps.setString(3, supplier.getPhone());
            ps.setString(4, supplier.getAddress());
            ps.setInt(5,supplier.getSupplierId());
            System.out.println();
            i=ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public int deletesupplier(int supplierId) {
        String sql="delete from supplier where supplier_id=?";
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,supplierId);
            i=ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }


}
