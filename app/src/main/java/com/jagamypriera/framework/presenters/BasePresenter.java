package com.jagamypriera.framework.presenters;

import android.content.Context;

import com.jagamypriera.framework.dagger.ComponentWrapper;
import com.jagamypriera.framework.dagger.DaggerAppComponent;
import com.jagamypriera.framework.dagger.NetworkModule;
import com.jagamypriera.framework.dagger.RealmModule;

import javax.inject.Inject;

import io.realm.Realm;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by jagamypriera on 23/07/17.
 */

public class BasePresenter {
    @Inject
    public OkHttpClient okHttpClient;
    @Inject
    public HttpUrl httpUrl;
    @Injectgti
    public Retrofit retrofit;
    private ComponentWrapper wrapper;

    public BasePresenter(Context context) {
        wrapper = new ComponentWrapper();
        DaggerAppComponent.builder().networkModule(new NetworkModule(context)).realmModule(new RealmModule(context))
                .build().makeAvailableTo(wrapper);
    }

    public Realm getRealm() {
        return wrapper.realm;
    }

    public OkHttpClient getOkHttpClient() {
        return wrapper.okHttpClient;
    }

    public HttpUrl getHttpUrl() {
        return wrapper.httpUrl;
    }

    public Retrofit getRetrofit() {
        return wrapper.retrofit;
    }
}
