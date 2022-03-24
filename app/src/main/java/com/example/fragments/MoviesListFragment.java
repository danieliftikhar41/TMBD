package com.example.fragments;

import static com.example.fragments.Config.DefaultConstants.*;
import static com.example.fragments.Config.DefaultConstants.retrofit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragments.Config.ApiCall;
import com.example.fragments.Model.Film.Film;
import com.example.fragments.Model.Film.searchFilmModel;
import com.example.fragments.Recyclers.SearchMovieRecyclerViewAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MoviesListFragment extends Fragment {

    public String sectionTitle;
    public View view;
    RecyclerView recyclerView;
    public MoviesListFragment() {
        // Required empty public constructor
    }

    public MoviesListFragment(String title) {
        this.sectionTitle = title;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movies_list, container, false);

        TextView txtSectionTitle = view.findViewById(R.id.sectionTitle);
        txtSectionTitle.setText(sectionTitle);
        recyclerView = view.findViewById(R.id.recyclerSearch);

        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<searchFilmModel> call = apiCall.getFev(ACCOUNT_ID,API_KEY,SESSION_ID);


        call.enqueue(new Callback<searchFilmModel>(){
            @Override
            public void onResponse(Call<searchFilmModel> call, Response<searchFilmModel> response) {
                if(response.code()!=200){
                    Log.i("testApi", "checkConnection");
                    Log.i("testApi", response.message());
                    return;
                }else {
                    Log.i("testApi", ""+response.body().getResults());
                    ArrayList<Film> arraySearch = new ArrayList<>();
                    arraySearch = response.body().getResults();
                    callRecycler(arraySearch);
                }
            }

            @Override
            public void onFailure(Call<searchFilmModel> call, Throwable t) {

            }
        });

        return view;
    }
    public void callRecycler(ArrayList<Film> arraySearch){
        SearchMovieRecyclerViewAdapter adapter = new SearchMovieRecyclerViewAdapter(arraySearch, getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(adapter);
    }
}