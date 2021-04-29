package com.example.restaurantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.restaurantapp.data.Result;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private List<Result> result;
    private Context context;
    public static final String FREE = "Free";
    public static final String INEXPENSIVE = "Inexpensive";
    public static final String MODERATE = "Moderate";
    public static final String EXPENSIVE = "Expensive";
    public static final String CLOSED = "Closed";
    public static final String OPEN_NOW = "Open Now";

    public RestaurantAdapter(List<Result> result, Context context) {
        this.result = result;
        this.context = context;
    }

    public void updateRestaurants(List<Result> result) {
        this.result = result;
    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_restaurant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.ViewHolder holder, int position) {
        holder.name.setText(result.get(position).name);
        final int rates = result.get(position).price_level;
        holder.other_details.setText(result.get(position).vicinity);
        holder.ratings.setText(String.valueOf(result.get(position).rating));
        Glide.with(context)
                .load(result.get(position).icon)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return this.result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,ratings, other_details;
        ImageView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_restaurant_name);
            icon = itemView.findViewById(R.id.iv_restaurant);
            ratings = itemView.findViewById(R.id.tv_ratings);
            other_details = itemView.findViewById(R.id.tv_restaurant_other_details);
        }
    }
}
