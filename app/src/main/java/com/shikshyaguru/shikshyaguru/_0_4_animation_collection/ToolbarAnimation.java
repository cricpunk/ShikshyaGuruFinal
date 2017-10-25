//package com.shikshyaguru.shikshyaguru.animation_collection;
//
//import android.support.design.widget.AppBarLayout;
//import android.util.Log;
//import android.view.View;
//
//import com.arlib.floatingsearchview.FloatingSearchView;
//import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
//import com.github.ksoichiro.android.observablescrollview.ScrollState;
//import com.nineoldandroids.view.ViewHelper;
//
///**
// * Created by cricpunk on 9/15/17.
// * Pankaj Koirala
// * Kathmandu Nepal
// */
//
//public class ToolbarAnimation {
//
//
//    private AppBarLayout mToolbar;
//    private ObservableScrollView mScrollable;
////    private int screenHeight;
//    private FloatingSearchView floatingSearchView;
//
//    public ToolbarAnimation(AppBarLayout toolbar, ObservableScrollView s, FloatingSearchView floatingSearchView) {
//        this.mToolbar = toolbar;
//        this.mScrollable = s;
//        this.floatingSearchView = floatingSearchView;
//    }
//
//    private boolean toolbarIsShown() {
//        return ViewHelper.getTranslationY(mToolbar) == 0;
//    }
//
//    private boolean toolbarIsHidden() {
//        return ViewHelper.getTranslationY(mToolbar) == -mToolbar.getHeight();
//    }
//
//    private void showToolbar() {
//        moveToolbar(0);
//    }
//
//    private void hideToolbar() {
//        moveToolbar(-mToolbar.getHeight());
//    }
//
//    private void moveToolbar(float toTranslationY) {
//        if (ViewHelper.getTranslationY(mToolbar) == toTranslationY) {
//            return;
//        }
//        com.nineoldandroids.animation.ValueAnimator animator = com.nineoldandroids.animation.ValueAnimator.ofFloat(ViewHelper.getTranslationY(mToolbar), toTranslationY).setDuration(200);
//        animator.addUpdateListener(new com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(com.nineoldandroids.animation.ValueAnimator animation) {
//                float translationY = (float) animation.getAnimatedValue();
//                ViewHelper.setTranslationY(mToolbar, translationY);
//                ViewHelper.setTranslationY(mScrollable, translationY);
////                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) (mScrollable).getLayoutParams();
////                lp.height = (int) -translationY + screenHeight - lp.topMargin;
//                (mScrollable).requestLayout();
//            }
//        });
//        animator.start();
//    }
//
//    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
//        Log.e("DEBUG", "onUpOrCancelMotionEvent: " + scrollState);
//        if (scrollState == ScrollState.UP) {
//            if (toolbarIsShown()) {
//                hideToolbar();
//                floatingSearchView.setVisibility(View.GONE);
//            }
//        } else if (scrollState == ScrollState.DOWN) {
//            if (toolbarIsHidden()) {
//                showToolbar();
//                floatingSearchView.setVisibility(View.VISIBLE);
//            }
//        }
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//// holds the original Toolbar height.
//// this can also be obtained via (an)other method(s)
////    private int mToolbarHeight, mAnimDuration = 600/* milliseconds */;
////    private ValueAnimator mVaActionBar;
////    private ActionBar actionBar;
//
////    public void hideActionBar() {
////        // initialize `mToolbarHeight`
////        if (mToolbarHeight == 0) {
////            mToolbarHeight = mToolbar.getHeight();
////        }
////
////        if (mVaActionBar != null && mVaActionBar.isRunning()) {
////            // we are already animating a transition - block here
////            return;
////        }
////
////        // animate `Toolbar's` height to zero.
////        mVaActionBar = ValueAnimator.ofInt(mToolbarHeight , 0);
////        mVaActionBar.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
////            @Override
////            public void onAnimationUpdate(ValueAnimator animation) {
////                // update LayoutParams
////                ((ConstraintLayout.LayoutParams)mToolbar.getLayoutParams()).height
////                        = (Integer)animation.getAnimatedValue();
////                mToolbar.requestLayout();
////            }
////        });
////
////        mVaActionBar.addListener(new AnimatorListenerAdapter() {
////            @Override
////            public void onAnimationEnd(Animator animation) {
////                super.onAnimationEnd(animation);
////
////                if (actionBar != null) { // sanity check
////                    actionBar.hide();
////                }
////            }
////        });
////
////        mVaActionBar.setDuration(mAnimDuration);
////        mVaActionBar.start();
////    }
////
////    public void showActionBar() {
////        if (mVaActionBar != null && mVaActionBar.isRunning()) {
////            // we are already animating a transition - block here
////            return;
////        }
////
////        // restore `Toolbar's` height
////        mVaActionBar = ValueAnimator.ofInt(0 , mToolbarHeight);
////        mVaActionBar.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
////            @Override
////            public void onAnimationUpdate(ValueAnimator animation) {
////                // update LayoutParams
////                ((ConstraintLayout.LayoutParams)mToolbar.getLayoutParams()).height
////                        = (Integer)animation.getAnimatedValue();
////                mToolbar.requestLayout();
////            }
////        });
////
////        mVaActionBar.addListener(new AnimatorListenerAdapter() {
////            @Override
////            public void onAnimationStart(Animator animation) {
////                super.onAnimationStart(animation);
////
////                if (actionBar != null) { // sanity check
////                    actionBar.show();
////                }
////            }
////        });
////
////        mVaActionBar.setDuration(mAnimDuration);
////        mVaActionBar.start();
////    }
