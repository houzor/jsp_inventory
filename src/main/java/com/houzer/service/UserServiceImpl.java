package com.houzer.service;

import com.houzer.dao.UserDao;
import com.houzer.dao.UserDaoImpl;
import com.houzer.pojo.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl(){
        userDao = new UserDaoImpl();
    }

    public User login(int id, String password) {
        return userDao.getById(id,password);
    }

    public Boolean updateUser(User user) {
        int i = userDao.updateUser(user);
        boolean flag=false;
        if (i != 0) {
            flag=true;
        }
        return flag;
    }

    public List<User> getAllUsers() {
        return userDao.getUserList();
    }

    public List<User> getByUserName(String userName) {
        return userDao.getUserByName(userName);
    }

    public User getByUserId(int uid) {
        return userDao.getUserById(uid);
    }

    public Boolean addUser(User user) {
        int i = userDao.addUser(user);
        boolean flag=false;
        if (i != 0) {
            flag=true;
        }
        return flag;
    }

    public Boolean deleteUser(int userId, Boolean ishaveattend, Boolean ishaverecord) throws SQLException {
        int i=0;
        boolean flag=false;
        if(ishaveattend==true||ishaverecord==true)
        {
            i=userDao.deleteHaveAttendUser(userId,ishaveattend,ishaverecord);
        }else{
            i=userDao.deleteUser(userId);
        }
        if(i!=0){
            flag=true;
        }
        return flag;
    }



}
