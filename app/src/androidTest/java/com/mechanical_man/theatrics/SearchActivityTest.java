package com.mechanical_man.theatrics;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.mechanical_man.theatrics.api.MockResults;
import com.mechanical_man.theatrics.api.Movie;
import com.mechanical_man.theatrics.ui.SearchActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.assertj.android.recyclerview.v7.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.startsWith;


/**
 * Created by Mechanical Man, LLC on 3/6/17. Theatrics
 */

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {
    @BindView(R.id.recycler_view) RecyclerView movieResults;

    @Rule public final ActivityTestRule<SearchActivity> main =
            new ActivityTestRule<>(SearchActivity.class, false, true);


    @Test
    public void shouldShow_SearchResults() {
        ButterKnife.bind(this, main.getActivity());

        onView(withId(R.id.search_edittext)).perform(clearText(), typeText("search"));
        onView(withId(R.id.search_edittext))
                .perform(pressImeActionButton());

        // I know this is bad, but I ran out of time for thread pool executors
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(movieResults)
                .isShown();

        assertThat(movieResults.getAdapter())
                .hasItemCount(3);

        List<Movie> mockResults = MockResults.getMockSearchResults().results;

        for (Movie m : mockResults) {
            onView(withText(startsWith(m.title)))
                    .check(matches(isDisplayed()));
        }
    }

}
