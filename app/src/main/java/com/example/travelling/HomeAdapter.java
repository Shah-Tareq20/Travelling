package com.example.travelling;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelling.Fragments.HomeFragment;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public HomeAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleText.setText(items.get(position).getCityName());
        holder.smallText.setText(items.get(position).getCountry());
        holder.backgroundImage.setImageResource(items.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
