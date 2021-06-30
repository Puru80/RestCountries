package com.example.restcountries;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.restcountries.roomdb.AppData;
import com.example.restcountries.roomdb.CountryDAO;
import com.example.restcountries.roomdb.CountryModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryDataService {
    Context context;
    List<CountryModel> list = new ArrayList<>();

    public CountryDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(List<CountryModel> models);
    }

    public void storeDataRoom(List<CountryModel> countryList){
        new Thread(() -> {
            AppData appDatabase = Room.databaseBuilder(context,
                    AppData.class, "country_data").build();
            CountryDAO dao = appDatabase.countryDAO();
            dao.delete();
            dao.insertAll(countryList);
        }).start();
    }

    public void getCountryData(VolleyResponseListener responseListener){
        List<CountryModel> countryList = new ArrayList<>();

        String query = "https://restcountries.eu/rest/v2/region/asia";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, query, null,
                response -> {
                    try {
                        for (int i=0;i<response.length();i++){
                            //System.out.println("i: " + i);
                            JSONObject country = (JSONObject) response.get(i);
                            CountryModel model = new CountryModel(i+1);

                            model.setName(country.getString("name"));
                            model.setCapital(country.getString("capital"));
                            model.setFlag(country.getString("flag"));
                            model.setRegion(country.getString("region"));
                            model.setSubregion(country.getString("subregion"));
                            model.setPopulation(country.getLong("population"));

                            JSONArray jArray = country.getJSONArray("borders");
                            int k = 0;
                            String str = "";
                            if(jArray.length()>0) {
                                while (k < jArray.length() - 1) {
                                    str += jArray.getString(k) + ", ";
                                    //System.out.println("k: " + k);
                                    k++;
                                }
                                str += jArray.getString(k);
                                //System.out.println("k: " + k);
                                model.setBorders(str);
                            }

                            str="";
                            k=0;
                            jArray = country.getJSONArray("languages");
                            if(jArray.length()>0) {
                                while (k < jArray.length() - 1) {
                                    str += jArray.getJSONObject(k).getString("name") + ", ";
                                    //System.out.println("k: " + k);
                                    k++;
                                }
                                str += jArray.getJSONObject(k).getString("name");
                                //System.out.println("k: " + k);
                                model.setLanguages(str);
                            }

                            countryList.add(model);
                        }
                        list = countryList;

                        responseListener.onResponse(countryList);


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }, error -> {
                    responseListener.onError("Some Error Occured");
                    Toast.makeText(context, "Some error occured", Toast.LENGTH_SHORT).show();
                });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
