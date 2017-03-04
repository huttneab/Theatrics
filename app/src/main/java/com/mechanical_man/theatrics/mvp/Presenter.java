package com.mechanical_man.theatrics.mvp;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

    void destroy();
}
