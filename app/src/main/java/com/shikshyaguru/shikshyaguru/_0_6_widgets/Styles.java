package com.shikshyaguru.shikshyaguru._0_6_widgets;

import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 4:19 PM 30 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class Styles {

    public static int sGetHeight(View targetView) {
        sMeasureView(targetView);
        return targetView.getMeasuredHeight();
    }

    private static void sMeasureView(View targetView) {
        targetView.measure(View.MeasureSpec.makeMeasureSpec(targetView.getLayoutParams().width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
    }

    public static ViewGroup.LayoutParams sSetMargin(View targetView, int l, int t, int r, int b) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) targetView.getLayoutParams();
        layoutParams.setMargins(l, t, r, b);
        return layoutParams;
    }

    public static View.OnFocusChangeListener textViewAnimation = new View.OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ViewAnimationUtils.createCircularReveal(v,
                            centerX(v),
                            centerY(v),
                            0,
                            finalRadius(v)).start();
                }
            }
        }

    };

    private static int centerX(View v) {
        return (v.getLeft() + v.getRight()) / 2;
    }

    private static int centerY(View v) {
        return (v.getTop() + v.getBottom()) / 2 ;
    }

    private static float finalRadius(View v) {
        //Final radius for the clipping circle
        int distanceX = Math.max(centerX(v), v.getWidth() - centerX(v));
        int distanceY = Math.max(centerY(v), v.getHeight() - centerY(v));
        return  (float) Math.hypot(distanceX, distanceY);
    }

}
