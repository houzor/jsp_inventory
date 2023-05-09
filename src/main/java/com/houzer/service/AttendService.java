package com.houzer.service;

import com.houzer.pojo.Attend;

import java.util.List;

public interface AttendService {

    Boolean addAttend(Attend attend);


    List<Attend> getAllAttend();


    List<Attend> getById(String attendId);

    Boolean ishaveAttend(int userId);
}
