package com.shikshyaguru.shikshyaguru._0_4_animation_collection;
/*
 * Created by Pankaj Koirala on 10/25/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class TextViewCollection {

    public static void setTextViewDrawableColor(TextView textView, int color) {
        for (Drawable drawable : textView.getCompoundDrawables()) {
            drawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
    }
}
