package com.mechanical_man.theatrics;

import android.app.Application;

import com.mechanical_man.theatrics.api.ApiModule;
import com.mechanical_man.theatrics.ui.DetailsActivity;
import com.mechanical_man.theatrics.ui.SearchActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

public class TheatricsApplication extends Application {

    @Singleton
    @Component(modules = {TheatricsModule.class, ApiModule.class})
    public interface ApplicationComponent {
        void inject(TheatricsApplication application);
        void inject(SearchActivity activity);
        void inject(DetailsActivity activity);
    }

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerTheatricsApplication_ApplicationComponent.builder()
                .theatricsModule(new TheatricsModule(this))
                .apiModule(new ApiModule())
                .build();
        component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }

}
