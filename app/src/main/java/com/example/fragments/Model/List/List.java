package com.example.fragments.Model.List;

public class List {
    public String title;
    public int count;
    public String name;
    public String description;
    public int id;
    public int favorite_count;
    public int item_count;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(int favorite_count) {
        this.favorite_count = favorite_count;
    }

    public List() {
    }
    public List(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public List(String title, int count) {
        this.title = title;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }
}
