package com.udacitynanodegree.rajeefmk.popularmovies.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.udacitynanodegree.rajeefmk.popularmovies.Adapters.MovieListAdapter;
import com.udacitynanodegree.rajeefmk.popularmovies.Models.Movie;
import com.udacitynanodegree.rajeefmk.popularmovies.PopularMoviesApplication;
import com.udacitynanodegree.rajeefmk.popularmovies.R;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.Constants;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.Keys;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private MovieListAdapter mMovieListAdapter;
    private String defaultSortCriteria = Constants.NO_SORT_CRITERIA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        GridView mMovieListView = (GridView) findViewById(R.id.movieListView);
        mMovieListAdapter = new MovieListAdapter(MovieListActivity.this);
        mMovieListView.setAdapter(mMovieListAdapter);
    }


    private void downLoadList(String sortCriteria) {
        if (sortCriteria.contentEquals(Constants.SORT_CRITERIA_FAVORITES)) {
            retrieveFavouriteMovieAndDisplay();
            return;
        }
        JsonObjectRequest popularMovieListRequest = new JsonObjectRequest(Request.Method.GET, getPopularMovielistUrl(sortCriteria), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    handleResponse(response);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        popularMovieListRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 10,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        PopularMoviesApplication.getInstance().addToRequestQueue(popularMovieListRequest);

    }

    private void handleResponse(JSONObject response) {
        try {
            JSONArray movieListArray = response.getJSONArray(Constants.MOVIE_LIST_ARRAY);
            List<Movie> movieListDataset = new ArrayList<>();

            for (int i = 0; i < movieListArray.length(); i++) {
                JSONObject movieObject = movieListArray.getJSONObject(i);
                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                Movie movie = gson.fromJson(String.valueOf(movieObject), Movie.class);
                movieListDataset.add(movie);
            }

            mMovieListAdapter.setDataset(movieListDataset);
            mMovieListAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*
    Keys.TMDB_API_KEY to be replaced with tester's API_KEY from TMDB
     */
    private String getPopularMovielistUrl(String sortCriteria) {
        return Constants.BASE_URL_MOVIE_DISCOVERY.replace("XXXX", sortCriteria) + Keys.TMDB_API_KEY;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_highest_rated) {
            setTitle(getString(R.string.action_highest_rated));
            downLoadList(Constants.SORT_CRITERIA_RATING);
            defaultSortCriteria = Constants.SORT_CRITERIA_RATING;
            return true;
        } else if (id == R.id.action_most_popular) {
            setTitle(getString(R.string.action_popular_movies));
            downLoadList(Constants.SORT_CRITERIA_POPULARITY);
            defaultSortCriteria = Constants.SORT_CRITERIA_POPULARITY;
            return true;
        } else if (id == R.id.action_favorite_movie) {
            setTitle(getString(R.string.action_favorite_movie));
            downLoadList(Constants.SORT_CRITERIA_FAVORITES);
            defaultSortCriteria = Constants.SORT_CRITERIA_FAVORITES;
            return true;
        }

        return false;
    }

    private void retrieveFavouriteMovieAndDisplay() {
        List<Movie> movieList = new Select().from(Movie.class).where("isFavorite = ? ", true).execute();
        mMovieListAdapter.setDataset(movieList);
        mMovieListAdapter.notifyDataSetChanged();
        if (movieList.size() == 0)
            Toast.makeText(this, getString(R.string.error_message_no_fav_movies), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        downLoadList(defaultSortCriteria);
    }
}
