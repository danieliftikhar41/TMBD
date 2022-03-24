package com.example.fragments.Recyclers;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragments.Model.List.List;
import com.example.fragments.Model.List.ListResult;
import com.example.fragments.R;

import java.util.ArrayList;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder> {
    private ArrayList<ListResult> arrayList;
    private Context context;

    public ListRecyclerAdapter(ArrayList<ListResult> arrN, Context c){
        this.arrayList = arrN;
        this.context = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.listTitle.setText(arrayList.get(i).getName());
        holder.itemCount.setText(arrayList.get(i).getDescription());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView listTitle;
        TextView itemCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listTitle = itemView.findViewById(R.id.listTitle);
            itemCount= itemView.findViewById(R.id.itemCount);
        }
    }
}

