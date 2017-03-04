package com.mechanical_man.theatrics;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

@Module
public class TheatricsModule {
    private final TheatricsApplication application;

    public TheatricsModule(TheatricsApplication application) {
        this.application = application;
    }

    @Provides @Singleton @ForApplication
    Context provideApplicationContext(){
        return application;
    }
}
