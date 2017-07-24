package com.jagamypriera.framework.dagger;

import android.app.Application;
import android.content.Context;

import com.jagamypriera.framework.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Taufik Akbar on 19/12/2016.
 */

@Module
public class AppModule {
    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return app;
    }
}
