package com.shikshyaguru.shikshyaguru._6_institutions_activity.views;
/*
 * Created by Pankaj Koirala on 9/30/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerAboutFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerActivitiesFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerContactFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerHomeFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerManagementFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerOffersFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerReviewsFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStaffFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStudentsFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerTeachersFragment;

public class InstitutionsLoaderFragment extends Fragment implements AppBarLayout.OnOffsetChangedListener{

    CollapsingToolbarLayout layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout._6_2_0_ihp_inst_loader_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        initToolbarAndLayout(view);
        initSmartTabLayout(view);
        initFloatingActionMenu(view);
    }

    private void initToolbarAndLayout(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.tb_inst_loader_frag);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        ActionBar getSupportActionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (getSupportActionBar != null) {
            getSupportActionBar.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar.setDisplayShowTitleEnabled(false);
        }

        AppBarLayout mAppBar = (AppBarLayout) view.findViewById(R.id.abl_inst_loader_frag);
        layout = (CollapsingToolbarLayout) view.findViewById(R.id.ctbl_inst_loader_frag);
        mAppBar.addOnOffsetChangedListener(this);
    }

    private void initSmartTabLayout(View view) {

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getFragmentManager(), FragmentPagerItems.with(getContext())
                .add("Home", ViewPagerHomeFragment.class)
                .add("Offers", ViewPagerOffersFragment.class)
                .add("Gallery", ViewPagerGalleryFragment.class)
                .add("Management", ViewPagerManagementFragment.class)
                .add("Students", ViewPagerStudentsFragment.class)
                .add("Teachers", ViewPagerTeachersFragment.class)
                .add("Staff's", ViewPagerStaffFragment.class)
                .add("Activities", ViewPagerActivitiesFragment.class)
                .add("About", ViewPagerAboutFragment.class)
                .add("Contact", ViewPagerContactFragment.class)
                .add("Reviews", ViewPagerReviewsFragment.class)
                .create()
        );

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.vp_inst_loader_frag);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) view.findViewById(R.id.stl_inst_loader_frag);
        viewPagerTab.setViewPager(viewPager);

    }

    private void initFloatingActionMenu(View view) {
        FloatingActionMenu floatingActionMenu = (FloatingActionMenu) view.findViewById(R.id.fam_inst_loader_frag);
        FloatingActionButton navigation = (FloatingActionButton) view.findViewById(R.id.fab_navigation_inst_loader_frag);
        FloatingActionButton share = (FloatingActionButton) view.findViewById(R.id.fab_share_inst_loader_frag);
        FloatingActionButton review = (FloatingActionButton) view.findViewById(R.id.fab_review_inst_loader_frag);
        FloatingActionButton favourite = (FloatingActionButton) view.findViewById(R.id.fab_favourite_inst_loader_frag);

        navigation.setImageResource(R.drawable.ic_navigation);
        share.setImageResource(R.drawable.ic_share_3);
        review.setImageResource(R.drawable.ic_review_message);
        favourite.setImageResource(R.drawable.ic_heart);

        navigation.setOnClickListener(floatingActionButtonClickListener);
        share.setOnClickListener(floatingActionButtonClickListener);
        review.setOnClickListener(floatingActionButtonClickListener);
        favourite.setOnClickListener(floatingActionButtonClickListener);

        ViewGroup.LayoutParams layoutParams = floatingActionMenu.getLayoutParams();
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        floatingActionMenu.setLayoutParams(layoutParams);
        floatingActionMenu.setClosedOnTouchOutside(true);

    }

    private View.OnClickListener floatingActionButtonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fab_navigation_inst_loader_frag:
                    Toast.makeText(getContext(), "Navigation", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fab_share_inst_loader_frag:
                    Toast.makeText(getContext(), "Share", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fab_review_inst_loader_frag:
                    Toast.makeText(getContext(), "Review", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fab_favourite_inst_loader_frag:
                    Toast.makeText(getContext(), "Favourite", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        layout.setTranslationY(verticalOffset);
    }
}
