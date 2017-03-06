package com.mechanical_man.theatrics.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mechanical_man.theatrics.R;
import com.mechanical_man.theatrics.api.ApiModule;
import com.mechanical_man.theatrics.api.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Mechanical Man, LLC on 3/5/17. Theatrics
 */

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.ViewHolder> {

    private final PublishSubject<Movie> onClickSubject = PublishSubject.create();

    private List<Movie> movies;
    private Picasso picasso;

    public SearchResultsAdapter(List<Movie> movies, Picasso picasso) {
        this.movies = movies;
        this.picasso = picasso;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        String title = movie.title + " (" + movie.getReleaseYear() + ")";
        holder.title.setText(title);
        picasso
                .load(ApiModule.generatePosterUrl(movie.posterPath))
                .placeholder(R.drawable.default_poster_24dp)
                .into(holder.posterView);

        holder.itemView.setOnClickListener(v -> onClickSubject.onNext(movie));
    }

    @Override
    public int getItemCount() {
        return null == movies ? 0 : movies.size();
    }

    public Observable<Movie> getPositionClicks() {
        return onClickSubject;
    }

    public void setData(final List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.poster_view) ImageView posterView;
        @BindView(R.id.title) TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
