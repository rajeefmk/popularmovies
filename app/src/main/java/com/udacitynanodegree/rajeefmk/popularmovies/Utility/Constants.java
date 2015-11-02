package com.udacitynanodegree.rajeefmk.popularmovies.Utility;

/**
 * Created by rmk on 7/10/15.
 */
public class Constants {

    public static final String BASE_URL_MOVIE_DISCOVERY = "http://api.themoviedb.org/3/discover/movie?sort_by=XXXX&api_key=";
    public static final String SORT_CRITERIA_POPULARITY = "popularity.desc";
    public static final String NO_SORT_CRITERIA = "";
    public static final String SORT_CRITERIA_RATING = "vote_average.desc";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p";
    public static final String IMAGE_SIZE_PARAMETER = "/w185";

    /* JSONObject and JSONArray keys */
    public static final String MOVIE_LIST_ARRAY = "results";
    public static final String SELECTED_MOVIE_OBJECT = "movie";

    public static final String MOVIE_ID = "id";
    public static final String MOVIE_ADULT = "adult";
    public static final String MOVIE_BACKDROP_PATH = "backdrop_path";
    public static final String MOVIE_ORIGINAL_LANGUAGE = "original_language";
    public static final String MOVIE_ORIGINAL_TITLE = "original_title";
    public static final String MOVIE_OVERVIEW = "overview";
    public static final String MOVIE_RELEASE_DATE = "release_date";
    public static final String MOVIE_POSTER_PATH = "poster_path";
    public static final String MOVIE_POPULARITY = "popularity";
    public static final String MOVIE_TITLE = "title";
    public static final String MOVIE_VIDEO = "video";
    public static final String MOVIE_VOTE_AVERAGE = "vote_average";
    public static final String MOVIE_VOTE_COUNT = "vote_count";

}
