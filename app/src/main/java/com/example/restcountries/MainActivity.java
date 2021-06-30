package com.example.restcountries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.restcountries.roomdb.AppData;
import com.example.restcountries.roomdb.CountryDAO;
import com.example.restcountries.roomdb.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<CountryModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton deleteBtn = findViewById(R.id.appCompatButton);
        AppCompatButton fetChData = findViewById(R.id.getDAta);

        AppData appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppData.class, "country_data").build();

        boolean network = checkNetworkConnection();

        final CountryDataService countryDataService = new CountryDataService(MainActivity.this);

        if(network) {
            countryDataService.getCountryData(new CountryDataService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Toast.makeText(MainActivity.this, "Some error Occured", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(List<CountryModel> models) {
                    modelList = models;
                }
            });
        }
        else{
            new Thread(() ->{
                CountryDAO dao = appDatabase.countryDAO();
                countryDataService.list = dao.getAll();
            }).start();
        }

        fetChData.setOnClickListener(v -> {
            if(checkNetworkConnection()) {
                countryDataService.getCountryData(new CountryDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Some error Occured", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(List<CountryModel> models) {
                        modelList = models;
                    }
                });
            }
            else{
                new Thread(() ->{
                    CountryDAO dao = appDatabase.countryDAO();
                    countryDataService.list = dao.getAll();
                }).start();
            }

            if(countryDataService.list.isEmpty())
                Toast.makeText(this, "Database empty, Please connect to internet and try again",
                        Toast.LENGTH_SHORT).show();
            else{
                RecyclerView recyclerView = findViewById(R.id.countryList);
                CountryAdapter adapter = new CountryAdapter(modelList, network);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                countryDataService.storeDataRoom(modelList);
            }
        });

        deleteBtn.setOnClickListener(v -> {
            new Thread(() -> {
                CountryDAO dao = appDatabase.countryDAO();
                dao.delete();
            }).start();
        });
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}