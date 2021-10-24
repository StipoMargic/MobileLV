package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {
    RepositoryList repositories = new RepositoryList();
    RecyclerView recyclerView;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rvInfo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        makeApiReq();
    }

    private void makeApiReq() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.github.com/search/repositories?q=stars:>100000";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response", response);

                Gson gson = new GsonBuilder().create();
                repositories = gson.fromJson(response,RepositoryList.class);

                adapter = new CustomAdapter(repositories.items);

                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.toString());
            }
        }
        );
        queue.add(stringRequest);
        queue.start();
    }
}