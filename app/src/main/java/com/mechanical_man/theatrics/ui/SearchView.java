package com.mechanical_man.theatrics.ui;


import com.mechanical_man.theatrics.api.Movie;

import java.util.List;

/**
 * Created by Mechanical Man, LLC on 3/5/17. Theatrics
 */

public interface SearchView {

    void showResults(final List<Movie> movies);
}
