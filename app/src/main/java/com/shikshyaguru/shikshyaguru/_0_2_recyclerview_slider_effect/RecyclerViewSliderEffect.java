package com.shikshyaguru.shikshyaguru._0_2_recyclerview_slider_effect;
/*
 * Created by Pankaj Koirala on 10/6/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewSliderEffect {

    public static void scrollListenerOnScrolled(RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        int width = recyclerView.getChildAt(0).getWidth();
        int padding = (recyclerView.getWidth() - width) / 2;

        for (int j = 0; j < childCount; j++) {
            View v = recyclerView.getChildAt(j);
            float rate = 0;
            if (v.getLeft() <= padding) {
                if (v.getLeft() >= padding - v.getWidth()) {
                    rate = (padding - v.getLeft()) * 1f / v.getWidth();
                } else {
                    rate = 1;
                }
                v.setScaleY(1 - rate * 0.2f);
                v.setScaleX(1 - rate * 0.05f);

            } else {
                if (v.getLeft() <= recyclerView.getWidth() - padding) {
                    rate = (recyclerView.getWidth() - padding - v.getLeft()) * 1f / v.getWidth();
                }
                v.setScaleY(0.8f + rate * 0.2f);    // Top/Bottom space
                v.setScaleX(0.95f + rate * 0.05f);  // Side space between item
            }
        }
    }

    public static void layoutChangeListenerOnLayoutChange(RecyclerView recyclerView) {
        if (recyclerView.getChildCount() < 3) {
            if (recyclerView.getChildAt(1) != null) {
                if (recyclerView.getVerticalScrollbarPosition() == 0) {
                    View v1 = recyclerView.getChildAt(1);
                    v1.setScaleY(0.95f);
                    v1.setScaleX(0.95f);

                } else {
                    View v1 = recyclerView.getChildAt(0);
                    v1.setScaleY(0.95f);
                    v1.setScaleX(0.95f);
                }
            }
        } else {
            if (recyclerView.getChildAt(0) != null) {
                View v0 = recyclerView.getChildAt(0);
                v0.setScaleY(0.95f);
                v0.setScaleX(0.95f);
            }
            if (recyclerView.getChildAt(2) != null) {
                View v2 = recyclerView.getChildAt(2);
                v2.setScaleY(0.95f);
                v2.setScaleX(0.95f);
            }
        }
    }

    public static void autoScrollRecyclerView(final RecyclerView.Adapter<?> adapter, final RecyclerView recyclerView) {
        final int speedScroll = 3000;
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;
            boolean flag = true;
            @Override
            public void run() {
                if(count < adapter.getItemCount()){
                    if(count==adapter.getItemCount()-1){
                        flag = false;
                    }else if(count == 0){
                        flag = true;
                    }
                    if(flag) count++;
                    else count--;

                    recyclerView.smoothScrollToPosition(count);
                    handler.postDelayed(this,speedScroll);

                }
            }
        };
        handler.postDelayed(runnable,speedScroll);

    }


}
