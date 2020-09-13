package com.example.android_myshop_admin.Model;

import java.io.Serializable;

/**
 * Created by LynkMieu on 20/08/2016.
 */
public class Account implements Serializable {

    private int user_id;
    private String userName;
    private String email;
    private String phone;
    private String address;
    private String password;
    private String quyen;



//    public Account(Integer user_id, String user_name, String password, String email, String phone, String address, String quyen) {
//        this.user_id = user_id;
//        this.userName = user_name;
//        this.password = password;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//        this.quyen = quyen;
//    }

    public Account(Integer user_id, String user_name, String password, String email, String phone, String address, String quyen) {
        this.user_id = user_id;
        this.userName = user_name;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.quyen = quyen;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account(String userName, String email, String phone, String address) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }



    public Account() {
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
