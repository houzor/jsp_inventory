package com.houzer.service;

import com.houzer.dao.GoodDao;
import com.houzer.dao.GoodDaoImpl;
import com.houzer.pojo.Good;

import java.util.ArrayList;
import java.util.List;

public class GoodServiceImpl implements GoodService{
    private GoodDao goodDao;

    public GoodServiceImpl(){
        goodDao = new GoodDaoImpl();
    }
    public List<Good> getAllGoods() {
        return goodDao.getGoodList();
    }

    public List<Good> getByName(String goodName) {
        return goodDao.getGoodListByName(goodName);
    }

    public Boolean addGoods(Good good) {
        int i=goodDao.addGood(good);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }

    public Good getById(String goodId) {

        return goodDao.getGoodById(goodId);
    }

    public Boolean updateGoods(Good good) {
        int i=goodDao.updateGood(good);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }

    public Boolean deletegood(String goodId) {
        int i=goodDao.deletegood(goodId);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }

    public Boolean updateAmount(String outId, int outAmount) {
        int i=goodDao.updateAmount(outId,outAmount);
        boolean flag=false;
        if(i!=0){
            flag=true;
        }
        return flag;
    }


    public List<Good> getBySupplierId(int supplierId) {
        return goodDao.getGoodBySupplierId(supplierId);
    }


}
