package com.example.android_myshop_admin.Model;

import java.io.Serializable;

public class ChiTietDonHang implements Serializable {

    public  int id;
    public  int id_bill;
    public int id_product;
    public  int quantity;
    public  double  price;
    public  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public ChiTietDonHang(int id, int id_bill, int id_product, int quantity, double price, String name) {
        this.id = id;
        this.id_bill = id_bill;
        this.id_product = id_product;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_bill() {
        return id_bill;
    }

    public void setId_bill(int id_bill) {
        this.id_bill = id_bill;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int  quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }




}
