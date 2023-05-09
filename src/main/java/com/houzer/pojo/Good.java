package com.houzer.pojo;

import java.util.Date;

public class Good {
    private String goodId;
    private String goodName;
    private double goodPrice;
    private Date createDate;

    private int amount;

    private int life;

    private int supplierId;
    private String supplierName;//供应商名称

    @Override
    public String toString() {
        return "Good{" +
                "goodId=" + goodId +
                ", goodName='" + goodName + '\'' +
                ", goodPrice=" + goodPrice +
                ", createDate=" + createDate +
                ", amount=" + amount +
                ", life=" + life +
                ", supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                '}';
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
}
