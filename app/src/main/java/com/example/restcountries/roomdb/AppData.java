package com.example.restcountries.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CountryModel.class}, version = 1)
public abstract class AppData extends RoomDatabase {
    public abstract CountryDAO countryDAO();
}
