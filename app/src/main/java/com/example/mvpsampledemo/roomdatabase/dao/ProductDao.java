package com.example.mvpsampledemo.roomdatabase.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.mvpsampledemo.roomdatabase.entity.Product;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product productEntity);

    @Query("Select * FROM tbl_product")
    List<Product> loadAllProductList();

    @Query("DELETE FROM tbl_product WHERE id = :id")
    void deleteById(int id);


}
