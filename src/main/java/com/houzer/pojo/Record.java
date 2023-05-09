package com.houzer.pojo;

public class Record {
    private int recordId;
    private String recordType;
    private int userId;
    private String recordName;
    private String recordTime;
    private int amount;
    private String goodId;

    @Override
    public String toString() {
        return "Record{" +
                "recordId=" + recordId +
                ", recordType='" + recordType + '\'' +
                ", userId=" + userId +
                ", recordName='" + recordName + '\'' +
                ", recordTime='" + recordTime + '\'' +
                ", amount=" + amount +
                ", goodId='" + goodId + '\'' +
                '}';
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }
}
