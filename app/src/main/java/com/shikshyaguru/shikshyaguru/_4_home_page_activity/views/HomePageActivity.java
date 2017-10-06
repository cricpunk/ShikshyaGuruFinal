package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_1_searching_mechanism.views.BaseExampleFragment;
import java.util.List;

/*
 * Created by Pankaj Koirala on 9/24/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class HomePageActivity extends AppCompatActivity implements
        BaseExampleFragment.BaseExampleFragmentCallbacks,
        NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;

//    @Bind(R.id.image_slider_layout) ViewPager imageSlider;
//    @Bind(R.id.back)ViewGroup back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._4_0_home_page_activity);

//        ButterKnife.bind(new HomePageMainFragment(), this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.home_page_drawer_layout);

//        NavigationView navigationView = (NavigationView) findViewById(R.id.home_page_nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        NavigationDrawerFragment navigationDrawer = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.home_page_nav_view);
        navigationDrawer.setUpNavigationDrawer(R.id.home_page_nav_view, mDrawerLayout, getWindow());

        showFragment(new HomePageMainFragment());
    }

    @Override
    public void onAttachSearchViewToDrawer(FloatingSearchView searchView) {
        searchView.attachNavigationDrawerToMenuButton(mDrawerLayout);
    }

    @Override
    public void onBackPressed() {
        List fragments = getSupportFragmentManager().getFragments();
        BaseExampleFragment currentFragment = (BaseExampleFragment) fragments.get(fragments.size() - 1);

        if (!currentFragment.onActivityBackPress()) {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        switch (menuItem.getItemId()) {
//            case R.id.scrolling_search_bar_example:
//                showFragment(new HomePageMainFragment());
//                return true;
            default:
                return true;
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_page_fragment_holder, fragment).commit();
    }

}
