package com.example.restcountries;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.restcountries.roomdb.CountryModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CountryDataService {
    private String query = "https://restcountries.eu/rest/v2/region/asia";
    Context context;

    public CountryDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener{
        void onError(String message);

        void onResponse(List<CountryModel> models);
    }

    public void getCountryData(VolleyResponseListener responseListener){
        List<CountryModel> countryList = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, query, null,
                response -> {
                    try {
                        for (int i=0;i<response.length();i++){
                            JSONObject country = (JSONObject) response.get(i);
                            CountryModel model = new CountryModel();

                            model.setName(country.getString("name"));
                            model.setCapital(country.getString("capital"));
                            model.setFlag(country.getString("flag"));
                            model.setRegion(country.getString("region"));
                            model.setSubregion(country.getString("subregion"));
                            model.setPopulation(country.getLong("population"));
//                            model.setBorders(country.getJSONArray("borders"));

                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }, error -> {

                });

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
