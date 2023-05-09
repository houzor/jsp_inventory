package com.houzer.dao;

import com.houzer.pojo.Record;
import com.houzer.pojo.Supplier;
import com.houzer.utils.Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao{
    public List<Record> getRecordList() {
        String sql="select * from record";
        List<Record> recordList=new ArrayList<Record>();
        ResultSet rs = null;
        Utils ut = new Utils();
        rs = ut.query(sql);
        System.out.println(rs.toString());
        System.out.println("我运行了");
        try {
            while(rs.next()){
                Record rc = new Record();
                rc.setRecordId(rs.getInt("record_id"));
                rc.setRecordType(rs.getString("record_type"));
                rc.setRecordName(rs.getString("record_name"));
//                这里需要转换一下时间格式,比较复杂
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("record_time").getTime());
                rc.setRecordTime(timeStamp);
                rc.setAmount(rs.getInt("amount"));
                rc.setUserId(rs.getInt("user_id"));
                rc.setGoodId(rs.getString("good_id"));
                System.out.println(rc.toString());
                recordList.add(rc);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return recordList;
    }

    public List<Record> getRecordListByName(String recordName) {
        String sql="select * from record where record_name like '%"+recordName+"%'";
        List<Record> recordList=new ArrayList<Record>();
        ResultSet rs = null;
        Utils ut = new Utils();
        rs = ut.query(sql);
        System.out.println(rs.toString());
        System.out.println("我运行了");
        try {
            while(rs.next()){
                Record rc = new Record();
                rc.setRecordId(rs.getInt("record_id"));
                rc.setRecordType(rs.getString("record_type"));
                rc.setRecordName(rs.getString("record_name"));
//                这里需要转换一下时间格式,比较复杂
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("record_time").getTime());
                rc.setRecordTime(timeStamp);
                rc.setAmount(rs.getInt("amount"));
                rc.setUserId(rs.getInt("user_id"));
                rc.setGoodId(rs.getString("good_id"));
                System.out.println(rc.toString());
                recordList.add(rc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recordList;
    }

    public int addRecord(Record record) {
        String sql="insert into record values(null,?,?,?,?,?,?)";
        Utils utils=new Utils();
        int i=0;
        Connection conn = utils.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, record.getRecordType());
            System.out.println(record.getUserId());
            ps.setInt(2, record.getUserId());
            ps.setString(3, record.getRecordName());
            ps.setString(4, record.getRecordTime());
            ps.setInt(5, record.getAmount());
            ps.setString(6,record.getGoodId());
            System.out.println(ps.toString());
            i=ps.executeUpdate();
            System.out.println(ps.toString());
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return i;
    }

    public int deleteRecordByGood(String goodId) {
        String sql="delete from record where good_id=?";
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

    public List<Record> getRecordListById(int userId) {
        String sql="select * from record where user_id="+userId;
        List<Record> recordList=new ArrayList<Record>();
        ResultSet rs = null;
        Utils ut = new Utils();
        rs = ut.query(sql);
        System.out.println(rs.toString());
        System.out.println("我运行了");
        try {
            while(rs.next()){
                Record rc = new Record();
                rc.setRecordId(rs.getInt("record_id"));
                rc.setRecordType(rs.getString("record_type"));
                rc.setRecordName(rs.getString("record_name"));
//                这里需要转换一下时间格式,比较复杂
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp("record_time").getTime());
                rc.setRecordTime(timeStamp);
                rc.setAmount(rs.getInt("amount"));
                rc.setUserId(rs.getInt("user_id"));
                rc.setGoodId(rs.getString("good_id"));
                System.out.println(rc.toString());
                recordList.add(rc);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return recordList;
    }
}
