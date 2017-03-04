package com.mechanical_man.theatrics;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

public class TheatricsApplication extends Application {

    @Singleton
    @Component(modules = TheatricsModule.class)
    public interface ApplicationComponent {
        void inject(TheatricsApplication application);
    }

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerTheatricsApplication_ApplicationComponent.builder()
                .theatricsModule(new TheatricsModule(this))
                .build();
        component.inject(this);
    }

    public ApplicationComponent component() {
        return component;
    }

}
