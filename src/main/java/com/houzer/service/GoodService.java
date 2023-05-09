package com.houzer.service;

import com.houzer.pojo.Good;

import java.util.ArrayList;
import java.util.List;

public interface GoodService {
    public List<Good> getAllGoods();

    public List<Good> getByName(String goodName);

    public Boolean addGoods(Good good);

    public Good getById(String goodId);

    public Boolean updateGoods(Good good);

    public Boolean deletegood(String goodId);

    Boolean updateAmount(String outId, int outAmount);



    List<Good> getBySupplierId(int supplierId);
}
