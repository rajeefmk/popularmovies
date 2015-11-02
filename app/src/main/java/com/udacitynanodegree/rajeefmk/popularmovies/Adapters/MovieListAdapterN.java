package com.udacitynanodegree.rajeefmk.popularmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacitynanodegree.rajeefmk.popularmovies.Activities.MovieDetailsActivity;
import com.udacitynanodegree.rajeefmk.popularmovies.Models.Movie;
import com.udacitynanodegree.rajeefmk.popularmovies.R;
import com.udacitynanodegree.rajeefmk.popularmovies.Utility.Constants;

import java.util.ArrayList;
import java.util.List;

public class MovieListAdapterN extends BaseAdapter {

    private final Context mContext;
    private List<Movie> mDataset = new ArrayList<>();

    public MovieListAdapterN(Context context) {
        mContext = context;
    }

    public void setDataset(List<Movie> movieList) {
        mDataset = movieList;
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public Movie getItem(int position) {
        return mDataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView movieThumbnail;
        final ProgressBar mProgressBar;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_grid_item, parent, false);
        }

        movieThumbnail = (ImageView) convertView.findViewById(R.id.movieThumbnail);
        mProgressBar = (ProgressBar) convertView.findViewById(R.id.progressBar);

        Picasso.with(mContext)
                .load(generateThumbnailUrl(getItem(position)))
                .into(movieThumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                });

        movieThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext, MovieDetailsActivity.class);
                mIntent.putExtra(Constants.MOVIE_TITLE, getItem(position).getTitle());
                mIntent.putExtra(Constants.MOVIE_VOTE_AVERAGE, getItem(position).getVote_average());
                mIntent.putExtra(Constants.MOVIE_RELEASE_DATE, getItem(position).getRelease_date());
                mIntent.putExtra(Constants.MOVIE_OVERVIEW, getItem(position).getOverview());
                mIntent.putExtra(Constants.MOVIE_POSTER_PATH, getItem(position).getPoster_path());
                mIntent.putExtra(Constants.MOVIE_BACKDROP_PATH, getItem(position).getBackdrop_path());

                mContext.startActivity(mIntent);
            }
        });
        return convertView;
    }

    private String generateThumbnailUrl(Movie movie) {
        return Constants.IMAGE_BASE_URL + Constants.IMAGE_SIZE_PARAMETER + movie.getPoster_path();
    }
}
