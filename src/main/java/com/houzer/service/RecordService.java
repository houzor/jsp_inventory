package com.houzer.service;

import com.houzer.pojo.Record;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecords();

    List<Record> getByName(String recordName);


    Boolean addRecord(Record record);

    Boolean deleteByGoodId(String goodId);

    Boolean isHaveRecord(int userId);

}
