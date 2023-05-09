package com.houzer.dao;

import com.houzer.pojo.Attend;
import com.houzer.pojo.Good;
import com.houzer.pojo.Supplier;
import com.houzer.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AttendDaoImpl implements AttendDao{
    public int addAttend(Attend attend) {
        String sql="insert into attend values(null,?,?,?)";
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, attend.getUserId());
            ps.setString(2, attend.getAttendType());
            ps.setString(3, attend.getAttendTime());
            System.out.println(ps.toString());
            i=ps.executeUpdate();
            System.out.println(ps.toString());
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public List<Attend> getAttendList() {
        String sql="select * from attend";
        List<Attend> attendList=new ArrayList<Attend>();
        ResultSet rs = null;
        Utils ut=new Utils();
        rs = ut.query(sql);
        try {
            while(rs.next()){
                Attend attend=new Attend();
                attend.setAttendId(rs.getInt("attend_id"));
                attend.setUserId(rs.getInt("user_id"));
                attend.setAttendType(rs.getString("attend_type"));
                //                这里需要转换一下时间格式,比较复杂
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("attend_time").getTime());
                attend.setAttendTime(timeStamp);
                attendList.add(attend);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return attendList;
    }

    public List<Attend> getAttendListById(String userId) {
        String sql="select * from attend where user_id="+userId;
        List<Attend> attendList=new ArrayList<Attend>();
        ResultSet rs = null;
        Utils ut=new Utils();
        System.out.println(sql);
        rs = ut.query(sql);
        try {
            while(rs.next()){
                Attend attend=new Attend();
                attend.setAttendId(rs.getInt("attend_id"));
                attend.setUserId(rs.getInt("user_id"));
                attend.setAttendType(rs.getString("attend_type"));
                //                这里需要转换一下时间格式,比较复杂
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("attend_time").getTime());
                attend.setAttendTime(timeStamp);
                attendList.add(attend);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return attendList;
    }
}
