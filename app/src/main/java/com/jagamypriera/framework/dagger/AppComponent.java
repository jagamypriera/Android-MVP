package com.jagamypriera.framework.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Taufik Akbar on 19/12/2016.
 */


@Singleton
@Component(modules = {AppModule.class, NetworkModule.class, RealmModule.class})
public interface AppComponent<T> {
    void makeAvailableTo(T object);
}
