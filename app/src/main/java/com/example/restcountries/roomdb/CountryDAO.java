package com.example.restcountries.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDAO {
    @Query("SELECT * FROM countrymodel")
    List<CountryModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CountryModel> listModel);

    @Query("DELETE FROM countrymodel")
    void delete();


}
