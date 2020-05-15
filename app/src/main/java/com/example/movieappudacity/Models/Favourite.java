package com.example.movieappudacity.Models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
@Entity(tableName = "Movie")
public class Favourite
{
    @PrimaryKey
    @NonNull
    private String id;
    private String originalTitle;
    private String movieImage;
    private String userRating;
    private String RelaseDate;
    private String overView;






    public Favourite(String originalTitle,String movieImage,String userRating,String RelaseDate,String overView,String id)
    {
        this.originalTitle=originalTitle;
        this.movieImage=movieImage;
        this.userRating=userRating;
        this.RelaseDate=RelaseDate;
        this.overView=overView;
        this.id=id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getRelaseDate() {
        return RelaseDate;
    }

    public void setRelaseDate(String relaseDate) {
        RelaseDate = relaseDate;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
    public String id() {
        return id;
    }

    public void setMovieid(String id) {
        this.id = id;
    }
//public String getMovieId() {
//    return movieId;
//}
//
//    public void setMovieId(String movieId) {
//        this.movieId = movieId;
//    }


}
