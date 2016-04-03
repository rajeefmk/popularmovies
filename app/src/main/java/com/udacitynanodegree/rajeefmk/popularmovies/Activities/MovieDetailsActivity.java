package com.udacitynanodegree.rajeefmk.popularmovies.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.udacitynanodegree.rajeefmk.popularmovies.Models.Movie;
import com.udacitynanodegree.rajeefmk.popularmovies.R;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.AppUtils;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.Constants;

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
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                setTitle(getString(R.string.title_movie_details));
            }

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
        super.onCreateOptionsMenu(menu);
        if (!isFavorite()) {
            getMenuInflater().inflate(R.menu.menu_movie_details, menu);
            return true;
        }
        return false;
    }

    private boolean isFavorite() {
        Movie mMovie = new Select().from(Movie.class).where("movieId = ?", movie.getMovieId()).executeSingle();
        return mMovie != null && mMovie.isFavorite();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.action_favorite_movie) {
            movie.setIsFavorite(true);
            movie.save();
            Toast.makeText(MovieDetailsActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            invalidateOptionsMenu();
            return true;
        }
        return false;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
}
