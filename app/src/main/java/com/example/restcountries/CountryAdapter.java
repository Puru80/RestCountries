package com.example.restcountries;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restcountries.roomdb.CountryModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {
    private final boolean network;
    private final List<CountryModel> list;

    public CountryAdapter(List<CountryModel> list, boolean network){
        this.list = list;
        this.network = network;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CountryModel model = list.get(position);

        holder.countryName.setText("Name: " + model.getName());
        holder.countryCapital.setText("Capital: " + model.getCapital());
        holder.countryRegion.setText("Region: " + model.getRegion() + ", " + "SubRegion: " + model.getSubregion());
        holder.countryPopulation.setText("Population: " + model.getPopulation());
        holder.countryLang.setText("Languages: " + model.getLanguages());
        holder.countryBorders.setText("Borders: " + model.getBorders());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView countryName, countryCapital, countryRegion, countryPopulation,
            countryBorders, countryLang;
        private final ImageView flag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            countryName = itemView.findViewById(R.id.countryName);
            countryCapital = itemView.findViewById(R.id.countryCapital);
            countryRegion = itemView.findViewById(R.id.countryRegion);
            countryPopulation = itemView.findViewById(R.id.countryPopulation);
            countryBorders = itemView.findViewById(R.id.countryBorders);
            countryLang = itemView.findViewById(R.id.countryLanguages);
            flag = itemView.findViewById(R.id.flag);
        }
    }
}
