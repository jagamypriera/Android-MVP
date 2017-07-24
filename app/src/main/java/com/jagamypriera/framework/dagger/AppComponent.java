package com.jagamypriera.framework.dagger;

import android.support.annotation.RestrictTo;

import javax.inject.Singleton;

import dagger.Component;

import static android.support.annotation.RestrictTo.Scope.GROUP_ID;
import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static android.support.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * Created by Taufik Akbar on 19/12/2016.
 */

@RestrictTo({LIBRARY_GROUP})
@Singleton
@Component(modules = {NetworkModule.class, RealmModule.class})
public interface AppComponent {
    void makeAvailableTo(ComponentWrapper wrapper);
}
