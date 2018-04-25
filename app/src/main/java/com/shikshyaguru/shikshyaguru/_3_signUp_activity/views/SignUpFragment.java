package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.InternetConnection;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Styles;
import com.shikshyaguru.shikshyaguru._0_7_shared_preferences.PrefManager;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.AuthUserDataSource;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.presenter.AuthenticationController;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.HomePageActivity;

import java.util.Objects;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;


/**
 * Created by cricpunk on 7/12/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class SignUpFragment extends Fragment implements SignUpViewInterface, View.OnClickListener{

    public static final String TAG = "Sign up Fragment";
    PrefManager prefManager;

    private  int userType = 0;
    private AuthenticationController controller;

    RelativeLayout currentLayout;
    ImageView studentIcon, teacherIcon, institutionIcon;
    EditText userName, email, password, confirmPassword;
    CircularProgressButton signUpBtn;
    TextView signIn;

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._3_6_sign_up_fragment_new, container, false);
        initComponents(view);

        prefManager = new PrefManager(Objects.requireNonNull(getContext()), LoginFragment.PREF_NAME);

        controller = new AuthenticationController(this, new AuthUserDataSource());

        return view;
    }

    private void initComponents(View view) {

        currentLayout = view.findViewById(R.id.signup_fragment);
        studentIcon = view.findViewById(R.id.ico_student);
        teacherIcon = view.findViewById(R.id.ico_teacher);
        institutionIcon = view.findViewById(R.id.ico_institution);

        userName = view.findViewById(R.id.et_sign_up_user_name);
        email = view.findViewById(R.id.et_sign_up_email);
        password = view.findViewById(R.id.et_sign_up_password);
        confirmPassword = view.findViewById(R.id.et_sign_up_confirm_password);

        signUpBtn = view.findViewById(R.id.btn_sign_up);
        signIn = view.findViewById(R.id.lbl_sign_in);

        userName.setOnFocusChangeListener(Styles.textViewAnimation);
        email.setOnFocusChangeListener(Styles.textViewAnimation);
        password.setOnFocusChangeListener(Styles.textViewAnimation);
        confirmPassword.setOnFocusChangeListener(Styles.textViewAnimation);

        studentIcon.setOnClickListener(this);
        teacherIcon.setOnClickListener(this);
        institutionIcon.setOnClickListener(this);

        signUpBtn.setOnClickListener(this);
        signIn.setOnClickListener(this);



    }

    @Override
    public void studentIconClick() {
        selectedUserType(studentIcon);
    }

    @Override
    public void teacherIconClick() {
        selectedUserType(teacherIcon);
    }

    @Override
    public void institutionIconClick() {
        selectedUserType(institutionIcon);
    }

    @Override
    public void signInClick() {
        startActivity(new Intent(getContext(), AuthenticationActivity.class));
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ico_student:
                controller.onStudentIconClickSU();
                break;

            case R.id.ico_teacher:
                controller.onTeacherClickSU();
                break;

            case R.id.ico_institution:
                controller.onInstitutionClickSU();
                break;

            case R.id.btn_sign_up:
                signUpBtnClick();
                break;

            case R.id.lbl_sign_in:
                controller.onSignInClick();
                break;
        }

    }

    public void signUpBtnClick() {

        if (InternetConnection.hasInternetConnection(Objects.requireNonNull(getContext()))) {

            if (userType != 0) {

                controller.onSignUpBtnClick(getActivity(), userType, userName, email, password, confirmPassword, signUpBtn);

            } else {

                PopupCollections.tooltipMessage(Objects.requireNonNull(getActivity()), studentIcon, "Please select best matching icon for you !").show();

            }

        } else {

            PopupCollections.simpleSnackBar(currentLayout, LoginFragment.NO_INTERNET_CONNECTION, LoginFragment.COLOR_GREEN);

        }

    }

    private void selectedUserType(ImageView imageView) {

        if (imageView == studentIcon) {
            imageView.setImageResource(R.drawable.ic_checked);
            teacherIcon.setImageDrawable(null);
            institutionIcon.setImageDrawable(null);
            userType = 1;

        } else if (imageView == teacherIcon) {
            imageView.setImageResource(R.drawable.ic_checked);
            studentIcon.setImageDrawable(null);
            institutionIcon.setImageDrawable(null);
            userType = 2;

        } else if (imageView == institutionIcon) {
            imageView.setImageResource(R.drawable.ic_checked);
            teacherIcon.setImageDrawable(null);
            studentIcon.setImageDrawable(null);
            userType = 3;

        }

    }

    @Override
    public void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // Set login status true
            prefManager.setUserLoggedIn(true);
            // Set service provider name
            prefManager.setServiceProvider("custom");
            // Set user id
            prefManager.setCurrentUID(currentUser.getUid());
            // Start homepage activity
            startActivity(new Intent(getContext(), HomePageActivity.class));
            // Finish current activity
            Objects.requireNonNull(getActivity()).finish();
        } else {
            prefManager.setUserLoggedIn(false);
            prefManager.setServiceProvider(null);
            prefManager.setCurrentUID(null);
            Objects.requireNonNull(getActivity()).finish();
            startActivity(new Intent(getContext(), AuthenticationActivity.class));
            Log.w(TAG, "No user");
        }

    }

    @Override
    public void showSnackBar(String localizedMessage) {
        PopupCollections.simpleSnackBar(currentLayout, localizedMessage, LoginFragment.COLOR_RED);
    }


}
