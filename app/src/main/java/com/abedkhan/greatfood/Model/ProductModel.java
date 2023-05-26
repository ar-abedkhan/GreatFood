package com.abedkhan.greatfood.Model;

public class ProductModel {
    String foodId,foodName,resturentName,foodImg,foodDes,location,foodPrice,ownerId;


    public ProductModel(String foodId, String foodName, String resturentName, String foodImg, String foodDes, String location, String foodPrice, String ownerId) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.resturentName = resturentName;
        this.foodImg = foodImg;
        this.foodDes = foodDes;
        this.location = location;
        this.foodPrice = foodPrice;
        this.ownerId = ownerId;
    }

    public ProductModel(){

    }


    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getResturentName() {
        return resturentName;
    }

    public void setResturentName(String resturentName) {
        this.resturentName = resturentName;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String getFoodDes() {
        return foodDes;
    }

    public void setFoodDes(String foodDes) {
        this.foodDes = foodDes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
