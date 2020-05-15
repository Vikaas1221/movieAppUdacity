package com.example.movieappudacity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieappudacity.Models.ReviewsModel;
import com.example.movieappudacity.Models.TrailerModel;
import com.example.movieappudacity.R;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Viewholder>
{
    ArrayList<ReviewsModel> arrayList;
    Context context;
    Context getContext;
    public ReviewAdapter(Context context,ArrayList<ReviewsModel> arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item_layout,parent,false);
        Viewholder holder=new Viewholder(view);
        getContext=parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position)
    {
        ReviewsModel model=arrayList.get(position);
        holder.author_t.setText(model.getAuthor());
        holder.content_t.setText(model.getContent());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder
    {
        TextView author_t,content_t;
        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            author_t=itemView.findViewById(R.id.author);
            content_t=itemView.findViewById(R.id.content);
        }
    }
}
