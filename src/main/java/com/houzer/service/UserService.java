package com.houzer.service;

import com.houzer.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    //用户登录
    public User login(int userid,String password);


    public Boolean updateUser(User user);

    List<User> getAllUsers();

    List<User> getByUserName(String userName);

    User getByUserId(int userId);

    Boolean addUser(User user);

    Boolean deleteUser(int userId, Boolean ishaveattend, Boolean ishaverecord) throws SQLException;


}
