package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;
/*
 * Created by Pankaj Koirala on 3/23/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_3_dynamic_fragment_loader.DynamicFragmentLoader;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.vansuita.gaussianblur.GaussianBlur;

public class AuthenticationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout._3_4_authentication_page_activity);

        // Background image with blur effect
        KenBurnsView kenBurnsView = findViewById(R.id.kbv);
        GaussianBlur.with(this)
                .size(500)
                .radius(10)
                .put(R.drawable.lbg2, kenBurnsView);

        // Loading default fragment for this activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DynamicFragmentLoader.loadFragment(
                new LoginFragment(),
                bundle,
                R.id.authentication_page_fragment_holder,
                getSupportFragmentManager());

    }

    /** This method is implemented just for twitter login purpose
     *  In this project login functions is handled from fragment which is the reason twitter wont
     *  work from fragment directly. We need to implement this method in activity class as well.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE) {
            // Titter onActivityResult for its callback.
            LoginFragment.mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
        }

    }

}
