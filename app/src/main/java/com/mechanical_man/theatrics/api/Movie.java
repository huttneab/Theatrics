package com.mechanical_man.theatrics.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

final class Movie {
    int id;

    String title;

    @SerializedName("poster_path")
    String posterPath;

    @SerializedName("primary_release_year")
    String releaseYear;
}
