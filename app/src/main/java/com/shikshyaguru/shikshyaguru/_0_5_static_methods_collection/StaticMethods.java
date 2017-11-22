package com.shikshyaguru.shikshyaguru._0_5_static_methods_collection;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.view.WindowManager;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 3:31 PM 19 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class StaticMethods {

    public static void changeStatusBarColor(Window window, Context context, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(context, color));
        }
    }
}
