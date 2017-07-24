package com.jagamypriera.framework.models;

/**
 * Created by jagamypriera on 18/06/17.
 */

public class ToolbarEvent {
    public String title;
    public boolean elevation;
    public ToolbarEvent setTitle(String title) {
        this.title = title;
        return this;
    }
    public ToolbarEvent setElevation(boolean elevation) {
        this.elevation = elevation;
        return this;
    }
}
