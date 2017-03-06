package com.mechanical_man.theatrics.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

public final class Movie {

    public int id;

    public String title;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public String releaseDate;

    public String getReleaseYear() {
        return ApiHelper.getReleaseYear(releaseDate);
    }

}
