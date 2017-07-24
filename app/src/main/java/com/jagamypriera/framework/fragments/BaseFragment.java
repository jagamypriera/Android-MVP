package com.jagamypriera.framework.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jagamypriera.framework.R;
import com.jagamypriera.framework.customviews.DividerItemDecoration;
import com.jagamypriera.framework.interfaces.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Taufik Akbar on 01/03/2017.
 */

public abstract class BaseFragment extends Fragment implements BaseView {
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
        print("onViewCreated called by: "+this.getClass().getSimpleName());
    }
    @Override
    public void onPause() {
        super.onPause();
        print("onPause called by: "+this.getClass().getSimpleName()+" unbinder is "+(unbinder == null?"null":"exists"));
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        print("onResume called by: "+this.getClass().getSimpleName()+" unbinder is "+(unbinder == null?"null":"exists"));
        unbinder=ButterKnife.bind(this.getView());
    }

    protected abstract int getFragmentLayout();

    @Override
    public void configList(RecyclerView mRecycler, int drawableColor) {
        mRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL, drawableColor));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
    }
    @Override
    public void showProgress() {
    }
    @Override
    public void dismissProgress() {
    }

    @Override
    public String getDeviceId() {
        return "";
    }
    @Override
    public void showWarningDialog(String titleWarning, String message, String btnText) {
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        String classname = intent.getComponent() == null ? "null" : intent.getComponent().getClassName();
        print("\nCALLED FROM\t" + BaseFragment.class.getSimpleName() + "\nORIGIN\t\t" + this.getActivity().getLocalClassName() + "\nTARGET\t\t" + classname + "\nDATA\t\t\t" + intent.getData() + "\n--end--");
    }

    @Override
    public void setupToolbarWithTitle(Toolbar mToolbar, TextView textView, String title) {
        if (mToolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            assert actionBar != null;
            mToolbar.setTitle("");
            actionBar.setTitle("");
            actionBar.setDisplayHomeAsUpEnabled(true);
            //mToolbar.setNavigationIcon(R.drawable.ic_back);
            textView.setText(title);
        }
    }
    @Override
    public void executePendingAction(){}
    @Override
    public boolean isPendingAction(){
        return false;
    }

    @Override
    public void addFragment(int container, Fragment fragment, String tag){
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        if(manager.findFragmentByTag(tag)==null) transaction.setCustomAnimations(R.anim.fade_in,
                R.anim.fade_out, R.anim.fade_in,
                R.anim.fade_out).add(container, fragment, tag).addToBackStack(tag).commit();
        else manager.popBackStackImmediate(tag, 0);
    }
    @Override
    public void setupToolbar(Toolbar mToolbar) {

    }

    @Override
    public void print(String message) {
        Log.i("asdf", message);
    }
}
