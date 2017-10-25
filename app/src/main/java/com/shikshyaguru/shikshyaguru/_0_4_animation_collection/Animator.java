package com.shikshyaguru.shikshyaguru._0_4_animation_collection;

import android.content.Context;
import android.view.ViewGroup;

import com.fujiyuu75.sequent.Animation;
import com.fujiyuu75.sequent.Sequent;

import static com.fujiyuu75.sequent.Direction.FORWARD;

/**
 * Created by cricpunk on 7/10/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class Animator {

    public void fadeInUp(Context context, ViewGroup layout) {
        Sequent.origin(layout).anim(context, Animation.FADE_IN_UP)
                .duration(1000)
                .delay(0)
                .offset(500)
                .flow(FORWARD)
                .start();
    }
}
