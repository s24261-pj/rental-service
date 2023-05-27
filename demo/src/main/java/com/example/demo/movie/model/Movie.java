package com.example.demo.movie.model;

public class Movie {
    private long id;
    private String name;
    private Category category;

    private boolean isAvailable = false;

    public Movie(long id, String name, Category category, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    public Movie() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
