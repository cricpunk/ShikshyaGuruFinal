package com.shikshyaguru.shikshyaguru._6_institutions_activity.views;
/*
 * Created by Pankaj Koirala on 9/30/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Toolbars;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginFragment;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.InstitutionsController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerContactFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerHomeFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerManagementFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerReviewsFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStaffFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStudentsFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerTeachersFragment;
import com.shikshyaguru.shikshyaguru._8_map_activitiy.GPSTracker;
import com.shikshyaguru.shikshyaguru._8_map_activitiy.MapsActivity;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class InstitutionLoaderFragment extends Fragment implements InstitutionLoaderInterface{

    private CoordinatorLayout currentView;
    private SmartTabLayout viewPagerTab;
    private CollapsingToolbarLayout collapsingToolbar;
    private InstitutionsController controller;
    public static String id, image, name, place, slogan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout._6_2_0_ihp_inst_loader_fragment, container, false);
        currentView = view.findViewById(R.id.root_institutions_loader_frag);

        if (getArguments() != null ) {
            id = getArguments().getString("ID");
            image = getArguments().getString("IMAGE");
            name = getArguments().getString("NAME");
            place = getArguments().getString("PLACE");
            slogan = getArguments().getString("SLOGAN");
        }

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Toolbars.setUpToolbar(toolbar, getActivity(), name);
        // To make onOptionItemSelected working we have to setHasOptionsMenu true in fragment.
        setHasOptionsMenu(true);

        controller = new InstitutionsController(this, new InstitutionDataSource());

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponents(view);
        initSmartTabLayout(view);
        initFloatingActionMenu(view);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                Objects.requireNonNull(getActivity()).onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void initComponents(View view) {

        collapsingToolbar = view.findViewById(R.id.ctbl_inst_loader_frag);

        KenBurnsView kenBurnsView = view.findViewById(R.id.iv_news_headline_icon);
        //TextView lblName = view.findViewById(R.id.lbl_institutions_name);
        TextView lblPlace = view.findViewById(R.id.lbl_institutions_city_name);
        TextView lblSlogan = view.findViewById(R.id.lbl_institutions_slogan);

        Picasso.get()
                .load(image)
                .placeholder(R.drawable.logo)
                .into(kenBurnsView);
        //lblName.setText(name);
        lblPlace.setText(place);

        if (slogan != null) {
            lblSlogan.setText(slogan);
        } else {
            System.out.println("======================================================");
            System.out.println(controller.getSlogan(id));
            lblSlogan.setText(controller.getSlogan(id));
        }

        Toast.makeText(getContext(), id, Toast.LENGTH_SHORT).show();

    }

    private void initSmartTabLayout(View view) {

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getFragmentManager(), FragmentPagerItems.with(getContext())
                .add("Home", ViewPagerHomeFragment.class)
                .add("Programmes", ViewPagerProgrammesFragment.class)
                .add("Students", ViewPagerStudentsFragment.class)
                .add("Management", ViewPagerManagementFragment.class)
                .add("Gallery", ViewPagerGalleryFragment.class)
                .add("Teachers", ViewPagerTeachersFragment.class)
                .add("Staff's", ViewPagerStaffFragment.class)
                //.add("Activities", ViewPagerActivitiesFragment.class)
                .add("Contact", ViewPagerContactFragment.class)
                .add("Reviews", ViewPagerReviewsFragment.class)
                .create()
        );

        final ViewPager viewPager = view.findViewById(R.id.vp_inst_loader_frag);
        viewPager.setAdapter(adapter);

        viewPagerTab = view.findViewById(R.id.stl_inst_loader_frag);
        viewPagerTab.setViewPager(viewPager);
        viewPagerTab.setOnPageChangeListener(onPageChangeListener);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 2 || position == 3 || position == 4 || position == 5 || position == 6) {
                viewPagerTab.setBackgroundColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.black_toolbar));
                collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.black_toolbar));
                collapsingToolbar.setStatusBarScrimColor(ContextCompat.getColor(getContext(), R.color.black_toolbar));
                //getActivity().getWindow().setBackgroundDrawableResource(R.color.card_black);
            } else {
                viewPagerTab.setBackgroundColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorAppMain));
                collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getContext(), R.color.colorAppMain));
                collapsingToolbar.setStatusBarScrimColor(ContextCompat.getColor(getContext(), R.color.colorAppMain));
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initFloatingActionMenu(View view) {

        FloatingActionMenu floatingActionMenu = view.findViewById(R.id.fam_inst_loader_frag);

        FloatingActionButton navigation = view.findViewById(R.id.fab_navigation_inst_loader_frag);
        FloatingActionButton favourite = view.findViewById(R.id.fab_favourite_inst_loader_frag);
        FloatingActionButton review = view.findViewById(R.id.fab_review_inst_loader_frag);
        FloatingActionButton suggestFriend = view.findViewById(R.id.fab_suggest_friend_inst_loader_frag);
        FloatingActionButton reportInstitution = view.findViewById(R.id.fab_report_inst_loader_frag);

        navigation.setImageResource(R.drawable.ic_fab_location);
        favourite.setImageResource(R.drawable.ic_fab_favourites);
        review.setImageResource(R.drawable.ic_fab_review);
        suggestFriend.setImageResource(R.drawable.ic_fab_suggest_friends);
        reportInstitution.setImageResource(R.drawable.ic_fab_report);

        navigation.setOnClickListener(floatingActionButtonClickListener);
        favourite.setOnClickListener(floatingActionButtonClickListener);
        review.setOnClickListener(floatingActionButtonClickListener);
        suggestFriend.setOnClickListener(floatingActionButtonClickListener);
        reportInstitution.setOnClickListener(floatingActionButtonClickListener);

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
                    controller.fabNavigateBtnClick();
                    break;
                case R.id.fab_favourite_inst_loader_frag:
                    controller.validateAndProceedFavBtn(InstitutionLoaderFragment.id);
                    break;
                case R.id.fab_review_inst_loader_frag:
                    controller.fabReviewBtnClick();
                    break;
                case R.id.fab_suggest_friend_inst_loader_frag:
                    controller.fabSuggestBtnClick();
                    break;
                case R.id.fab_report_inst_loader_frag:
                    controller.validateAndProceedReportBtn(InstitutionLoaderFragment.id);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void navigateDirection() {

        GPSTracker gpsTracker = new GPSTracker(getContext());
        if (gpsTracker.canGetLocation()) {
            Intent intent = new Intent(getContext(),  MapsActivity.class);
            startActivity(intent);
        } else {
            gpsTracker.showSettingsAlert();
        }

    }

    @Override
    public void reviewInstitution() {
        Toast.makeText(getContext(), "Review", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void suggestFriends() {
        Toast.makeText(getContext(), "Suggest", Toast.LENGTH_SHORT).show();

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

        Query query = mDatabase.getReference().child("users").child(NavigationDrawerFragment.currentUser.getUid()).child("favourites");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    System.out.println("================================================");
                    System.out.println(postSnapshot.getKey() +":"+postSnapshot.getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void displaySnackbar(String message) {
        PopupCollections.simpleSnackBar(currentView, message, LoginFragment.COLOR_GREEN);
    }

}
