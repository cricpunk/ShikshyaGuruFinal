package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;
/*
 * Created by Pankaj Koirala on 9/25/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_7_shared_preferences.PrefManager;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.AuthenticationActivity;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginFragment;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.DataSource;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.DrawerListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.presenter.HomePageController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionsHomePageActivity;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserHomePageActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NavigationDrawerFragment extends Fragment implements DrawerInterface, View.OnClickListener {

    private View rootView;
    public static final String PREF_FILE_NAME = "testPref";
    public static final String USER_LEARNED_DRAWER = "user_learned_drawer";
    // Remember that i have put category id for favourites as 100 through out this project;
    public static final int FAVOURITE_CATEGORY = 100;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private LayoutInflater layoutInflater;
    private List<DrawerListItem> listOfDrawerHeader;
    private ArrayList<String> favInstList;
    private HomePageController controller;

    private FirebaseAuth mAuth;
    public static FirebaseUser currentUser;

    public NavigationDrawerFragment() {
        //Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._4_6_navigation_drawer_fragment, container, false);
        this.rootView = view;
        this.layoutInflater = inflater;

        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(Objects.requireNonNull(getActivity()), USER_LEARNED_DRAWER));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        navigationDrawerSection();
        controller = new HomePageController(this, new DataSource());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void navigationDrawerSection() {
        ImageView userProfile = rootView.findViewById(R.id.iv_nav_drawer_user_profile_pic);
        TextView userName = rootView.findViewById(R.id.lbl_nav_drawer_user_name);
        TextView userEmail = rootView.findViewById(R.id.lbl_nav_drawer_user_email);

        if (currentUser != null) {

            Picasso.get()
                    .load(currentUser.getPhotoUrl())
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(userProfile);

            userName.setText(currentUser.getDisplayName());
            userEmail.setText(currentUser.getEmail());
        }
        userProfile.setOnClickListener(this);

    }

    public void setUpNavigationDrawer(int fragmentId, DrawerLayout drawerlayout, final Window getWindow) {
        View mDrawerFragment = Objects.requireNonNull(getActivity()).findViewById(fragmentId);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerlayout, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
//                if (slideOffset > 0) {
////                    HomePageMainFragment.mSearchView.setAlpha( (2 - slideOffset));
////                    getActivity().findViewById(HomePageMainFragment.getAppBarID()).setAlpha(2 - slideOffset);
////                    getWindow.setStatusBarColor(ContextCompat.getColor(getContext(), R.color.semi_transparent));
//                    getWindow.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                }
//                if (slideOffset < 0.2) {
//                    getWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//                }

            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            drawerlayout.openDrawer(mDrawerFragment);
        }
        drawerlayout.setDrawerListener(mDrawerToggle);
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, "false");
    }

//    public static String readFromPreferences(Context context, String preferenceName, String preferenceValue) {
//        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(preferenceName, preferenceValue);
//    }

    @Override
    public void setUpDrawerMainHeader(List<DrawerListItem> drawerListItems) {

        this.listOfDrawerHeader = drawerListItems;
        RecyclerView mDrawerMainHeaderRecyclerView = rootView.findViewById(R.id.rec_drawer_header);
        mDrawerMainHeaderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DrawerHeaderAdapter drawerHeaderAdapter = new DrawerHeaderAdapter();
        mDrawerMainHeaderRecyclerView.setAdapter(drawerHeaderAdapter);

    }

    @Override
    public void favouriteInstitutionList(ArrayList<String> favInstList) {
        this.favInstList = favInstList;
    }

    @Override
    public void onUserProfileClickListener() {
        Intent intent = new Intent(getContext(), UserHomePageActivity.class);
        intent.putExtra("REQUEST_CODE", "user_loader");
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        controller.onUserProfileClick();
    }

    private class DrawerHeaderAdapter extends RecyclerView.Adapter<DrawerHeaderAdapter.DrawerHeaderViewHolder> {

        @NonNull
        @Override
        public DrawerHeaderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_7_rec_drawer_items, parent, false);
            return new DrawerHeaderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DrawerHeaderViewHolder holder, int position) {
            DrawerListItem currentItem = listOfDrawerHeader.get(position);
            holder.mDrawerHeaderIcon.setImageResource(currentItem.getIcon());
            holder.mDrawerHeader.setText(currentItem.getHeader());
            // Favourites
            if (position == 2) {
                holder.mDrawerNotiCounter.setVisibility(View.VISIBLE);
                holder.mDrawerNotiCounter.setText(String.valueOf(favInstList.size()));
            }
        }

        @Override
        public int getItemCount() {
            return listOfDrawerHeader.size();
        }

        class DrawerHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView mDrawerHeaderIcon;
            private TextView mDrawerHeader;
            private TextView mDrawerNotiCounter;
            private RelativeLayout rootView;

            DrawerHeaderViewHolder(View itemView) {
                super(itemView);

                mDrawerHeaderIcon = itemView.findViewById(R.id.iv_drawer_header_icon);
                mDrawerHeader = itemView.findViewById(R.id.lbl_drawer_header);
                mDrawerNotiCounter = itemView.findViewById(R.id.lbl_drawer_noti_counter);
                rootView = itemView.findViewById(R.id.root_drawer_item);
                rootView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {

                switch (this.getAdapterPosition()) {

                    // Profile
                    case 0:
                        onUserProfileClickListener();
                        break;

                    // Messages
                    case 1:
                        openMessage();
                        break;

                    // Favourites
                    case 2:
                        openFavourites();
                        break;

                    // Followers
                    case 3:
                        openFollowers();
                        break;

                    // Following
                    case 4:
                        openFollowing();
                        break;

                    // Questions
                    case 5:
                        openQuestions();
                        break;

                    // Logout
                    case 6:
                        logout();
                        break;

                    default:
                        break;
                }
                //Toast.makeText(getContext(), "Clicked At : " + this.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }

            private void openMessage() {
                Intent intent = new Intent(getContext(), UserHomePageActivity.class);
                intent.putExtra("REQUEST_CODE", "message_loader");
                intent.putExtra("TITLE", "Messages");
                startActivity(intent);
            }

            private void openFavourites() {
                Intent intent = new Intent(getContext(), InstitutionsHomePageActivity.class);
                intent.putExtra("REQUEST_CODE", "institutions_main");
                intent.putExtra("CATEGORY", FAVOURITE_CATEGORY);
                intent.putExtra("TITLE", "Favourite Institutions");
                intent.putStringArrayListExtra("FAVOURITES", favInstList);
                startActivity(intent);
            }

            private void openFollowers() {
                Intent intent = new Intent(getContext(), UserHomePageActivity.class);
                intent.putExtra("REQUEST_CODE", "user_main");
                intent.putExtra("TITLE", "Followers");
                startActivity(intent);
            }

            private void openFollowing() {
                Intent intent = new Intent(getContext(), UserHomePageActivity.class);
                intent.putExtra("REQUEST_CODE", "user_main");
                intent.putExtra("TITLE", "Following");
                startActivity(intent);
            }

            private void openQuestions() {
                Intent intent = new Intent(getContext(), UserHomePageActivity.class);
                intent.putExtra("REQUEST_CODE", "question_loader");
                intent.putExtra("TITLE", "Questions");
                startActivity(intent);
            }

            private void logout() {

                switch (LoginFragment.USER_PROVIDER) {

                    case "facebook.com":
                        //Log out from facebook
                        mAuth.signOut();
                        LoginManager.getInstance().logOut();
                        updateUi();
                        break;

                    case "twitter.com":
                        mAuth.signOut();
                        updateUi();
                        break;

                    case "google.com":

                        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(getString(R.string.default_web_client_id))
                                .requestEmail()
                                .build();
                        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(Objects.requireNonNull(getContext()), gso);

                        mGoogleSignInClient.signOut().addOnCompleteListener(Objects.requireNonNull(getActivity()),
                                new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        mAuth.signOut();
                                        updateUi();
                                    }
                                });
                        break;

                    case "custom":
                        mAuth.signOut();
                        updateUi();
                        break;

                    default:
                        break;

                }




            }

            private void updateUi() {
                PrefManager prefManager = new PrefManager(Objects.requireNonNull(getContext()), LoginFragment.PREF_NAME);
                prefManager.setUserLoggedIn(false);
                //Start login activity
                startActivity(new Intent(getContext(), AuthenticationActivity.class));
                Objects.requireNonNull(getActivity()).finish();
            }

        }
    }

}
