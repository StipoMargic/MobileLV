package com.example.myapplication4;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView title, column;
    ClickListener listener;
    public CustomViewHolder(@NonNull View itemView, ClickListener listener) {
        super(itemView);
        this.title = itemView.findViewById(R.id.textView);
        this.column = itemView.findViewById(R.id.textView2);
        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(getAdapterPosition(), v);
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
