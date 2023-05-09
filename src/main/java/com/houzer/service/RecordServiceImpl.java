package com.houzer.service;

import com.houzer.dao.GoodDao;
import com.houzer.dao.GoodDaoImpl;
import com.houzer.dao.RecordDao;
import com.houzer.dao.RecordDaoImpl;
import com.houzer.pojo.Good;
import com.houzer.pojo.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordServiceImpl implements RecordService{
    private RecordDao recordDao;

    public RecordServiceImpl(){
        recordDao = new RecordDaoImpl();
    }
    public List<Record> getAllRecords() {
        return recordDao.getRecordList();
    }

    public List<Record> getByName(String recordName) {
        return recordDao.getRecordListByName(recordName);
    }

    public Boolean addRecord(Record record) {
        int i=recordDao.addRecord(record);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }

    public Boolean deleteByGoodId(String goodId) {
        int i=recordDao.deleteRecordByGood(goodId);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }

    public Boolean isHaveRecord(int userId) {
        List<Record> recordList=new ArrayList<Record>();
        recordList=recordDao.getRecordListById(userId);
        System.out.println(recordList);
        boolean flag=true;
        if(recordList.size() == 0){
            flag=false;
        }
        return flag;
    }
}
