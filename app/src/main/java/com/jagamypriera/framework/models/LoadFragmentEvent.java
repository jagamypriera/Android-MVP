package com.jagamypriera.framework.models;

import android.support.v4.app.Fragment;

/**
 * Created by jagamypriera on 18/06/17.
 */

public class LoadFragmentEvent {
    public Fragment fragment;
    public String tag;
    public int containerId;

    public LoadFragmentEvent setFragment(Fragment fragment) {
        this.fragment = fragment;
        return this;
    }

    public LoadFragmentEvent setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public LoadFragmentEvent setContainerId(int containerId) {
        this.containerId = containerId;
        return this;
    }
}
