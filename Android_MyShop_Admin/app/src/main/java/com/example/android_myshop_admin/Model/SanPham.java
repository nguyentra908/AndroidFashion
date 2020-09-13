package com.example.android_myshop_admin.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    public  int Id;
    public  String name;
    public  int price;
    public  String  description;
    public  String  image;
    public  String  color;

    public  String  material;
    public  int id_type;
    public SanPham() {

    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }



    public SanPham(int id, String name, int price, String image, String color, String material, String description, int id_type) {
        Id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.color = color;
        this.material = material;
        this.id_type = id_type;
    }

    public SanPham(String name, String image, int price, String description) {

        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;

    }



    public int getId() {
        return Id;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }
}
