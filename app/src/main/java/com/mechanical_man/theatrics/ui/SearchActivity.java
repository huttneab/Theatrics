package com.mechanical_man.theatrics.ui;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mechanical_man.theatrics.R;
import com.mechanical_man.theatrics.TheatricsApplication;
import com.mechanical_man.theatrics.api.MovieService;
import com.mechanical_man.theatrics.mvp.PresenterFactory;
import com.mechanical_man.theatrics.mvp.PresenterManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchView, PresenterFactory<SearchPresenter> {
    @Inject PresenterManager presenterManager;
    @Inject MovieService movieService;


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    private SearchPresenter presenter;
    private SearchResultsAdapter resultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        ((TheatricsApplication) getApplication()).component().inject(this);

        setSupportActionBar(toolbar);
        presenter = presenterManager.getOrCreatePresenter(SearchPresenter.class, this);

        // recycler view components
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        resultsAdapter = new SearchResultsAdapter();
        recyclerView.setAdapter(resultsAdapter);
        recyclerView.setLayoutManager(layoutManager);

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
    }

    @Override
    public void showResults(List<Movie> movies) {

    }

    @NonNull
    @Override
    public SearchPresenter createPresenter() {
        return new SearchPresenter(movieService);
    }
}
