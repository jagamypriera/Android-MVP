package com.jagamypriera.framework.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
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
import com.jagamypriera.framework.fragments.AsdfMvpBaseFragment;
import com.jagamypriera.framework.models.LoadFragmentEvent;
import com.jagamypriera.framework.models.ToolbarEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.support.annotation.RestrictTo.Scope.SUBCLASSES;


/**
 * Created by jagamypriera on 24/07/17.
 */
@RestrictTo({SUBCLASSES})
public class AsdfBaseActivity extends AppCompatActivity implements AsdfBaseActivityInterface {
    protected Unbinder unbinder;
    boolean doublePress = false;
    private Toolbar toolbar;
    private TextView toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void print(String message) {
        Log.i("asdf", message);
    }

    private void updateToolbarElevation(boolean elevation) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            toolbar.setElevation(elevation ? 10 : 0);
    }

    @Subscribe
    public void onToolbarEvent(ToolbarEvent event) {
        if (event == null) return;
        toolbarTitle.setText(event.title);
        updateToolbarElevation(event.elevation);
    }

    @Subscribe
    public void onLoadFragmentEvent(LoadFragmentEvent event) {
        if (event == null) return;
        addFragment(event.containerId, event.fragment, event.tag);
    }

    @Override
    public void setupToolbarWithTitle(Toolbar toolbar, TextView textView, String title) {
        this.toolbar = toolbar;
        this.toolbarTitle = textView;
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("");
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        this.toolbarTitle.setText(title);
        //mToolbar.setNavigationIcon(R.drawable.ic_back);
    }

    @Override
    public void addFragment(int container, Fragment fragment, String tag) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (manager.findFragmentByTag(tag) == null) transaction.setCustomAnimations(R.anim.fade_in,
                R.anim.fade_out, R.anim.fade_in,
                R.anim.fade_out).add(container, fragment, tag).addToBackStack(tag).commit();
        else manager.popBackStackImmediate(tag, 0);
    }

    @Override
    public void configList(RecyclerView recycler, int lineColor, int orientation) {
        recycler.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recycler.setLayoutManager(manager);
        recycler.addItemDecoration(new DividerItemDecoration(this, orientation, lineColor));
        recycler.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        int fragmentCount = manager.getBackStackEntryCount();
        if (fragmentCount > 1) {
            FragmentManager.BackStackEntry backEntry = manager.getBackStackEntryAt(fragmentCount - 1);
            String tag = backEntry.getName();
            AsdfMvpBaseFragment fragment = (AsdfMvpBaseFragment) manager.findFragmentByTag(tag);
            if (fragment.isPendingAction()) fragment.executePendingAction();
            else getSupportFragmentManager().popBackStack();
        } else {
            if (doublePress) {
                finish();
                return;
            }
            this.doublePress = true;
            Toast.makeText(this, "Tekan tombol kembali lagi untuk keluar", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doublePress = false;
                }
            }, 2000);
        }
    }

    @Override
    public void showWarningDialog(String titleWarning, String message, String btnText) {
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void dismissProgress() {
    }

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
        print("\nCALLED FROM\t" + AsdfBaseActivity.class.getSimpleName() + "\nORIGIN\t\t" + this.getLocalClassName() + "\nTARGET\t\t" + classname + "\nDATA\t\t\t" + intent.getData() + "\n--end--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (unbinder != null) unbinder.unbind();
    }

    @Override
    protected void onResume() {
        super.onResume();
        unbinder = ButterKnife.bind(this);
    }
}
