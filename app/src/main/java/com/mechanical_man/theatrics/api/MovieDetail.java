package com.mechanical_man.theatrics.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

public final class MovieDetail {

    public int id;

    public String title;

    @SerializedName("backdrop_path")
    public String posterPath;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("overview")
    public String description;

    /**
     * runtime is in minutes
     */
    public int runtime;

    @SerializedName("popularity")
    public double rating;

    public String getReleaseYear() {
        return ApiHelper.getReleaseYear(releaseDate);
    }
}
