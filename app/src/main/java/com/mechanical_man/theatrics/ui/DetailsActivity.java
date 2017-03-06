package com.mechanical_man.theatrics.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mechanical_man.theatrics.R;
import com.mechanical_man.theatrics.TheatricsApplication;
import com.mechanical_man.theatrics.api.ApiModule;
import com.mechanical_man.theatrics.api.MovieDetail;
import com.mechanical_man.theatrics.api.MovieService;
import com.mechanical_man.theatrics.mvp.PresenterFactory;
import com.mechanical_man.theatrics.mvp.PresenterManager;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements DetailsView,
        PresenterFactory<DetailsPresenter> {

    @Inject PresenterManager presenterManager;
    @Inject MovieService movieService;
    @Inject Picasso picasso;

    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.title) TextView title;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.poster_view) ImageView poster;
    @BindView(R.id.release_year) TextView releaseYear;
    @BindView(R.id.rating) RatingBar ratings;
    @BindView(R.id.run_time) TextView runtime;

    private DetailsPresenter presenter;

    public static Intent newInstance(int movieId, String movieTitle, Context context) {
        Intent detailsIntent = new Intent(context, DetailsActivity.class);
        detailsIntent.putExtra("movie_id", movieId);
        detailsIntent.putExtra("movie_title", movieTitle);
        return detailsIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        ((TheatricsApplication) getApplication()).component().inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = presenterManager.getOrCreatePresenter(DetailsPresenter.class, this);

        if (null == savedInstanceState) {
            Intent intent = getIntent();
            presenter.getMovieDetails(intent.getIntExtra("movie_id", 0));
            String titleText = intent.getStringExtra("movie_title");
            collapsingToolbar.setTitle(titleText);
            title.setText(titleText);
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
            presenterManager.remove(DetailsPresenter.class);
        }
    }

    @NonNull
    @Override
    public DetailsPresenter createPresenter() {
        return new DetailsPresenter(movieService);
    }

    @Override
    public void showDetails(MovieDetail movieDetail) {
        description.setText(movieDetail.description);
        picasso.load(ApiModule.generatePosterUrl(movieDetail.posterPath)).into(poster);
        runtime.setText(String.valueOf(movieDetail.runtime));
        releaseYear.setText(movieDetail.getReleaseYear());

        // show ratings out of 5 stars instead of 10
        ratings.setRating((float) (movieDetail.rating / 2));
    }
}
