package com.example.travelling;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView backgroundImage;
    TextView titleText, smallText;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        backgroundImage = itemView.findViewById(R.id.home_frag_image);
        smallText = itemView.findViewById(R.id.home_frag_small_text);
        titleText = itemView.findViewById(R.id.home_frag_title);
    }
}
