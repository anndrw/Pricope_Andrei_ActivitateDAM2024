package com.example.androidtest5;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BurgerDAO {
    @Insert
    void insert(Burger burger);

    @Update
    void update(Burger burger);

    @Delete()
    void delete(Burger burger);

    @Query("SELECT * from Burger")
    List<Burger> getBurgeriLista();
}
