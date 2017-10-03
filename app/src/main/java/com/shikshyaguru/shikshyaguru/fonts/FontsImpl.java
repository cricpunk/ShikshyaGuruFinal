package com.shikshyaguru.shikshyaguru.fonts;

import android.app.Application;

/**
 * Created by cricpunk on 7/10/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class FontsImpl extends Application {
    @Override
    public void onCreate() {
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/_1_OpenSansRegular.ttf");
        super.onCreate();
    }
}
