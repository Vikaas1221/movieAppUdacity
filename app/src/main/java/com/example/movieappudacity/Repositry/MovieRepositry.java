package com.example.movieappudacity.Repositry;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.movieappudacity.Databse.MovieDatabase;
import com.example.movieappudacity.Models.Favourite;
import com.example.movieappudacity.Models.Model;
import com.example.movieappudacity.Models.ReviewsModel;
import com.example.movieappudacity.Models.TrailerModel;
import com.example.movieappudacity.Networks.Internet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.movieappudacity.Activities.MainActivity.context;

public class MovieRepositry
{
    String call="";
    String API_KEY="";
    Internet internet;


    public MutableLiveData<ArrayList<Model>> getAllMovies(String query)
    {

        API_KEY="https://api.themoviedb.org/3/movie/"+query+"?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US&page=1";
        Log.d("qwert","In repo");
        final MutableLiveData<ArrayList<Model>> data = new MutableLiveData<>();
        final ArrayList<Model> arrayList=new ArrayList<>();
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, API_KEY, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d("iu4","inside json"+query);
                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject object= null;
                        try {
                            object = (JSONObject) jsonArray.get(i);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String original_name= null;
                        try {
                            original_name = object.getString("original_title");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String image_url= null;
                        try {
                            image_url = object.getString("poster_path");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String user_Rating= null;
                        try {
                            user_Rating = object.getString("vote_average");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String Relase_date= null;
                        try {
                            Relase_date = object.getString("release_date");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String overview= null;
                        try {
                            overview = object.getString("overview");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String id= null;
                        try {
                            id = object.getString("id");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Model model=new Model(original_name,image_url,user_Rating,Relase_date,overview,id);
                        Log.d("poiy",""+query+"(("+model.getOriginalTitle());
                        arrayList.add(model);
                        Log.d("added","added sucess"+query);

                    }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public MutableLiveData<ArrayList<TrailerModel>> getAllTrailers(String id)
    {
         final String TrailersAPIKEY="https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US";
        final MutableLiveData<ArrayList<TrailerModel>> data = new MutableLiveData<>();
        final ArrayList<TrailerModel> arrayList=new ArrayList<>();
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, TrailersAPIKEY, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String key= null;
                    try {
                        key = object.getString("key");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    TrailerModel model=new TrailerModel(key);
                    arrayList.add(model);
                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public MutableLiveData<ArrayList<ReviewsModel>> getAllReviews(String id)
    {
        final String TrailersAPIKEY="https://api.themoviedb.org/3/movie/"+id+"/reviews?api_key=1f59ebe04c42625fc6c290dccb75e0d5&language=en-US";
        final MutableLiveData<ArrayList<ReviewsModel>> data = new MutableLiveData<>();
        final ArrayList<ReviewsModel> arrayList=new ArrayList<>();
        JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET, TrailersAPIKEY, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                JSONArray jsonArray= null;
                try {
                    jsonArray = response.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                arrayList.clear();
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object= null;
                    try {
                        object = (JSONObject) jsonArray.get(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Author= null;
                    try {
                        Author = object.getString("author");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String Content= null;
                    try {
                        Content = object.getString("content");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    ReviewsModel model=new ReviewsModel(Author,Content);
                    arrayList.add(model);
                }
                data.setValue(arrayList);

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(context,"Something wrong",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(objectRequest);
        return data;

    }
    public LiveData<List<Favourite>> getAllFavourites()
    {
        LiveData<List<Favourite>> favData;
        Model model;
        MovieDatabase mDb=MovieDatabase.getsInstance(context);
        favData=mDb.movieDao().loadAllMovies();
        return favData;
    }



}
