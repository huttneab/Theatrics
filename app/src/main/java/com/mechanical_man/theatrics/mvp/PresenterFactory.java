package com.mechanical_man.theatrics.mvp;

import android.support.annotation.NonNull;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

public interface PresenterFactory<P extends Presenter> {
    @NonNull P createPresenter();
}
