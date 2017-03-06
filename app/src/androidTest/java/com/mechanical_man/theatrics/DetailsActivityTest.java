package com.mechanical_man.theatrics;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mechanical_man.theatrics.api.MockResults;
import com.mechanical_man.theatrics.api.MovieDetail;
import com.mechanical_man.theatrics.ui.DetailsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import butterknife.BindView;
import butterknife.ButterKnife;

import static org.assertj.android.api.Assertions.assertThat;

/**
 * Created by Mechanical Man, LLC on 3/6/17. Theatrics
 */

@RunWith(AndroidJUnit4.class)
public class DetailsActivityTest {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.description) TextView description;
    @BindView(R.id.release_year) TextView releaseYear;
    @BindView(R.id.rating) RatingBar ratings;
    @BindView(R.id.run_time) TextView runtime;

    @Rule public final ActivityTestRule<DetailsActivity> main =
            new ActivityTestRule<>(DetailsActivity.class, false, false);

    @Test
    public void shouldShow_MovieDetails() {
        MovieDetail mockMovieDetail = MockResults.getMockMovieDetail();
        Intent intent = DetailsActivity.newInstance(
                1,
                mockMovieDetail.title,
                InstrumentationRegistry.getTargetContext());

        main.launchActivity(intent);
        ButterKnife.bind(this, main.getActivity());

        assertThat(title).hasText(mockMovieDetail.title);
        assertThat(releaseYear).hasText("2001");
        assertThat(ratings).hasRating((float) mockMovieDetail.rating / 2);
        assertThat(description).hasText(mockMovieDetail.description);
        assertThat(runtime).hasText(String.valueOf(mockMovieDetail.runtime));
    }
}

