package com.mechanical_man.theatrics.api;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

public interface MovieService {
    //TODO find a better way of including the api key

    @GET("search/movie?api_key=1419277c31b39f8ca591b8da5d77b5f8&language=en-US")
    Observable<MovieResults> searchMovies(@Query("query") String query);

    @GET("movie/{id}?api_key=1419277c31b39f8ca591b8da5d77b5f8")
    Observable<MovieDetail> getMovieDetails(@Path("id") int id);
}
