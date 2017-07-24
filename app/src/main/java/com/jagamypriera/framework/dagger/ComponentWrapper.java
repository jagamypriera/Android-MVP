package com.jagamypriera.framework.dagger;

import android.support.annotation.RestrictTo;

import javax.inject.Inject;

import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by jagamypriera on 24/07/17.
 */
@RestrictTo({LIBRARY_GROUP})
public class ComponentWrapper {
    @Inject public Realm realm;
    @Inject public OkHttpClient okHttpClient;
    @Inject public Retrofit retrofit;
}
