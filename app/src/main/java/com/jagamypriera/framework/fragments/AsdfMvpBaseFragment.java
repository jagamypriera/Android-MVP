package com.jagamypriera.framework.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jagamypriera.framework.customviews.DividerItemDecoration;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;
import static android.support.annotation.RestrictTo.Scope.SUBCLASSES;

/**
 * Created by Taufik Akbar on 01/03/2017.
 */

public abstract class AsdfMvpBaseFragment extends Fragment implements AsdfBaseFragmentInterface {
    protected Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }
    @Override
    public void onPause() {
        super.onPause();
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        unbinder=ButterKnife.bind(this.getView());
    }

    protected abstract int getFragmentLayout();
    @Override
    public void configList(RecyclerView mRecycler, int drawableColor, int orientation) {
        mRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), orientation, drawableColor));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
    }
    @Override
    public void showProgress() {
    }
    @Override
    public void dismissProgress() {
    }
    @Override
    public void showWarningDialog(String titleWarning, String message, String btnText) {
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        String classname = intent.getComponent() == null ? "null" : intent.getComponent().getClassName();
        print("\nCALLED FROM\t" + AsdfMvpBaseFragment.class.getSimpleName() + "\nORIGIN\t\t" + this.getActivity().getLocalClassName() + "\nTARGET\t\t" + classname + "\nDATA\t\t\t" + intent.getData() + "\n--end--");
    }
    @Override public void executePendingAction(){}
    @Override public boolean isPendingAction(){
        return false;
    }
    @Override public void setupToolbar(Toolbar mToolbar) {}
    @Override public void print(String message) {
        Log.i("asdf", message);
    }
}
