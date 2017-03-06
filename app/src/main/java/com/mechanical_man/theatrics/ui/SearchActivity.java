package com.mechanical_man.theatrics.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.mechanical_man.theatrics.R;
import com.mechanical_man.theatrics.TheatricsApplication;
import com.mechanical_man.theatrics.api.Movie;
import com.mechanical_man.theatrics.api.MovieService;
import com.mechanical_man.theatrics.mvp.PresenterFactory;
import com.mechanical_man.theatrics.mvp.PresenterManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnEditorAction;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class SearchActivity extends AppCompatActivity implements SearchView, PresenterFactory<SearchPresenter> {
    @Inject PresenterManager presenterManager;
    @Inject MovieService movieService;
    @Inject Picasso picasso;


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private SearchPresenter presenter;
    private SearchResultsAdapter resultsAdapter;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        ((TheatricsApplication) getApplication()).component().inject(this);

        setSupportActionBar(toolbar);
        presenter = presenterManager.getOrCreatePresenter(SearchPresenter.class, this);

        // recycler view components
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        resultsAdapter = new SearchResultsAdapter(null, picasso);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(resultsAdapter);

        disposable = resultsAdapter.getPositionClicks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    Intent detailsIntent =
                            DetailsActivity.newInstance(movie.id, movie.title, SearchActivity.this);
                    startActivity(detailsIntent);
                });
    }

    @OnEditorAction(R.id.search_edittext)
    public boolean onSearch(TextView v, int actionId, KeyEvent event) {
        if (IME_ACTION_SEARCH == actionId) {
            presenter.search(v.getText().toString());

            // hide keyboard so we can see the results
            recyclerView.requestFocus();
            InputMethodManager inputMethodManager = (InputMethodManager)
                    getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            presenterManager.remove(SearchPresenter.class);
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void showResults(List<Movie> movies) {
        resultsAdapter.setData(movies);
    }

    @NonNull
    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter(movieService);
    }
}
