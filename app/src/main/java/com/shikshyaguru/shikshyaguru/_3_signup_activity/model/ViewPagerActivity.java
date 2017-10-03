package com.shikshyaguru.shikshyaguru._3_signup_activity.model;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._3_signup_activity.adapter.SignUpLogInViewPagerAdapter;
import com.shikshyaguru.shikshyaguru._3_signup_activity.fragments.CenterFragment;
import com.shikshyaguru.shikshyaguru._3_signup_activity.fragments.LoginFragment;
import com.shikshyaguru.shikshyaguru._3_signup_activity.fragments.SignUpFragment;

/**
 * Created by cricpunk on 7/13/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class ViewPagerActivity extends AppCompatActivity{
    SignUpLogInViewPagerAdapter adapterViewPager;

    private static class MyPagerAdapter extends SignUpLogInViewPagerAdapter {
        private static int NUM_ITEMS = 3;

        MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return SignUpFragment.newInstance(0, "Sign Up");
                case 1: // Fragment # 1 - This will show SecondFragment
                    return CenterFragment.newInstance(1, "Center");
                case 2: // Fragment # 2 - This will show ThirdFragment
                    return LoginFragment.newInstance(2, "Log In");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._3_3_view_pager);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.signUpLoginUpViewPager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(1);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                new ZoomOutPageTransformer().transformPage(viewPager, -1);

            }

            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(ViewPagerActivity.this,
//                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                dont(viewPager.getCurrentItem());
            }
        });

        viewPager.setClipToPadding(false);
        viewPager.setPadding(80,0,80,0);
        viewPager.setPageMargin(40);

    }

    public void dont(int position) {
        SignUpFragment signUpFragment = (SignUpFragment) adapterViewPager.getRegisteredFragment(0);
        CenterFragment centerFragment = (CenterFragment) adapterViewPager.getRegisteredFragment(1);
        LoginFragment loginFragment = (LoginFragment) adapterViewPager.getRegisteredFragment(2);

        ConstraintLayout signIn = (ConstraintLayout) signUpFragment.getActivity().findViewById(R.id.signUp_full_layout);
        ConstraintLayout center = (ConstraintLayout) centerFragment.getActivity().findViewById(R.id.center_full_layout);
        ConstraintLayout login = (ConstraintLayout) loginFragment.getActivity().findViewById(R.id.login_full_layout);

        switch (position) {
            case 0:
                signIn.setPadding(0,0,0,0);
                center.setPadding(0,120,0,120);
                break;
            case 1:
                signIn.setPadding(0,120,0,120);
                center.setPadding(0,0,0,0);
                login.setPadding(0,120,0,120);
                break;
            case 2:
                center.setPadding(0,120,0,120);
                login.setPadding(0,0,0,0);
                break;
            default:
                break;
        }

    }

}


