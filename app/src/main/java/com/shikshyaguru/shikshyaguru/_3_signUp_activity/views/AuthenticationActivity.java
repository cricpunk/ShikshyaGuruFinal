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
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsLoaderFragment;
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
                new InstitutionsLoaderFragment(),
                bundle,
                R.id.authentication_page_fragment_holder,
                getSupportFragmentManager());

    }

}
