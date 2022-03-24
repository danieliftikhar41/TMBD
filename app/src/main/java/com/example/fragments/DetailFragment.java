package com.example.fragments;

import static com.example.fragments.Config.DefaultConstants.ACCOUNT_ID;
import static com.example.fragments.Config.DefaultConstants.API_KEY;
import static com.example.fragments.Config.DefaultConstants.BASE_IMG_URL;
import static com.example.fragments.Config.DefaultConstants.SESSION_ID;
import static com.example.fragments.Config.DefaultConstants.retrofit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fragments.Config.ApiCall;
import com.example.fragments.Config.GlideApp;
import com.example.fragments.Model.Film.AccountStates;
import com.example.fragments.Model.Film.Film;
import com.example.fragments.Model.Film.searchFilmModel;
import com.example.fragments.Model.List.List;
import com.example.fragments.Recyclers.AddMovieListsRecyclerViewAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailFragment extends Fragment {

    boolean fav;
    ApiCall apiCall;
    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Bundle bundle = getArguments();
        Film film = (Film) bundle.getSerializable("Film");

        TextView txtDetailTitle = view.findViewById(R.id.txtDetailTitle);
        TextView txtDetailDesc = view.findViewById(R.id.txtDetailDesc);
        ImageView imgDetail = view.findViewById(R.id.imgDetail);
        ImageButton btnFav = view.findViewById(R.id.btnFav);
        ImageButton btnAddtoList = view.findViewById(R.id.btnAddtoList);


        txtDetailTitle.setText(film.getOriginal_title());
        txtDetailDesc.setText(film.getOverview());

        GlideApp.with(getContext())
                .load(BASE_IMG_URL + film.getPoster_path())
                .centerCrop()
                .into(imgDetail);
        apiCall = retrofit.create(ApiCall.class);
        Call<AccountStates> favcall = apiCall.account_state(film.getId(),API_KEY,SESSION_ID);
        favcall.enqueue(new Callback<AccountStates>(){
            @Override
            public void onResponse(Call<AccountStates> favcall, Response<AccountStates> response) {
                if(response.code()!=200){
                    Log.i("testApi", "checkConnection");
                    Log.i("testApi", response.message());
                    return;
                }else {
                    Log.i("testApi", "done");
                    fav=response.body().getFavorite();
                    if(fav){
                        btnFav.setImageResource(R.drawable.ic_fav_on);
                    }else{
                        btnFav.setImageResource(R.drawable.ic_fav_off);
                    }
                }
            }

            @Override
            public void onFailure(Call<AccountStates> call, Throwable t) {

            }
        });
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                apiCall = retrofit.create(ApiCall.class);
                boolean nFav=!fav;
                Log.i("testApi", ""+nFav);
                Call<searchFilmModel> call= apiCall.setFav(ACCOUNT_ID,API_KEY,SESSION_ID,"movie", film.getId(), nFav);;

                call.enqueue(new Callback<searchFilmModel>(){
                    @Override
                    public void onResponse(Call<searchFilmModel> call, Response<searchFilmModel> response) {

                        if(response.isSuccessful()){

                            if(fav){
                                btnFav.setImageResource(R.drawable.ic_fav_off);

                            }else{
                                btnFav.setImageResource(R.drawable.ic_fav_on);
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<searchFilmModel> call, Throwable t) {

                    }
                });
            }
        });

        btnAddtoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


        return view;

    }

    public void showDialog(){
        View alertCustomdialog = getLayoutInflater().inflate( R.layout.form_movie_to_list, null);

        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        //set our custom alert dialog to tha alertdialog builder
        alert.setView(alertCustomdialog);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();


        ArrayList<List> arrayList = new ArrayList<List>();
        arrayList.add(new List("Comedia", 8));
        arrayList.add(new List("Ciència", 8));
        arrayList.add(new List("Terror", 8));
        arrayList.add(new List("Comedia", 8));
        arrayList.add(new List("Ciència", 8));
        arrayList.add(new List("Terror", 8));
        arrayList.add(new List("Comedia", 8));
        arrayList.add(new List("Ciència", 8));
        arrayList.add(new List("Terror", 8));


        RecyclerView recyclerView = alertCustomdialog.findViewById(R.id.recyclerList);
        AddMovieListsRecyclerViewAdapter adapter = new AddMovieListsRecyclerViewAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}