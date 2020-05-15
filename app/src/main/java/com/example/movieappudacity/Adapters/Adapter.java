package com.example.movieappudacity.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieappudacity.Activities.DetailActivity;
import com.example.movieappudacity.Models.Model;
import com.example.movieappudacity.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>
{
    List<Model> arrayList;
  //  ArrayList<Model> arrayList;
    Context context;
    Context getContext;
    public Adapter(Context context,List<Model> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        getContext=parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        final Model model=arrayList.get(position);
        String url="https://image.tmdb.org/t/p/w500";

        Picasso.get().load(url+model.getMovieImage()).into(holder.movieImage);
        holder.movieImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d("mnbv",""+model.getId());

                Gson gson=new Gson();
                Intent intent=new Intent(getContext, DetailActivity.class);
                String Jsonmodel=gson.toJson(model);
                intent.putExtra("jsonData",Jsonmodel);
                getContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView movieImage;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            movieImage=itemView.findViewById(R.id.movie_image);
        }
    }
}
