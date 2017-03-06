package com.mechanical_man.theatrics.api;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mechanical Man, LLC on 3/6/17. Theatrics
 */

@Singleton
public class MockMovieService implements MovieService {

    @Inject
    public MockMovieService() {
    }

    @Override
    public Observable<MovieResults> searchMovies(@Query("query") String query) {
        return Observable.just(MockResults.getMockSearchResults());
    }

    @Override
    public Observable<MovieDetail> getMovieDetails(@Path("id") int id) {
        return Observable.just(MockResults.getMockMovieDetail());
    }
}
