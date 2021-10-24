package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    public TextView name, stars;
    public ImageView avatar;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.textView);
        stars = itemView.findViewById(R.id.textView2);
        avatar = itemView.findViewById(R.id.imageView);
    }

    public TextView getName() {
        return name;
    }

    public TextView getStars() {
        return stars;
    }

    public ImageView getAvatar() {
        return avatar;
    }
}
