package com.mechanical_man.theatrics.ui;

import com.mechanical_man.theatrics.api.MovieResults;
import com.mechanical_man.theatrics.api.MovieService;
import com.mechanical_man.theatrics.mvp.Presenter;
import com.mechanical_man.theatrics.mvp.Result;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Mechanical Man, LLC on 3/5/17. Theatrics
 */

public class SearchPresenter implements Presenter<SearchView> {

    private BehaviorSubject<Result<MovieResults>> subject = BehaviorSubject.create();
    private Disposable disposable;

    private final MovieService movieService;
    public SearchPresenter(MovieService movieService) {
        this.movieService = movieService;
    }

    public void search(String query) {
        movieService.searchMovies(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(Result::from)
                .onErrorReturn(Result::error)
                .subscribe(subject::onNext);
    }

    @Override
    public void attachView(SearchView view) {

        disposable = subject.subscribe(listResult -> {
            if(listResult.isSuccess()){
                view.showResults(listResult.value().results);
            } else {
                view.showResults(null);
            }
        });
    }

    @Override
    public void detachView() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void destroy() {

    }
}
