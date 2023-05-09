package com.houzer.pojo;

import java.util.Date;

public class Attend {
    private int attendId;
    private int userId;
    private String attendType;
    private String attendTime;

    @Override
    public String toString() {
        return "Attend{" +
                "attendId=" + attendId +
                ", userId=" + userId +
                ", attendType='" + attendType + '\'' +
                ", attendTime='" + attendTime + '\'' +
                '}';
    }

    public int getAttendId() {
        return attendId;
    }

    public void setAttendId(int attendId) {
        this.attendId = attendId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAttendType() {
        return attendType;
    }

    public void setAttendType(String attendType) {
        this.attendType = attendType;
    }

    public String getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(String attendTime) {
        this.attendTime = attendTime;
    }
}
