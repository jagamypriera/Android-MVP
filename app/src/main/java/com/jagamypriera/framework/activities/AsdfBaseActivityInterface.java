package com.jagamypriera.framework.activities;

import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by jagamypriera on 24/07/17.
 */
@RestrictTo({LIBRARY_GROUP})
public interface AsdfBaseActivityInterface {
    void addFragment(int container, Fragment fragment, String tag);
    void configList(RecyclerView mRecycler, int drawableColor, int orientation);
    void showWarningDialog(String titleWarning, String message, String btnText);
    void setupToolbarWithTitle(Toolbar mToolbar, TextView textView, String title);
    void showProgress();
    void dismissProgress();
    void print(String message);
}
