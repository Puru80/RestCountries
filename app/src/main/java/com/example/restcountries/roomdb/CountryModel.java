package com.example.restcountries.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "countrymodel")
public class CountryModel {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "capital")
    private String capital;
    @ColumnInfo(name = "flag")
    private String flag;
    @ColumnInfo(name = "region")
    private String region;
    @ColumnInfo(name = "subregion")
    private String subregion;
    @ColumnInfo(name = "population")
    private long population;
    @ColumnInfo(name = "borders")
    private String borders;
    @ColumnInfo(name = "languages")
    private String languages;

    public CountryModel(int id) {
        this.id = id;
    }

    /*
        Display following attributes - name, capital, flag(display image in app),
        region, subregion, population, borders & languages.
    */

    /*
        {
            "name": "Afghanistan",
            "capital": "Kabul",
            "region": "Asia",
            "subregion": "Southern Asia",
            "population": 27657145,
            "borders": [
                "IRN",
                "PAK",
                "TKM",
                "UZB",
                "TJK",
                "CHN"
            ],
            "languages": [
                {
                    "iso639_1": "ps",
                        "iso639_2": "pus",
                        "name": "Pashto",
                        "nativeName": "پښتو"
                },
                {
                    "iso639_1": "uz",
                        "iso639_2": "uzb",
                        "name": "Uzbek",
                        "nativeName": "Oʻzbek"
                },
                {
                    "iso639_1": "tk",
                        "iso639_2": "tuk",
                        "name": "Turkmen",
                        "nativeName": "Türkmen"
                }
            ],
            "flag": "https://restcountries.eu/data/afg.svg",
        }
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "CountryModel{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", flag='" + flag + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", population=" + population +
                ", borders=" + borders +
                ", languages=" + languages +
                '}';
    }
}
