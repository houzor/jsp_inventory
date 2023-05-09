package com.houzer.dao;

import com.houzer.pojo.Record;

import java.util.List;

public interface RecordDao {

    List<Record> getRecordList();

    List<Record> getRecordListByName(String recordName);

    int addRecord(Record record);

    int deleteRecordByGood(String goodId);

    List<Record> getRecordListById(int userId);

}
