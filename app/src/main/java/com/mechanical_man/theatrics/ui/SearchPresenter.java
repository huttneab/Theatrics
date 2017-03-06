package com.mechanical_man.theatrics.ui;

import com.mechanical_man.theatrics.api.MovieService;
import com.mechanical_man.theatrics.mvp.Presenter;

/**
 * Created by Mechanical Man, LLC on 3/5/17. Theatrics
 */

public class SearchPresenter implements Presenter<SearchView> {

    private final MovieService movieService;

    public SearchPresenter(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void attachView(SearchView view) {

    }

    @Override
    public void detachView() {

    }

    @Override
    public void destroy() {

    }
}
