package com.mechanical_man.theatrics.ui;

import com.mechanical_man.theatrics.api.MovieDetail;
import com.mechanical_man.theatrics.api.MovieService;
import com.mechanical_man.theatrics.mvp.Presenter;
import com.mechanical_man.theatrics.mvp.Result;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * Created by Mechanical Man, LLC on 3/6/17. Theatrics
 */

public class DetailsPresenter implements Presenter<DetailsView> {

    private BehaviorSubject<Result<MovieDetail>> subject = BehaviorSubject.create();
    private Disposable disposable;

    private final MovieService movieService;

    public DetailsPresenter(MovieService movieService) {
        this.movieService = movieService;
    }

    public void getMovieDetails(int movieId) {
        movieService.getMovieDetails(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(Result::from)
                .onErrorReturn(Result::error)
                .subscribe(subject::onNext);
    }

    @Override
    public void attachView(DetailsView view) {
        disposable = subject.subscribe(detailResult -> {
            if (detailResult.isSuccess()) {
                view.showDetails(detailResult.value());
            } else {

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
