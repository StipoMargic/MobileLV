package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    public List<Repository> items;

    public CustomAdapter(List<Repository> repositories) {
        this.items = repositories;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_data, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.getName().setText(items.get(position).getName());
        holder.getStars().setText(items.get(position).getStargazers_count());

        String avatar = items.get(position).getOwner().getAvatar_url();

        Glide.with(holder.itemView.getContext()).load(avatar).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
