package com.shikshyaguru.shikshyaguru._0_6_widgets;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 11:51 AM 24 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class StatusBar {

    public static void changeStatusBarColor(Context context, Window window, int color) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(context, color));
    }

}
