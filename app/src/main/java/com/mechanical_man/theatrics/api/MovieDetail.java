package com.mechanical_man.theatrics.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

final class MovieDetail {
    String title;

    @SerializedName("poster_path")
    String posterPath;

    @SerializedName("primary_release_year")
    String releaseYear;

    @SerializedName("overview")
    String description;

    /**
     * runtime is in minutes
     */
    int runtime;

    @SerializedName("popularity")
    double rating;
}
