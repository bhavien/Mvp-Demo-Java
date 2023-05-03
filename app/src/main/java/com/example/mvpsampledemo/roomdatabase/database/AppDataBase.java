package com.example.mvpsampledemo.roomdatabase.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvpsampledemo.roomdatabase.dao.ProductDao;
import com.example.mvpsampledemo.roomdatabase.entity.Product;

@Database(
        entities = Product.class,
        version = 1,
        exportSchema = false
)

public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;
    public abstract ProductDao productDao();

    public static synchronized AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDataBase.class, "product_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
