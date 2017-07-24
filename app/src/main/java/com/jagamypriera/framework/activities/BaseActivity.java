package com.jagamypriera.framework.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
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
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jagamypriera.framework.R;
import com.jagamypriera.framework.customviews.DividerItemDecoration;
import com.jagamypriera.framework.fragments.BaseFragment;
import com.jagamypriera.framework.interfaces.BaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by Taufik Akbar on 21/12/2016.
 */

public class BaseActivity extends AppCompatActivity implements BaseView {
    protected Unbinder unbinder;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder=ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    public void setupToolbar(Toolbar mToolbar) {
        if (mToolbar != null) {
            //mToolbar.setNavigationIcon(R.drawable.ic_back);
            setSupportActionBar(mToolbar);
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void print(String message) {
        Log.i("asdf", message);
    }

    @Override
    public void setupToolbarWithTitle(Toolbar mToolbar, TextView textView, String title) {
        if (mToolbar != null) {
            //mToolbar.setNavigationIcon(R.drawable.ic_back);
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setTitle("");
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            textView.setText(title);
        }
    }
    @Override
    public void addFragment(int container, Fragment fragment, String tag) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        if(manager.findFragmentByTag(tag)==null) transaction.setCustomAnimations(R.anim.fade_in,
                R.anim.fade_out, R.anim.fade_in,
                R.anim.fade_out).add(container, fragment, tag).addToBackStack(tag).commit();
        else manager.popBackStackImmediate(tag, 0);
    }
    @Override
    public void configList(RecyclerView mRecycler, int lineColor) {
        mRecycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, lineColor));
        mRecycler.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager=getSupportFragmentManager();
        int fragmentCount = manager.getBackStackEntryCount();
        if (fragmentCount > 1) {
            FragmentManager.BackStackEntry backEntry = manager.getBackStackEntryAt(fragmentCount-1);
            String tag = backEntry.getName();
            BaseFragment fragment = (BaseFragment) manager.findFragmentByTag(tag);
            if(fragment.isPendingAction()) fragment.executePendingAction();
            else getSupportFragmentManager().popBackStack();
        } else  {
            if (doubleBackToExitPressedOnce) {
                finish();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    @Override
    public void showWarningDialog(String titleWarning, String message, String btnText) {
    }
    @Override
    public String getDeviceId() {
        return "";
    }
    @Override public void showProgress() {}
    @Override public void dismissProgress() {}
    @Override public void executePendingAction() {}
    @Override public boolean isPendingAction() {return false;}
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            int fragmentCount = getSupportFragmentManager().getBackStackEntryCount();
            if (fragmentCount > 1) {
                getSupportFragmentManager().popBackStack();
            } else if (fragmentCount == 1) finish();
             else super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        String classname = intent.getComponent() == null ? "null" : intent.getComponent().getClassName();
        print("\nCALLED FROM\t" + BaseActivity.class.getSimpleName() + "\nORIGIN\t\t" + this.getLocalClassName() + "\nTARGET\t\t" + classname + "\nDATA\t\t\t" + intent.getData() + "\n--end--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        print("onDestroy called by: "+this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        print("onPause called by: "+this.getClass().getSimpleName()+" unbinder is "+(unbinder == null?"null":"exists"));
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        print("onResume called by: "+this.getClass().getSimpleName()+" unbinder is "+(unbinder == null?"null":"exists"));
        unbinder=ButterKnife.bind(this);
    }
}
