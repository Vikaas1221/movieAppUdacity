package com.example.movieappudacity.Viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.movieappudacity.Models.Favourite;
import com.example.movieappudacity.Models.Model;
import com.example.movieappudacity.Models.ReviewsModel;
import com.example.movieappudacity.Models.TrailerModel;
import com.example.movieappudacity.Repositry.MovieRepositry;

import java.util.ArrayList;
import java.util.List;

public class Viewmodel extends ViewModel
{
    LiveData<ArrayList<Model>> mutableLiveData;
    LiveData<ArrayList<TrailerModel>> TrailermutableLiveData;
    LiveData<ArrayList<ReviewsModel>> ReviewsmutableLiveData;
    LiveData<List<Favourite>> FavmutableLiveData;
    MutableLiveData<ArrayList<Model>> mutableLiveDataTop_Rated;
    MutableLiveData<String> queryMutable=new MutableLiveData<>();
    MovieRepositry repositry=null;
    public Viewmodel()
    {
        repositry=new MovieRepositry();
    }
    public void setQuery(String query)
    {
        queryMutable.setValue(query);
    }


    public LiveData<ArrayList<Model>> getMutableLiveData()
    {
        Log.d("uytr","int viewmodel");
        if (mutableLiveData==null)
        {
          //  Transformations.map(queryMutable,input -> repositry.getAllMovies(queryMutable.getValue()));
            mutableLiveData= Transformations.switchMap(queryMutable,input->repositry.getAllMovies(queryMutable.getValue()));
        }
        return mutableLiveData;
    }
    public LiveData<ArrayList<TrailerModel>> getTrailerData(String id)
    {
        if (TrailermutableLiveData==null)
        {
            TrailermutableLiveData=repositry.getAllTrailers(id);
        }
        return TrailermutableLiveData;
    }
    public LiveData<ArrayList<ReviewsModel>> getReviewsData(String id)
    {
        if (ReviewsmutableLiveData==null)
        {
            ReviewsmutableLiveData=repositry.getAllReviews(id);
        }
        return ReviewsmutableLiveData;
    }
    public LiveData<List<Favourite>> getFavouriteMovies()
    {
        if (FavmutableLiveData==null)
        {
            FavmutableLiveData=repositry.getAllFavourites();
        }
        return FavmutableLiveData;

    }

}
