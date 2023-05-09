package com.houzer.dao;

import com.houzer.pojo.Good;
import com.houzer.utils.Utils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoImpl implements GoodDao{
    public List<Good> getGoodList() {
        String sql="select g.*,s.supplier_name from supplier s, good g where g.supplier_id=s.supplier_id";
        List<Good> goodList=new ArrayList<Good>();
        ResultSet rs = null;
        Utils ut=new Utils();
        rs = ut.query(sql);
        try {
            while(rs.next()){
                Good good=new Good();
                good.setGoodId(rs.getString("good_id"));
                good.setGoodName(rs.getString("good_name"));
                good.setGoodPrice(rs.getDouble("good_price"));
                good.setCreateDate(rs.getDate("create_date"));
                good.setAmount(rs.getInt("amount"));
                good.setLife(rs.getInt("life"));
                good.setSupplierId(rs.getInt("supplier_id"));
                good.setSupplierName(rs.getString("supplier_name"));
                goodList.add(good);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return goodList;
    }

    public List<Good> getGoodListByName(String goodName) {
        String sql="select g.*,s.supplier_name from supplier s, good g where g.supplier_id=s.supplier_id and good_name like'%"+goodName+"%'";
        List<Good> goodList=new ArrayList<Good>();
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        try {
            while(rs.next()){
                Good good=new Good();
                good.setGoodId(rs.getString("good_id"));
                good.setGoodName(rs.getString("good_name"));
                good.setGoodPrice(rs.getDouble("good_price"));
                good.setCreateDate(rs.getDate("create_date"));
                good.setAmount(rs.getInt("amount"));
                good.setLife(rs.getInt("life"));
                good.setSupplierId(rs.getInt("supplier_id"));
                good.setSupplierName(rs.getString("supplier_name"));
                goodList.add(good);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return goodList;
    }

    public int addGood(Good good) {
        String sql="insert into good values(?,?,?,?,?,?,?)";
//        这里我用了prepareStatement，因为有很多个参数，我不想去拼接字符串了，可能到后面都把他改掉
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,good.getGoodId());
            ps.setString(2,good.getGoodName());
            ps.setDouble(3,good.getGoodPrice());
//            这里有一个问题util.date和sql.date不一样,要用转换方式转一下
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String time = simpleDateFormat.format(good.getCreateDate());

            ps.setDate(4,java.sql.Date.valueOf(time));
            ps.setInt(5,good.getAmount());
            ps.setInt(6,good.getLife());
            ps.setInt(7,good.getSupplierId());
            System.out.println();
            i=ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public Good getGoodById(String goodId) {
        String sql="select * from good where good_id="+goodId;
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        Good good=new Good();
        try {
            if(rs.next()){
                good.setGoodId(rs.getString("good_id"));
                good.setGoodName(rs.getString("good_name"));
                good.setGoodPrice(rs.getDouble("good_price"));
                good.setCreateDate(rs.getDate("create_date"));
                good.setAmount(rs.getInt("amount"));
                good.setLife(rs.getInt("life"));
                good.setSupplierId(rs.getInt("supplier_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return good;
    }

    public int updateGood(Good good) {
        String sql="update good set good_name=?,good_price=?,create_date=?,amount=?,life=?,supplier_id=? where good_id=?";
//        这里我用了prepareStatement，因为有很多个参数，我不想去拼接字符串了，可能到后面都把他改掉
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,good.getGoodName());
            ps.setDouble(2,good.getGoodPrice());
//            这里有一个问题util.date和sql.date不一样,要用转换方式转一下
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String time = simpleDateFormat.format(good.getCreateDate());

            ps.setDate(3,java.sql.Date.valueOf(time));
            ps.setInt(4,good.getAmount());
            ps.setInt(5,good.getLife());
            ps.setInt(6,good.getSupplierId());
            ps.setString(7,good.getGoodId());
            System.out.println(ps.toString());
            i=ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public int deletegood(String goodId) {
        String sql="delete from good where good_id=?";
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,goodId);
            i=ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public int updateAmount(String outId, int outAmount) {
        String sql="update good set amount=? where good_id=?";
//        这里我用了prepareStatement，因为有很多个参数，我不想去拼接字符串了，可能到后面都把他改掉
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,outAmount);
            ps.setInt(2, Integer.parseInt(outId));
            System.out.println(ps.toString());
            i=ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public List<Good> getGoodBySupplierId(int supplierId) {
        String sql="select * from good where supplier_id="+supplierId;
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        List<Good> goodList=new ArrayList<Good>();
        try {
            while(rs.next()){
                Good good=new Good();
                good.setGoodId(rs.getString("good_id"));
                good.setGoodName(rs.getString("good_name"));
                good.setGoodPrice(rs.getDouble("good_price"));
                good.setCreateDate(rs.getDate("create_date"));
                good.setAmount(rs.getInt("amount"));
                good.setLife(rs.getInt("life"));
                good.setSupplierId(rs.getInt("supplier_id"));
                goodList.add(good);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return goodList;
    }


}
