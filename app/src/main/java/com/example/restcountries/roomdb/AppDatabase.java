package com.example.restcountries.roomdb;

import androidx.room.Database;

@Database(entities = {CountryModel.class}, version = 1)
public abstract class AppDatabase {
    public abstract CountryDAO countryDAO();
}
