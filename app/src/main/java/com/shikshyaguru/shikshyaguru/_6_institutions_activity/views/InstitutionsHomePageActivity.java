package com.shikshyaguru.shikshyaguru._6_institutions_activity.views;
/*
 * Created by Pankaj Koirala on 9/30/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_3_dynamic_fragment_loader.DynamicFragmentLoader;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoader;

public class InstitutionsHomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._6_0_0_institutions_home_page_activity);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DynamicFragmentLoader.loadFragment(
                new InstitutionsLoaderFragment(),
                bundle,
                R.id.institutions_home_page_fragment_holder,
                getSupportFragmentManager());

    }

}
