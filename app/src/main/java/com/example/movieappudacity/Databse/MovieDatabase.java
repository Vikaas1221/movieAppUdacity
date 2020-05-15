package com.example.movieappudacity.Databse;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movieappudacity.DAO.MovieDao;
import com.example.movieappudacity.Models.Favourite;

@Database(entities = {Favourite.class},version = 2,exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase
{
    private static final String LOG_TAG = MovieDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "movieslist";
    private static MovieDatabase sInstance;

    public static MovieDatabase getsInstance(Context context)
    {
        if (sInstance==null)
        {
            synchronized (LOCK)
            {
                sInstance=Room.databaseBuilder(context.getApplicationContext(),MovieDatabase.class,MovieDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return sInstance;
    }
    public abstract MovieDao movieDao();

}
