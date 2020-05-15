package com.example.movieappudacity.Networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.movieappudacity.Activities.MainActivity;

public class Internet
{
    public boolean checkConnection()
    {
        ConnectivityManager cm =
                (ConnectivityManager) MainActivity.context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
