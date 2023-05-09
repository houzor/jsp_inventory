package com.houzer.dao;

import com.houzer.pojo.Attend;

import java.util.List;

public interface AttendDao {
    int addAttend(Attend attend);

    List<Attend> getAttendList();



    List<Attend> getAttendListById(String attendId);
}
