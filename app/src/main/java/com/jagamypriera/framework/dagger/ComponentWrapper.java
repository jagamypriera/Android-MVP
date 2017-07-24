package com.jagamypriera.framework.dagger;

import javax.inject.Inject;

import io.realm.Realm;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by jagamypriera on 24/07/17.
 */

public class ComponentWrapper {
    @Inject public Realm realm;
    @Inject public OkHttpClient okHttpClient;
    @Inject public HttpUrl httpUrl;
    @Inject public Retrofit retrofit;
}
