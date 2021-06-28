package com.example.restcountries.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDAO {
    @Query("SELECT * FROM countrymodel")
    List<CountryModel> getAll();

    @Insert
    void insertAll(List<CountryModel> listModel);

    @Query("DELETE FROM countrymodel")
    void delete();


}
