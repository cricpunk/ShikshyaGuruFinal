package com.shikshyaguru.shikshyaguru._0_6_widgets;

import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 12:01 PM 24 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class Toolbars {

    public static void setUpToolbar(Toolbar toolbar, Activity activity, String title) {

        ((AppCompatActivity) activity).setSupportActionBar(toolbar);

        ActionBar getSupportActionBar = ((AppCompatActivity) activity).getSupportActionBar();
        if (getSupportActionBar != null) {
            getSupportActionBar.setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar.setDisplayShowHomeEnabled(true);
            getSupportActionBar.setTitle(title);
            //getSupportActionBar.setDisplayShowTitleEnabled(false);
        }

//        AppBarLayout mAppBar = rootView.findViewById(R.id.abl_inst_loader_vp_programmes_courses_loader);
//        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//
//            }
//        });

    }

}
