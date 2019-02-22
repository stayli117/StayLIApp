package com.stayli.app.model.domain;

import android.graphics.drawable.Drawable;

/**
 * Created by yhgao on 2018/2/8.
 */

public class User {
    private String name;
    private String link;
    private Drawable drawable;

    public User(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
