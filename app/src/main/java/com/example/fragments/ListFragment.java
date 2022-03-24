package com.example.fragments;

import static com.example.fragments.Config.DefaultConstants.ACCOUNT_ID;
import static com.example.fragments.Config.DefaultConstants.API_KEY;
import static com.example.fragments.Config.DefaultConstants.SESSION_ID;
import static com.example.fragments.Config.DefaultConstants.retrofit;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragments.Config.ApiCall;
import com.example.fragments.Model.Film.Film;
import com.example.fragments.Model.Film.searchFilmModel;
import com.example.fragments.Model.List.List;
import com.example.fragments.Model.List.ListModel;
import com.example.fragments.Model.List.ListResult;
import com.example.fragments.Recyclers.AddMovieListsRecyclerViewAdapter;
import com.example.fragments.Recyclers.ListRecyclerAdapter;
import com.example.fragments.Recyclers.SearchMovieRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {
    RecyclerView recyclerView;
    public ListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        FloatingActionButton btnAdd = view.findViewById(R.id.btnAddList);
        ApiCall apiCall = retrofit.create(ApiCall.class);
        Call<ListModel> call = apiCall.getList(ACCOUNT_ID,API_KEY,SESSION_ID);

        call.enqueue(new Callback<ListModel>(){
            @Override
            public void onResponse(Call<ListModel> call, Response<ListModel> response) {
                if(response.code()!=200){
                    Log.i("testApi", "checkConnection");
                    Log.i("testApi", response.message());
                    return;
                }else {
                    recyclerView = view.findViewById(R.id.recyclerList);
                    Log.i("testApi", ""+response.body().getResults().size());
                    ArrayList<ListResult> arrayList = new ArrayList<>();
                    arrayList = response.body().getResults();
                    callRecycler(arrayList);
                }
            }

            @Override
            public void onFailure(Call<ListModel> call, Throwable t) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        return view;
    }

    public void showDialog(){
        View alertCustomdialog = getLayoutInflater().inflate( R.layout.form_add_list, null);

        //initialize alert builder.
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        //set our custom alert dialog to tha alertdialog builder
        alert.setView(alertCustomdialog);

        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.show();
        TextView nameView=alertCustomdialog.findViewById(R.id.txtList);
        TextView DesView=alertCustomdialog.findViewById(R.id.txtDescription);

        Button btnSaveList = alertCustomdialog.findViewById(R.id.btnSaveList);

        btnSaveList.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                ApiCall apiCall = retrofit.create(ApiCall.class);
                List l=new List();
                l.setName(nameView.getText().toString());
                l.setDescription(DesView.getText().toString());
                Call<searchFilmModel> call = apiCall.setList(API_KEY,SESSION_ID, l);
                call.enqueue(new Callback<searchFilmModel>(){
                    @Override
                    public void onResponse(Call<searchFilmModel> call, Response<searchFilmModel> response) {
                        if(response.code()!=200){
                            Log.i("testApi", "checkConnection");
                            Log.i("testApi", response.message());
                            return;
                        }else {
                            Log.i("testApi", ""+response.body().getResults());
                        }
                    }

                    @Override
                    public void onFailure(Call<searchFilmModel> call, Throwable t) {

                    }
                });

                dialog.dismiss();
            }
        });
    }
    public void callRecycler(ArrayList<ListResult> arrayList){

        ListRecyclerAdapter adapter = new ListRecyclerAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);
    }
}