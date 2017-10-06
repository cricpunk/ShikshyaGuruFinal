package com.shikshyaguru.shikshyaguru._5_news_activity.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import com.shikshyaguru.shikshyaguru.R;

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
        loadFragment(bundle);
    }

    /*
     *
     ###############################################################################################
     ##==>  This method will be called from inside onCreate which take bundle as an argument      ##
     ##     which is used to retrieve request code.                                               ##
     ##==>  loadFragment method will display related fragment as per the request past through     ##
     ##     activities.                                                                           ##
     ##==>  NewsLoaderFragment.class will be displayed if activities request for single news      ##
     ##     details.                                                                              ##
     ##==>  NewsMainFragment.class will be displayed if activities request for news home page     ##
     ###############################################################################################
     *
     */
    private void loadFragment(Bundle bundle) {
        if (bundle != null) {
            String requestCode = (String) bundle.get("REQUEST_CODE");
            if (requestCode != null) {
                switch (requestCode) {
                    case "news_home":
                        showFragment(new NewsMainFragment());
                        break;
                    case "news_loader":
                        showFragment(new NewsLoaderFragment());
                        break;
                    default:
                        break;
                }
            } else {
                showFragment(new NewsMainFragment());
            }
        } else {
            showFragment(new NewsMainFragment());
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.news_home_page_fragment_holder, fragment).commit();
    }

}
