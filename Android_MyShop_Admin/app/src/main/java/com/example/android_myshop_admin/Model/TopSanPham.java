package com.example.android_myshop_admin.Model;

import java.io.Serializable;

public class TopSanPham implements Serializable {
    private int id;
    private String name;
    private int soluongsp;

    public TopSanPham(int id, String name, int soluongsp) {
        this.id = id;
        this.name = name;
        this.soluongsp = soluongsp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}
