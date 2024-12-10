package com.example.androidtest5;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Burger.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class BurgerAbstract extends RoomDatabase {
    public abstract BurgerDAO getInterface();
}
