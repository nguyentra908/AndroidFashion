package com.example.android_myshop_admin.Model;

import java.io.Serializable;

public class TopTaiKhoan implements Serializable {
    private int user_id;
    private String user_name;

    private int totalprice;
    private int soluongdonhang;

    public TopTaiKhoan(int user_id, String user_name, int totalprice, int soluongdonhang) {
        this.user_id = user_id;
        this.user_name = user_name;

        this.totalprice = totalprice;
        this.soluongdonhang = soluongdonhang;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }



    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public int getSoluongdonhang() {
        return soluongdonhang;
    }

    public void setSoluongdonhang(int soluongdonhang) {
        this.soluongdonhang = soluongdonhang;
    }
}
