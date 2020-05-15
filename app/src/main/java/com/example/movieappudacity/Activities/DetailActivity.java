package com.example.movieappudacity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movieappudacity.Adapters.ReviewAdapter;
import com.example.movieappudacity.Adapters.TrailerAdapter;
import com.example.movieappudacity.AppExecutors;
import com.example.movieappudacity.Databse.MovieDatabase;
import com.example.movieappudacity.Models.Favourite;
import com.example.movieappudacity.Models.Model;
import com.example.movieappudacity.Models.ReviewsModel;
import com.example.movieappudacity.Models.TrailerModel;
import com.example.movieappudacity.R;
import com.example.movieappudacity.Viewmodel.Viewmodel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity
{
    ImageView poster,addbutton;
    TextView Movietitle,MovieRelaseDate,MovieRating,MovieOverview;
    RecyclerView trailerRecyclerView,reviewsRecyclerView;
    Viewmodel viewmodel;
    Model model;
    private MovieDatabase mDb;
    private Boolean isFav = false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Gson gson=new Gson();
        model=gson.fromJson(getIntent().getStringExtra("jsonData"),Model.class);
        String url="https://image.tmdb.org/t/p/w500";
        init(model.getId());
        mDb=MovieDatabase.getsInstance(getApplicationContext());
        Picasso.get().load(url+model.getMovieImage()).into(poster);
        Movietitle.setText(model.getOriginalTitle());
        MovieRelaseDate.setText(model.getRelaseDate());
        MovieRating.setText(model.getUserRating()+"/10");
        MovieOverview.setText(model.getOverView());
        // To check if movie is resent in database or not
        AppExecutors.getInstance().diskIO().execute(new Runnable()
        {
            @Override
            public void run()
            {
                final Favourite fmov = mDb.movieDao().searchMovie(model.getOriginalTitle());
                setFavorite((fmov != null)? true : false);
            }
        });

        addbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addToFavourites();
            }
        });

    }
    public void init(String id)
    {
        poster=findViewById(R.id.movieImage);
        Movietitle=findViewById(R.id.originalName);
        MovieRelaseDate=findViewById(R.id.dateOfRelase);
        MovieRating=findViewById(R.id.userRating);
        MovieOverview=findViewById(R.id.overview);
        addbutton=findViewById(R.id.addButton);
        trailerRecyclerView=findViewById(R.id.trilerRecyclerView);
        reviewsRecyclerView=findViewById(R.id.ReviewsRecyclerView);
        fetchTrailers(id);
        fetchReviews(id);

    }
    public void fetchTrailers(String id)
    {
        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        trailerRecyclerView.setHasFixedSize(true);
        viewmodel= ViewModelProviders.of(this).get(Viewmodel.class);
        viewmodel.getTrailerData(id).observe(this, new Observer<ArrayList<TrailerModel>>()
        {
            @Override
            public void onChanged(ArrayList<TrailerModel> trailerModels)
            {
                TrailerAdapter adapter=new TrailerAdapter(getApplicationContext(),trailerModels);
                trailerRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });

    }
    public void fetchReviews(String  id)
    {
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        reviewsRecyclerView.setHasFixedSize(true);
        viewmodel= ViewModelProviders.of(this).get(Viewmodel.class);
        viewmodel.getReviewsData(id).observe(this, new Observer<ArrayList<ReviewsModel>>()
        {
            @Override
            public void onChanged(ArrayList<ReviewsModel> reviewsModels)
            {
                ReviewAdapter adapter=new ReviewAdapter(getApplicationContext(),reviewsModels);
                reviewsRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }
    public void addToFavourites()
    {

        Favourite favourite=new Favourite(model.getOriginalTitle(),model.getMovieImage(),model.getUserRating(),model.getRelaseDate(),model.getOverView(),model.getId());
        Log.d("afsdg",""+favourite.getOriginalTitle().toString());
        AppExecutors.getInstance().diskIO().execute(new Runnable()
        {
            @Override
            public void run()
            {
                if (isFav)
                {

                    mDb.movieDao().deleteTask(favourite);
                }
                else
                {
                    mDb.movieDao().insert(favourite);
                    Log.d("jhgf","+deleted");
                }
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        setFavorite(!isFav);
                    }
                });


            }

        });


    }
    private void setFavorite(Boolean fav){
        if (fav) {
            isFav = true;
            addbutton.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            isFav = false;
            addbutton.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
    }


}
