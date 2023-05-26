package com.abedkhan.greatfood.Model;

public class UserModel {

    String  userId,userName,UserMail,userPass;


    public UserModel(String userId, String userName, String userMail, String userPass) {
        this.userId = userId;
        this.userName = userName;
        UserMail = userMail;
        this.userPass = userPass;
    }

    public UserModel(){
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return UserMail;
    }

    public void setUserMail(String userMail) {
        UserMail = userMail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
