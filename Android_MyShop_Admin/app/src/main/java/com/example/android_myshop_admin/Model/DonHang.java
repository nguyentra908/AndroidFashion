package com.example.android_myshop_admin.Model;

import java.io.Serializable;
import java.sql.Date;

public class DonHang implements Serializable {
    public  int Id;
    public  int id_customer;
    public String date_order;
    public  float total;

    public  String note;
    public  String  status;
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public DonHang(int id, int id_customer, String date_order, float total, String status) {
        Id = id;
        this.id_customer = id_customer;
        this.date_order = date_order;
        this.total = total;

        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
