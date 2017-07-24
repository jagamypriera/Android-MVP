package com.jagamypriera.framework.dagger;

import android.content.Context;
import android.support.annotation.RestrictTo;

import com.jagamypriera.framework.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by jagamypriera on 07/07/17.
 */
@RestrictTo({LIBRARY_GROUP})
@Module
public class RealmModule {
    private final Context context;

    public RealmModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        Realm.init(context);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(context.getString(R.string.app_name))
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        return Realm.getDefaultInstance();
    }
}
