package com.udacitynanodegree.rajeefmk.popularmovies.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.udacitynanodegree.rajeefmk.popularmovies.Adapters.TrailerListAdapter;
import com.udacitynanodegree.rajeefmk.popularmovies.Models.Trailer;
import com.udacitynanodegree.rajeefmk.popularmovies.PopularMoviesApplication;
import com.udacitynanodegree.rajeefmk.popularmovies.R;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.Constants;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.Keys;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TrailerActivity extends AppCompatActivity {

    TrailerListAdapter mTrailerListAdapter;

    @Bind(R.id.trailer_list)
    RecyclerView trailerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);
        ButterKnife.bind(this);
        int movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
        if (movieId != -1) {
            setViews(movieId);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        } else {
            finish();
        }
    }

    private void setViews(int movieId) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        trailerListView.setHasFixedSize(true);
        trailerListView.setLayoutManager(mLayoutManager);
        mTrailerListAdapter = new TrailerListAdapter(this);
        trailerListView.setAdapter(mTrailerListAdapter);
        downloadTrailers(movieId);
    }

    private void downloadTrailers(int movieId) {
        JsonObjectRequest popularMovieListRequest = new JsonObjectRequest(Request.Method.GET, getTrailerListUrl(movieId), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    handleResponse(response);
                } else {
                    Toast.makeText(TrailerActivity.this, getString(R.string.toast_message_no_trailer), Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TrailerActivity.this, getString(R.string.toast_message_no_trailer), Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        popularMovieListRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 10,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        PopularMoviesApplication.getInstance().addToRequestQueue(popularMovieListRequest);
    }

    private void handleResponse(JSONObject response) {
        try {
            JSONArray trailerList = response.getJSONArray(Constants.TRAILER_LIST_ARRAY);
            if (trailerList.length() != 0) {
                List<Trailer> trailerDataset = new ArrayList<>();

                for (int i = 0; i < trailerList.length(); i++) {
                    JSONObject trailerObject = trailerList.getJSONObject(i);
                    Trailer trailer = new Trailer();
                    trailer.setId(trailerObject.getString(Constants.TRAILER_ID));
                    trailer.setName(trailerObject.getString(Constants.TRAILER_NAME));
                    trailer.setKey(trailerObject.getString(Constants.TRAILER_KEY));
                    trailer.setSite(trailerObject.getString(Constants.TRAILER_SITE));
                    trailer.setSize(trailerObject.getInt(Constants.TRAILER_SIZE));
                    trailerDataset.add(trailer);
                }

                mTrailerListAdapter.setDataset(trailerDataset);
                mTrailerListAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, getString(R.string.toast_message_no_trailer), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    Keys.TMDB_API_KEY to be replaced with tester's API_KEY from TMDB
     */

    private String getTrailerListUrl(int movieId) {
        return Constants.GET_TRAILERS.replace("XXXX", String.valueOf(movieId)) + Keys.TMDB_API_KEY;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
