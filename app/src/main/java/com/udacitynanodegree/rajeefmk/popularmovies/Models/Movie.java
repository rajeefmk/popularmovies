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
    @Column(name = "movieId", unique = true)
    int movieId;

    @Expose
    @SerializedName("adult")
    @Column(name = "isAdultMovie")
    boolean isAdultMovie;

    @Expose
    @SerializedName("backdrop_path")
    @Column(name = "backdopPath")
    String backdropPath;

    @Expose
    @SerializedName("original_language")
    @Column(name = "originalLanguage")
    String originalLanguage;

    @Expose
    @SerializedName("original_title")
    @Column(name = "originalTitle")
    String originalTitle;

    @Expose
    @SerializedName("overview")
    @Column(name = "overView")
    String overView;

    @Expose
    @SerializedName("release_date")
    @Column(name = "releaseDate")
    String releaseDate;

    @Expose
    @SerializedName("poster_path")
    @Column(name = "posterPath")
    String posterPath;

    @Expose
    @Column(name = "popularity")
    float popularity;

    @Expose
    @SerializedName("title")
    @Column(name = "movieTitle")
    String movieTitle;

    @Expose
    @SerializedName("video")
    @Column(name = "video")
    boolean video;

    @Expose
    @SerializedName("vote_average")
    @Column(name = "voteAverage")
    float voteAverage;

    @Expose
    @SerializedName("vote_count")
    @Column(name = "voteCount")
    int voteCount;

    @Column(name = "isFavorite")
    boolean isFavorite;

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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
