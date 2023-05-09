package com.houzer.dao;

import com.houzer.pojo.User;
import com.houzer.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
    private User user;
    public User getById(int id, String password) {
        String sql="select * from user where user_id="+id+" and user_pwd='"+password+"'";
        System.out.println(sql);
        Utils ut=new Utils();
        ResultSet rs = ut.query(sql);

        try {
            if(rs.next()){
                user=new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPhone(rs.getString("user_phone"));
                user.setUserType(rs.getString("user_type"));
                user.setUserPwd(rs.getString("user_pwd"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public int updateUser(User user) {
        String sql = "update user set user_name=?,user_phone=?,user_pwd=?,user_type=? where user_id=?";
        Utils ut=new Utils();
        int i=0;
        Connection con = ut.getConn();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getUserPhone());
            ps.setString(3,user.getUserPwd());
            ps.setString(4,user.getUserType());
            ps.setInt(5,user.getUserId());
            System.out.println(ps.toString());
            i=ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e
            );
        }
        return i;
    }

    public List<User> getUserList() {
        String sql="select * from user";
        List<User> userList=new ArrayList<User>();
        ResultSet rs = null;
        Utils ut=new Utils();
        rs = ut.query(sql);
        try {
            while(rs.next()){
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPwd(rs.getString("user_pwd"));
                user.setUserPhone(rs.getString("user_phone"));
                user.setUserType(rs.getString("user_type"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public List<User> getUserByName(String userName) {
        String sql="select * from user where user_name like '%"+userName+"%'";
        List<User> userList=new ArrayList<User>();
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        try {
            while (rs.next()){
                user=new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPhone(rs.getString("user_phone"));
                user.setUserType(rs.getString("user_type"));
                user.setUserPwd(rs.getString("user_pwd"));
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public User getUserById(int userId) {
        String sql="select * from user where user_id="+userId;
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        User user=new User();
        try {
            while (rs.next()){
                user.setUserId(rs.getInt("user_id"));
                user.setUserName(rs.getString("user_name"));
                user.setUserPhone(rs.getString("user_phone"));
                user.setUserType(rs.getString("user_type"));
                user.setUserPwd(rs.getString("user_pwd"));
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public int addUser(User user) {
        String sql="insert into user values(?,?,?,?,?)";
        Utils ut = new Utils();
        int i=0;
        Connection conn = ut.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,user.getUserId());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getUserPhone());
            ps.setString(4,user.getUserPwd());
            ps.setString(5, user.getUserType());
            System.out.println();
            i=ps.executeUpdate();
            conn.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return i;
    }

    public int deleteUser(int userId) {
        String sql = "delete from user where user_id=?";
        Utils ut=new Utils();
        int i=0;
        Connection con = ut.getConn();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps. setInt(1,userId);
            i=ps.executeUpdate();
            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return i;
    }

    public int deleteHaveAttendUser(int userId, Boolean ishaveattend, Boolean ishaverecord) throws SQLException {
        String sql1="delete from attend where user_id=?";
        String sql2="delete from record where user_id=?";
        String sql3="delete from user where user_id=?";
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
//        这里要先关闭自动提交
        conn.setAutoCommit(false);
        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            PreparedStatement ps3 = conn.prepareStatement(sql3);
            ps1.setInt(1,userId);
            ps2.setInt(1,userId);
            ps3.setInt(1,userId);
            if(ishaveattend){
                i=ps1.executeUpdate();
            }
            if(ishaverecord){
                i=ps2.executeUpdate();
            }
            i=ps3.executeUpdate();
//            我们手动提交事务
            conn.commit();
//            重新开启自动提交
            conn.setAutoCommit(true);
            conn.close();

        } catch (SQLException e) {
            conn.rollback();
            throw new RuntimeException(e);
        }
        return i;
    }


}
