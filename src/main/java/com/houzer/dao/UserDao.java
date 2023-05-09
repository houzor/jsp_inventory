package com.houzer.dao;

import com.houzer.pojo.User;
import com.houzer.utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    public User getById(int id, String password);


    int updateUser(User user);

    List<User> getUserList();

    List<User> getUserByName(String userName);

    User getUserById(int uid);

    int addUser(User user);

    int deleteUser(int userId);


    int deleteHaveAttendUser(int userId, Boolean ishaveattend, Boolean ishaverecord) throws SQLException;
}
