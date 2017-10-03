package com.shikshyaguru.shikshyaguru._5_news_activity.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_0_lib_observable_scroll_view.BaseActivity;

/**
 * Created by cricpunk on 9/13/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class NewsHomePageActivity extends BaseActivity implements ObservableScrollViewCallbacks {

    private View mImageView;
    private View mToolbarView;
    private ObservableScrollView mScrollView;
    private int mParallaxImageHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._5_0_news_home_page_activity);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

//        mImageView = findViewById(R.id.banner_slider);
//        mToolbarView = findViewById(R.id.toolbar);
//        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary)));
//        mScrollView = (ObservableScrollView) findViewById(R.id.scrollable);
//        mScrollView.setScrollViewCallbacks(this);
//        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);
//        bannerSlider();
//
//
//        ViewPagerItemAdapter adapter = new ViewPagerItemAdapter(ViewPagerItems.with(this)
//                .add("Head 1", R.layout.view_pager_fragment)
//                .add("Head 2", R.layout.view_pager_fragment)
//                .add("Head 3", R.layout.view_pager_fragment)
//                .add("Head 4", R.layout.view_pager_fragment)
//                .add("Head 5", R.layout.view_pager_fragment)
//                .add("Head 6", R.layout.view_pager_fragment)
//                .add("Head 7", R.layout.view_pager_fragment)
//                .add("Head 8", R.layout.view_pager_fragment)
//                .add("Head 9", R.layout.view_pager_fragment)
//                .create());
//
//
//
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPager.setAdapter(adapter);
//
//        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.tab_view_pager);
//        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        mToolbarView.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(mImageView, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

//    void bannerSlider() {
//        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
//        List<Banner> banners=new ArrayList<>();
//        //add banner using image url
//        //banners.add(new RemoteBanner("Put banner image url here ..."));
//        //add banner using resource drawable
//        banners.add(new DrawableBanner(R.drawable.pic0));
//        banners.add(new DrawableBanner(R.drawable.pic1));
//        banners.add(new DrawableBanner(R.drawable.pic0));
//        banners.add(new DrawableBanner(R.drawable.pic1));
//        banners.add(new DrawableBanner(R.drawable.pic0));
//        banners.add(new DrawableBanner(R.drawable.pic1));
//        bannerSlider.setBanners(banners);
//    }

}
