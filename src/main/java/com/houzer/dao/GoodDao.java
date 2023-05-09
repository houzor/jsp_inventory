package com.houzer.dao;

import com.houzer.pojo.Good;

import java.util.ArrayList;
import java.util.List;

public interface GoodDao {
    public List<Good> getGoodList();

    public List<Good> getGoodListByName(String goodName);

    int addGood(Good good);

    public Good getGoodById(String goodId);

    int updateGood(Good good);

    int deletegood(String goodId);

    int updateAmount(String outId, int outAmount);

    List<Good> getGoodBySupplierId(int supplierId);


}
