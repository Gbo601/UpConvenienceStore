package org.wlzhj.ucs_admin.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Item {
    private int id;
    private String itemName;
    private String category;
    private boolean isOnSale;
    private BigDecimal price;
    private String detail;
    private String picUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date addTime;
    private int amount;


    public Item(){

    }
    public Item(int id, String itemName, String category, boolean isOnSale, BigDecimal price, String detail, String picUrl, Date addTime, int amount) {
        this.id = id;
        this.itemName = itemName;
        this.category = category;
        this.isOnSale = isOnSale;
        this.price = price;
        this.detail = detail;
        this.picUrl = picUrl;
        this.addTime = addTime;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", isOnSale=" + isOnSale +
                ", price=" + price +
                ", detail='" + detail + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", addTime=" + addTime +
                ", amount=" + amount +
                '}';
    }
}
