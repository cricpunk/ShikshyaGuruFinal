package com.shikshyaguru.shikshyaguru._0_4_animation_collection;
/*
 * Created by Pankaj Koirala on 10/14/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.shikshyaguru.shikshyaguru.R;

public class CircularReveal {

    private View view;
    private Boolean hasFocus;
    private Activity activity;

    public CircularReveal(Activity activity, View view, Boolean hasFocus) {
        this.activity = activity;
        this.view = view;
        this.hasFocus = hasFocus;
    }


    public void circularReveal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            GradientDrawable drawable = (GradientDrawable) view.getBackground();
            if (hasFocus) {
                drawable.setStroke(2, ContextCompat.getColor(activity, R.color.colorAppMain));
                ViewAnimationUtils
                        .createCircularReveal(
                                view,
                                centerX(view),
                                centerY(view),
                                0,
                                finalRadius(view))
                        .setDuration(1500)
                        .start();

            } else {

                drawable.setStroke(2, Color.parseColor("#40222222"));
//                ViewAnimationUtils
//                        .createCircularReveal(
//                                view,
//                                view.getLeft(),
//                                view.getRight(),
//                                finalRadius(view), 0)
//                        .setDuration(500)
//                        .start();
            }

        }
    }

    private int centerX(View v) {
        return (v.getLeft() + v.getRight()) / 2;
    }

    private int centerY(View v) {
        return (v.getTop() + v.getBottom()) / 2;
    }

    private float finalRadius(View v) {
        //Final radius for the clipping circle
        int distanceX = Math.max(centerX(v), v.getWidth() - centerX(v));
        int distanceY = Math.max(centerY(v), v.getHeight() - centerY(v));
        return (float) Math.hypot(distanceX, distanceY);
    }
}
