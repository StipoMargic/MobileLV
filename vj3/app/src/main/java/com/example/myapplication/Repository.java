package com.example.myapplication;

public class Repository {
    String name, stargazers_count;
    Owner owner;

    public String getName() {
        return name;
    }

    public String getStargazers_count() {
        return stargazers_count;
    }

    public Owner getOwner() {
        return owner;
    }
}
