package com.shikshyaguru.shikshyaguru._5_news_activity.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_3_dynamic_fragment_loader.DynamicFragmentLoader;

 /*
  * Created by cricpunk on 9/13/17.
  * Pankaj Koirala
  * Kathmandu Nepal
  *
  ##################################################################################################
  ##==>  NewsHomePageActivity.class triggers when news headlines and all news is clicked from the ##
  ##     HomePageActivity.class for now.                                                          ##
  ##==>  If application needs to load news section from any other activities then this class will ##
  ##     handle all those requests.                                                               ##
  ##################################################################################################
  *
  */

public class NewsHomePageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._5_0_news_home_page_activity);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DynamicFragmentLoader.loadFragment(
                new NewsMainFragment(),
                bundle,
                R.id.news_home_page_fragment_holder,
                getSupportFragmentManager());

    }


}
