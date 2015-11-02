package com.udacitynanodegree.rajeefmk.popularmovies.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacitynanodegree.rajeefmk.popularmovies.R;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        if (getIntent().getExtras() != null) {
            populateViews();
        }
    }

    private void populateViews() {
        LoadImage();
        movieTitle.setText(getMovieTitle());
        movieRating.setText(getRating());
        releaseDate.setText(getReleaseDate());
        synopsisDetail.setText(getSynopsis());

        Log.d("test", getMovieTitle());
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
                .load(generateThumbnailUrl(getIntent().getStringExtra(Constants.MOVIE_POSTER_PATH)))
                .into(movieThumbnail);
    }

    private String generateThumbnailUrl(String posterPath) {
        return Constants.IMAGE_BASE_URL + Constants.IMAGE_SIZE_PARAMETER + posterPath;
    }

    private String getRating() {
        return "Rating: " + String.valueOf(getIntent().getIntExtra(Constants.MOVIE_VOTE_AVERAGE, 0));
    }

}
