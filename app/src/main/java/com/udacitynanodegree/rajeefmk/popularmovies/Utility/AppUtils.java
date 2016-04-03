package com.udacitynanodegree.rajeefmk.popularmovies.Utility;

import com.udacitynanodegree.rajeefmk.popularmovies.Models.Movie;

/**
 * Created by rmk on 3/4/16.
 */
public class AppUtils {

    public static String generateThumbnailUrl(String posterPath) {
        return Constants.IMAGE_BASE_URL + Constants.IMAGE_SIZE_PARAMETER + posterPath;
    }
}
