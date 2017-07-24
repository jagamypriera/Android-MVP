package com.jagamypriera.framework.fragments;

import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

/**
 * Created by jagamypriera on 18/07/17.
 */
@RestrictTo({LIBRARY_GROUP})
public interface AsdfBaseFragmentInterface {
    void showWarningDialog(String titleWarning, String message, String btnText);
    void showProgress();
    void dismissProgress();
    boolean isPendingAction();
    void executePendingAction();
    void setupToolbar(Toolbar mToolbar);
    void print(String message);
    void configList(RecyclerView mRecycler, int drawableColor, int orientation);
}
