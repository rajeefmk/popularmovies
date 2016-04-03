package com.udacitynanodegree.rajeefmk.popularmovies.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.udacitynanodegree.rajeefmk.popularmovies.Adapters.ReviewListAdapter;
import com.udacitynanodegree.rajeefmk.popularmovies.Models.Review;
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

public class ReviewActivity extends AppCompatActivity {

    @Bind(R.id.review_list)
    RecyclerView reviewListView;

    ReviewListAdapter mReviewListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        ButterKnife.bind(this);
        int movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
        if (movieId != -1) {
            setViews(movieId);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            finish();
        }
    }

    private void setViews(int movieId) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        reviewListView.setHasFixedSize(true);
        reviewListView.setLayoutManager(mLayoutManager);
        mReviewListAdapter = new ReviewListAdapter(this);
        reviewListView.setAdapter(mReviewListAdapter);
        downloadReviews(movieId);
    }

    private void downloadReviews(int movieId) {
        JsonObjectRequest popularMovieListRequest = new JsonObjectRequest(Request.Method.GET, getReviewsUrl(movieId), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    handleResponse(response);
                } else {
                    Toast.makeText(ReviewActivity.this, getString(R.string.toast_message_no_reviews), Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReviewActivity.this, getString(R.string.toast_message_no_reviews), Toast.LENGTH_SHORT).show();
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
            JSONArray reviewList = response.getJSONArray(Constants.REVIEW_LIST_ARRAY);
            if (reviewList.length() != 0) {
                List<Review> reviewDataset = new ArrayList<>();

                for (int i = 0; i < reviewList.length(); i++) {
                    JSONObject reviewObject = reviewList.getJSONObject(i);
                    Review review = new Review();
                    review.setId(reviewObject.getString(Constants.REVIEW_ID));
                    review.setAuthor(reviewObject.getString(Constants.REVIEW_AUTHOR));
                    review.setContent(reviewObject.getString(Constants.REVIEW_CONTENT));
                    review.setUrl(reviewObject.getString(Constants.REVIEW_URL));
                    reviewDataset.add(review);
                }

                mReviewListAdapter.setDataset(reviewDataset);
                mReviewListAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.toast_message_no_reviews), Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

     /*
    Keys.TMDB_API_KEY to be replaced with tester's API_KEY from TMDB
     */

    private String getReviewsUrl(int movieId) {
        return Constants.GET_REVIEWS.replace("XXXX", String.valueOf(movieId)) + Keys.TMDB_API_KEY;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
