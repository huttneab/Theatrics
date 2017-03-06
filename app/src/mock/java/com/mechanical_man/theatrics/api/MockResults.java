package com.mechanical_man.theatrics.api;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mechanical Man, LLC on 3/6/17. Theatrics
 */

public class MockResults {

    public static MovieResults getMockSearchResults() {
        Movie movie_1 = new Movie();
        movie_1.posterPath = null;
        movie_1.id = 1;
        movie_1.title = "good movie";
        movie_1.releaseDate = "2001-01-01";

        Movie movie_2 = new Movie();
        movie_2.posterPath = null;
        movie_2.id = 2;
        movie_2.title = "even better movie";
        movie_2.releaseDate = "2002-01-01";

        Movie movie_3 = new Movie();
        movie_3.posterPath = null;
        movie_3.id = 3;
        movie_3.title = "the best movie of all time";
        movie_3.releaseDate = "2003-01-01";

        List<Movie> movies = Arrays.asList(movie_1, movie_2, movie_3);

        MovieResults movieResults = new MovieResults();
        movieResults.results = movies;

        return movieResults;
    }

    public static MovieDetail getMockMovieDetail() {
        MovieDetail movieDetail = new MovieDetail();
        movieDetail.posterPath = null;
        movieDetail.releaseDate = "2001-01-01";
        movieDetail.description = "lorem ipsum";
        movieDetail.runtime = 137;
        movieDetail.title = "best movie of all time";
        movieDetail.rating = 10;

        return movieDetail;
    }
}
