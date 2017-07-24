package com.jagamypriera.framework;

import android.app.Application;

import com.jagamypriera.framework.dagger.DaggerAppComponent;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Taufik Akbar on 19/12/2016.
 */

public class App extends Application {
    private DaggerAppComponent.Builder builder;

    @Override
    public void onCreate() {
        super.onCreate();
        builder=DaggerAppComponent.builder();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/OpenSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
    public DaggerAppComponent.Builder getBuilder() {
        return builder;
    }
}
