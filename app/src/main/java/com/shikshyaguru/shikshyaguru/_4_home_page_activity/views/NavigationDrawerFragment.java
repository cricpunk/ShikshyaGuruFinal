package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;
/*
 * Created by Pankaj Koirala on 9/25/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.logic.HomePageController;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.DrawerListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.FakeDataSource;

import java.util.List;

public class NavigationDrawerFragment extends Fragment implements DrawerInterface {

    public static final String PREF_FILE_NAME = "testpref";
    public static final String KEY_USER_LERNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private View mDrawerFragment;
    private Window getWindow;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;

    private LayoutInflater layoutInflater;
    private List<DrawerListItem> listOfDrawerHeader;
    private RecyclerView mDrawerMainHeaderRecyclerView;
    private HomePageController homePageController;

    public NavigationDrawerFragment() {
        //Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_LERNED_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.layoutInflater = inflater;
        return inflater.inflate(R.layout._4_6_navigation_drawer_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDrawerMainHeaderRecyclerView = (RecyclerView) view.findViewById(R.id.rec_drawer_header);

        homePageController = new HomePageController(this, new FakeDataSource());
    }

    public void setUpNavigationDrawer(int fragmentId, DrawerLayout drawerlayout, final Window getWindow) {
        mDrawerLayout = drawerlayout;
        this.getWindow = getWindow;
        mDrawerFragment = getActivity().findViewById(fragmentId);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerlayout, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LERNED_DRAWER, mUserLearnedDrawer + "");
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
            mDrawerLayout.openDrawer(mDrawerFragment);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, preferenceValue);
    }

    @Override
    public void setUpDrawerMainHeader(List<DrawerListItem> drawerListItems) {
        this.listOfDrawerHeader = drawerListItems;
        mDrawerMainHeaderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DrawerHeaderAdapter drawerHeaderAdapter = new DrawerHeaderAdapter();
        mDrawerMainHeaderRecyclerView.setAdapter(drawerHeaderAdapter);
    }

    private class DrawerHeaderAdapter extends RecyclerView.Adapter<DrawerHeaderAdapter.DrawerHeaderViewHolder> {

        @Override
        public DrawerHeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = layoutInflater.inflate(R.layout._4_7_rec_drawer_items, parent, false);
            return new DrawerHeaderViewHolder(view);
        }

        @Override
        public void onBindViewHolder(DrawerHeaderViewHolder holder, int position) {
            DrawerListItem currentItem = listOfDrawerHeader.get(position);
            holder.mDrawerHeaderIcon.setImageResource(currentItem.getIcon());
            holder.mDrawerHeader.setText(currentItem.getHeader());
        }

        @Override
        public int getItemCount() {
            return listOfDrawerHeader.size();
        }

        class DrawerHeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnTouchListener {

            private ImageView mDrawerHeaderIcon;
            private TextView mDrawerHeader;
            private ConstraintLayout rootView;

            DrawerHeaderViewHolder(View itemView) {
                super(itemView);

                mDrawerHeaderIcon = (ImageView) itemView.findViewById(R.id.iv_drawer_header_icon);
                mDrawerHeader = (TextView) itemView.findViewById(R.id.lbl_drawer_header);
                rootView = (ConstraintLayout) itemView.findViewById(R.id.root_drawer_item);
                rootView.setOnClickListener(this);
                rootView.setOnTouchListener(this);
            }

            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked At : " + this.getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
//                        rootView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.drawerHeaderItemClick));
                        break;
                    case MotionEvent.ACTION_UP:
//                        rootView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.drawerHeaderItemBg));
                        break;
                    default:
                        break;
                }
                return false;
            }
        }
    }

}
