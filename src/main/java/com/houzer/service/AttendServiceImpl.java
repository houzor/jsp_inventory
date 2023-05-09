package com.houzer.service;

import com.houzer.dao.AttendDao;
import com.houzer.dao.AttendDaoImpl;
import com.houzer.dao.RecordDao;
import com.houzer.dao.RecordDaoImpl;
import com.houzer.pojo.Attend;
import com.houzer.pojo.Good;

import java.util.ArrayList;
import java.util.List;

public class AttendServiceImpl implements AttendService{
    private AttendDao attendDao;

    public AttendServiceImpl(){
        attendDao = new AttendDaoImpl();
    }
    public Boolean addAttend(Attend attend) {
        int i=attendDao.addAttend(attend);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }

    public List<Attend> getAllAttend() {
        return attendDao.getAttendList();
    }

    public List<Attend> getById(String attendId) {
        return attendDao.getAttendListById(attendId);
    }

    public Boolean ishaveAttend(int userId) {
        List<Attend> attendList=new ArrayList<Attend>();
        attendList=attendDao.getAttendListById(String.valueOf(userId));
        boolean flag=true;
        if(attendList.size() == 0){
            flag=false;
        }
        return flag;
    }


}
