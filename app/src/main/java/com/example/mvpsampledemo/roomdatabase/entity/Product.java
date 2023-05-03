package com.example.mvpsampledemo.roomdatabase.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "tbl_product")
public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public Product(String name) {
        this.name = name;
    }
}
