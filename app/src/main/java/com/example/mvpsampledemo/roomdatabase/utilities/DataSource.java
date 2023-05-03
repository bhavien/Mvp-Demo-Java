package com.example.mvpsampledemo.roomdatabase.utilities;


import com.example.mvpsampledemo.roomdatabase.database.AppDataBase;
import com.example.mvpsampledemo.roomdatabase.entity.Product;
import java.util.ArrayList;



public class DataSource {
    private static DataSource instance;
    private static AppDataBase dataBase;
    ArrayList<Product> product = new ArrayList<>();

    public static DataSource with(AppDataBase appDataBase) {
        if (dataBase == null)
            dataBase = appDataBase;
        if (instance == null)
            instance = new DataSource();
        return instance;
    }

    public void addProduct(String name) {
        Product product = productInstance(name);
        dataBase.productDao().insert(product);
    }

    private Product productInstance(String name) {
        Product product = new Product(name);
        product.name = name;
        return product;
    }

    public void loadProduct() {
        if (dataBase == null)
            return;
        for (int i = 0; i < product.size(); i++) {
            Product bookEntity = productInstance(product.get(i).name);
            product.add(bookEntity);
            dataBase.productDao().insert(bookEntity);
        }

    }

    public ArrayList<Product> getProducts(){
        loadProduct();
        return product;
    }

}
