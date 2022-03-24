package com.example.fragments.Model.List;

import com.example.fragments.Model.Film.Film;

import java.util.ArrayList;

public class ListModel {
    public int page;
    public ArrayList<ListResult> results;

    public int getPage() {
        return page;
    }

    public ArrayList<ListResult> getResults() {
        return results;
    }

}
