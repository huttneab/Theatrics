package com.mechanical_man.theatrics.api;

import android.app.Application;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by Mechanical Man, LLC on 3/4/17. Theatrics
 */

@Module
public class ApiModule {
    private static final String baseUrl = "https://api.themoviedb.org/3/";
    private static final int cacheSize = 10 * 1024 * 1024; // 10 MiB

    @Provides
    @Singleton
    Picasso providePicasso(Application application) {
        return Picasso.with(application);
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOKHttpClient(Cache cache) {
        return new OkHttpClient.Builder().cache(cache).build();
    }

    @Provides
    @Singleton
    NetworkBehavior provideNetworkBehavior() {
        NetworkBehavior behavior = NetworkBehavior.create();

        // normally these would be variable, but we'll just keep it simple for now
        behavior.setDelay(0, MILLISECONDS);
        behavior.setFailurePercent(0);
        behavior.setVariancePercent(0);
        return behavior;
    }

    @Provides
    @Singleton
    MockRetrofit provideMockRetrofit(Retrofit retrofit, NetworkBehavior networkBehavior) {
        return new MockRetrofit.Builder(retrofit)
                .networkBehavior(networkBehavior)
                .build();
    }

    @Provides
    @Singleton
    MovieService provideMovieService(MockMovieService mockMovieService) {
        return mockMovieService;
    }

    public static Uri generatePosterUrl(String path) {
        if (null == path) {
            return null;
        }

        return Uri.parse("https://image.tmdb.org/t/p/w500").buildUpon()
                .appendEncodedPath(path)
                .build();
    }
}
