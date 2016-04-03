package com.udacitynanodegree.rajeefmk.popularmovies.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.udacitynanodegree.rajeefmk.popularmovies.Models.Movie;
import com.udacitynanodegree.rajeefmk.popularmovies.R;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.AppUtils;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.Constants;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.PreferenceUtils;

import java.util.HashSet;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailsActivity extends AppCompatActivity {

    @Bind(R.id.title)
    TextView movieTitle;

    @Bind(R.id.rating)
    TextView movieRating;

    @Bind(R.id.releaseDate)
    TextView releaseDate;

    @Bind(R.id.synopsisDetail)
    TextView synopsisDetail;

    @Bind(R.id.movieThumbnail)
    ImageView movieThumbnail;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null) {
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            movie = gson.fromJson(getIntent().getStringExtra(Constants.SELECTED_MOVIE_OBJECT), Movie.class);
            populateViews();
            //  movieId = getIntent().getIntExtra(Constants.MOVIE_ID, -1);
            if (getSupportActionBar() != null)
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } else {
            finish();
        }
    }

    private void populateViews() {
        LoadImage();
        movieTitle.setText(movie.getMovieTitle());
        movieRating.setText(getRating());
        releaseDate.setText(movie.getReleaseDate());
        synopsisDetail.setText(movie.getOverView());

    }

    private String getReleaseDate() {
        return getIntent().getStringExtra(Constants.MOVIE_RELEASE_DATE);
    }

    private String getSynopsis() {
        return getIntent().getStringExtra(Constants.MOVIE_OVERVIEW);
    }

    private String getMovieTitle() {
        return getIntent().getStringExtra(Constants.MOVIE_TITLE);
    }

    private void LoadImage() {
        Picasso.with(this)
                .load(AppUtils.generateThumbnailUrl(movie.getPosterPath()))
                .into(movieThumbnail);
    }

    private String getRating() {
        return "Rating: " + String.valueOf(movie.getVoteAverage());
    }

    @OnClick(R.id.review_button)
    void reviewButtonClicked() {
        startActivity(new Intent(this, ReviewActivity.class).putExtra(Constants.MOVIE_ID, movie.getMovieId()));
    }

    @OnClick(R.id.trailer_button)
    void trailerButtonClicked() {
        startActivity(new Intent(this, TrailerActivity.class).putExtra(Constants.MOVIE_ID, movie.getMovieId()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isNotFavorite())
            getMenuInflater().inflate(R.menu.menu_movie_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private boolean isNotFavorite() {
        boolean isNotFavourite = true;
        String movieId = String.valueOf(getIntent().getIntExtra(Constants.MOVIE_ID, -1));
        HashSet<String> favoriteList = PreferenceUtils.getStringSet(this, Constants.FAVOURITE_LIST, null);
        if (favoriteList != null)
            for (String favouriteId : favoriteList) {
                if (favouriteId.contentEquals(movieId)) {
                    isNotFavourite = false;
                    break;
                }
            }
        return isNotFavourite;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.action_favorite_movie) {
            HashSet<String> favoriteList = PreferenceUtils.getStringSet(this, Constants.FAVOURITE_LIST, null);
            if (favoriteList != null) {
                favoriteList.add(String.valueOf(getIntent().getIntExtra(Constants.MOVIE_ID, -1)));
            } else {
                HashSet<String> hashSet = new HashSet<>();
                hashSet.add(String.valueOf(getIntent().getIntExtra(Constants.MOVIE_ID, -1)));
                PreferenceUtils.setStringSet(this, Constants.FAVOURITE_LIST, hashSet);
            }
            Toast.makeText(MovieDetailsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            invalidateOptionsMenu();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
