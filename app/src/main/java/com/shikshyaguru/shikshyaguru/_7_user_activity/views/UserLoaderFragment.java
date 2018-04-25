package com.shikshyaguru.shikshyaguru._7_user_activity.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDataSource;
import com.shikshyaguru.shikshyaguru._7_user_activity.presenter.UserController;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/*
 * Created by Pankaj Koirala on 9/30/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class UserLoaderFragment extends Fragment implements View.OnClickListener, UserLoaderInterface {

    private SlidingUpPanelLayout mainLayout;
    private View frontLayout;
    private View bgLayout;

    private ImageView bProfileBg, bBtnMenu, bBtnMoreIcon;

    private ImageView fUserProfilePic;
    private TextView fName, fType, fInstitution, fQuestion, fAnswer, fFollower, fFollowing, fMail;
    private TextView fBtnFollow, fBtnSendMessage, fBtnAskQuestion;

    private UserController controller;

    private String uId, image, name, email, uName, type, institution, followers, following;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._7_1_0_user_loader_fragment, container, false);

        if (getArguments() != null) {

            this.uId = getArguments().getString("UID");
            this.image = getArguments().getString("IMAGE");
            this.name = getArguments().getString("NAME");
            this.email = getArguments().getString("EMAIL");
            this.uName = getArguments().getString("USER_NAME");
            this.type = String.valueOf(getArguments().getInt("TYPE"));
            this.followers = getArguments().getString("FOLLOWERS");
            this.following = getArguments().getString("FOLLOWING");
            this.institution = getArguments().getString("INSTITUTION");

        }


        initComponents(view);
        initToolbarAndLayout();
        initSlidingPanel();

        controller = new UserController(this, new UserDataSource());

        controller.setUserProfile(uId);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initComponents(View view) {

        mainLayout = view.findViewById(R.id.sliding_layout);
        bgLayout = view.findViewById(R.id.include_background_content);
        frontLayout = view.findViewById(R.id.include_main_content);

        bProfileBg = bgLayout.findViewById(R.id.iv_user_loader_profile_bg);
        bBtnMenu = bgLayout.findViewById(R.id.iv_user_loader_menu);
        bBtnMoreIcon = bgLayout.findViewById(R.id.iv_user_loader_more_icon);

//        private ImageView fUserProfilePic;
//        private TextView fName, fType, fInstitution, fQuestion, fAnswer, fFollower, fFollowing, Fmail;
//        private TextView fBtnFollow, fBtnSendMessage, fBtnAskQuestion, fBtnSeeAllPeople;

        fUserProfilePic = frontLayout.findViewById(R.id.iv_user_loader_profile_pic);
        fName = frontLayout.findViewById(R.id.lbl_user_loader_name);
        fType = frontLayout.findViewById(R.id.lbl_user_loader_user_type);
        fInstitution = frontLayout.findViewById(R.id.lbl_user_loader_institution_name);
        fQuestion = frontLayout.findViewById(R.id.lbl_user_loader_question);
        fAnswer = frontLayout.findViewById(R.id.lbl_user_loader_answer);
        fFollower = frontLayout.findViewById(R.id.lbl_user_loader_follower);
        fFollowing = frontLayout.findViewById(R.id.lbl_user_loader_following);
        fMail = frontLayout.findViewById(R.id.lbl_user_loader_email);

        fBtnFollow = frontLayout.findViewById(R.id.btn_user_loader_follow_btn);
        fBtnSendMessage = frontLayout.findViewById(R.id.btn_user_loader_send_message);
        fBtnAskQuestion = frontLayout.findViewById(R.id.btn_user_loader_ask_question);

        fBtnSendMessage.setOnClickListener(this);
        fBtnAskQuestion.setOnClickListener(this);

        // Dent show follow button for ourselves
        if (uId.equals(NavigationDrawerFragment.currentUser.getUid())) {
            fBtnFollow.setVisibility(View.INVISIBLE);
            fBtnAskQuestion.setText(R.string.peoples);
            fBtnSendMessage.setText(R.string.update_profile);
        }

    }

    private void initToolbarAndLayout() {
        Toolbar toolbar = bgLayout.findViewById(R.id.tb_user_loader_frag);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);

        ActionBar getSupportActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (getSupportActionBar != null) {
            getSupportActionBar.setDisplayHomeAsUpEnabled(false);
            getSupportActionBar.setDisplayShowTitleEnabled(false);
        }

    }

    private void initSlidingPanel() {

        mainLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i("Sliding Up", "onPanelSlide, offset " + slideOffset);
                if (mainLayout != null) {
                    if (mainLayout.getAnchorPoint() == 1.0f) {
                        mainLayout.setAnchorPoint(0.19f);
                        mainLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                    }
                }

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i("Sliding Up", "onPanelStateChanged " + newState);
            }
        });

//        mainLayout.setFadeOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mainLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
//            }
//        });

    }

    @Override
    public void setUserProfile(Object userProfileDetails) {

        Picasso.get()
                .load(image)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_user)
                .into(fUserProfilePic);

        fName.setText(name);
        fMail.setText(email);

        if (type != null) {
            switch (type) {
                case "1":
                    fType.setText(R.string.typeStudent);
                    break;
                case "2":
                    fType.setText(R.string.typeTeacher);
                    break;
                case "3":
                    fType.setText(R.string.typeInstitution);
                    break;
                default:
                    break;

            }
        }
        fFollower.setText(followers);
        fFollowing.setText(following);
        fInstitution.setText(institution);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_user_loader_send_message:

                if (fBtnSendMessage.getText().toString().toLowerCase().equals("update profile")) {
                    Toast.makeText(getContext(), "Update profile", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Send Message", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_user_loader_ask_question:

                if (fBtnAskQuestion.getText().toString().toLowerCase().equals("peoples")) {

                    Intent intent = new Intent(getContext(), UserHomePageActivity.class);
                    intent.putExtra("REQUEST_CODE", "user_main");
                    intent.putExtra("TITLE", "Users");
                    intent.putExtra("CATEGORY", "all");
                    startActivity(intent);

                } else {
                    Toast.makeText(getContext(), "Ask Question", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }

}
