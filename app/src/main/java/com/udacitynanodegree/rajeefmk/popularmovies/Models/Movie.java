package com.udacitynanodegree.rajeefmk.popularmovies.Models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rmk on 2/11/15.
 */

@Table(name = "Favourites")
public class Movie extends Model {

    @Expose
    @SerializedName("id")
    @Column(name = "movieId")
    int movieId;

    @Expose
    @SerializedName("adult")
    boolean isAdultMovie;

    @Expose
    @SerializedName("backdrop_path")
    String backdropPath;

    @Expose
    @SerializedName("original_language")
    String originalLanguage;

    @Expose
    @SerializedName("original_title")
    String originalTitle;

    @Expose
    @SerializedName("overview")
    String overView;

    @Expose
    @SerializedName("release_date")
    String releaseDate;

    @Expose
    @SerializedName("poster_path")
    String posterPath;

    @Expose
    float popularity;

    @Expose
    @SerializedName("title")
    String movieTitle;

    @Expose
    @SerializedName("video")
    boolean video;

    @Expose
    @SerializedName("vote_average")
    float voteAverage;

    @Expose
    @SerializedName("vote_count")
    int voteCount;

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int id) {
        this.movieId = id;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isAdultMovie() {
        return isAdultMovie;
    }

    public void setAdult(boolean adult) {
        this.isAdultMovie = adult;
    }

}
