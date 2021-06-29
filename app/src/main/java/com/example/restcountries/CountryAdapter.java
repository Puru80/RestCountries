package com.example.restcountries;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restcountries.roomdb.CountryModel;
import com.squareup.picasso.Picasso;

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

        holder.countryName.setText(model.getName());
        holder.countryCapital.setText(model.getCapital());
        holder.countryRegion.setText("Region: " + model.getRegion() + ", " + "SubRegion: " + model.getSubregion());
        holder.countryPopulation.setText((int) model.getPopulation());

        /*int i = 0;
        String str = "";
        while(i<model.getBorders().size()-1){
            str += model.getBorders().get(i) + ", ";
            i++;
        }
        str += model.getBorders().get(i);
        holder.countryBorders.setText(str);

        i=0;
        str = "";
        while(i<model.getLanguages().size()-1){
            str += model.getLanguages().get(i) + ", ";
            i++;
        }
        str += model.getLanguages().get(i);
        holder.countryLang.setText(str);*/

        holder.countryLang.setText(model.getLanguages());
        holder.countryBorders.setText(model.getBorders());

        if(network){
            Picasso.get().load(model.getFlag()).into(holder.flag);
        }
        else holder.flag.setImageResource(R.drawable.ic_baseline_flag_24);

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
