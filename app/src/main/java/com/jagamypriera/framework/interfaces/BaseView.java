package com.jagamypriera.framework.interfaces;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by jagamypriera on 18/07/17.
 */

public interface BaseView {
    void addFragment(int container, Fragment fragment, String tag);
    void configList(RecyclerView mRecycler, int drawableColor);
    void showWarningDialog(String titleWarning, String message, String btnText);
    void setupToolbarWithTitle(Toolbar mToolbar, TextView textView, String title);
    String getDeviceId();
    void showProgress();
    void dismissProgress();
    boolean isPendingAction();
    void executePendingAction();
    void setupToolbar(Toolbar mToolbar);
    void print(String message);
}
