package com.shikshyaguru.shikshyaguru._0_6_widgets;

import android.view.View;
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

}
