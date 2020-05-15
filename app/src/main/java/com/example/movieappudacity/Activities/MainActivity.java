package com.example.movieappudacity.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.movieappudacity.Adapters.Adapter;
import com.example.movieappudacity.Models.Favourite;
import com.example.movieappudacity.Models.Model;
import com.example.movieappudacity.Networks.Internet;
import com.example.movieappudacity.R;
import com.example.movieappudacity.Viewmodel.Viewmodel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    RecyclerView movieRecyclerview;
    RecyclerView.Adapter adapter;
    public static Context context;
    Viewmodel viewmodel;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        movieRecyclerview=findViewById(R.id.movieRecycler);
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        fetchMovies("popular");
        setTitle(getString(R.string.app_name)+"-Popular Movies");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.mostPopular:
            {
                progressBar.setVisibility(View.VISIBLE);
                setTitle(getString(R.string.app_name)+"-Popular Movies");
                fetchMovies("popular");

                break;
            }
            case R.id.topRated:
            {
                progressBar.setVisibility(View.VISIBLE);
                setTitle(getString(R.string.app_name)+"- Top Rated Movies");
                fetchMovies("top_rated");
                break;
            }
            case R.id.favourites:
            {

                progressBar.setVisibility(View.VISIBLE);
                setTitle(getString(R.string.app_name)+"- Favourites");
                getFavouriteMovies();
                break;

            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void fetchMovies(String query)
    {
        Internet internet=new Internet();
        if (!internet.checkConnection())
        {
            Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
            return;
        }
        movieRecyclerview.setItemAnimator(null);
        movieRecyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        movieRecyclerview.setHasFixedSize(true);
        viewmodel= ViewModelProviders.of(this).get(Viewmodel.class);
        viewmodel.setQuery(query);
        viewmodel.getMutableLiveData().observe(this, new Observer<ArrayList<Model>>() {
            @Override
            public void onChanged(ArrayList<Model> models)
            {
                if (models.size()==0)
                {
                    Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_SHORT).show();
                    return;
                }

                adapter=new Adapter(getApplicationContext(),models);
                movieRecyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);


            }

        });

    }
    public void getFavouriteMovies()
    {
        List<Model> models=new ArrayList<>();
        movieRecyclerview.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        movieRecyclerview.setHasFixedSize(true);
        viewmodel= ViewModelProviders.of(this).get(Viewmodel.class);
        viewmodel.getFavouriteMovies().observe(this, new Observer<List<Favourite>>()
        {
            @Override
            public void onChanged(List<Favourite> favourites)
            {
                models.clear();
                for (int i=0;i<favourites.size();i++)
                {
                    Model model=new Model(favourites.get(i).getOriginalTitle(),favourites.get(i).getMovieImage()
                    ,favourites.get(i).getUserRating(),favourites.get(i).getRelaseDate(),favourites.get(i).getOverView()
                    ,favourites.get(i).getMovieId());

                    models.add(model);
                }
                adapter=new Adapter(getApplicationContext(),models);
                movieRecyclerview.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);

            }
        });
    }
}
