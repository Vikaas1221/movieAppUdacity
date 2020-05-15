package com.example.movieappudacity.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.movieappudacity.Models.Favourite;
import com.example.movieappudacity.Models.Model;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MovieDao
{
    @Query("Select * from Movie order by id")
    LiveData<List<Favourite>> loadAllMovies();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Favourite favourite);

    @Delete
    void deleteTask(Favourite favourite);

    @Query("Select * from Movie where originalTitle = :originalTitle")
    Favourite searchMovie(String originalTitle);

    @Query("Delete from Movie where originalTitle = :originalTitle")
    void deleteMovie(String originalTitle);
}
