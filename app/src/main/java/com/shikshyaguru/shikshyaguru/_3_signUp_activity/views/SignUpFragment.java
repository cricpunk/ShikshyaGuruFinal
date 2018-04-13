package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;

import android.content.Intent;
import android.graphics.Color;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.InternetConnection;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Styles;
import com.shikshyaguru.shikshyaguru._0_7_shared_preferences.PrefManager;
import com.shikshyaguru.shikshyaguru._0_8_validation.PerformValidation;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.NewUserData;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.AuthAuthUserDataSource;
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

    private FirebaseAuth mAuth;

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._3_6_sign_up_fragment_new, container, false);
        initComponents(view);

        prefManager = new PrefManager(Objects.requireNonNull(getContext()), LoginFragment.PREF_NAME);
        mAuth = FirebaseAuth.getInstance();

        controller = new AuthenticationController(this, new AuthAuthUserDataSource());

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
    public void signUpBtnClick() {

        final String txtUserName = userName.getText().toString();
        final String txtEmail = email.getText().toString();
        final String txtPassword = password.getText().toString();
        final String txtConfirmPassword = confirmPassword.getText().toString();

        String uEmail = txtUserName.trim() + "@shikshyaguru.com";

        if (InternetConnection.hasInternetConnection(Objects.requireNonNull(getContext()))) {

            if (userType != 0) {

                if (
                        PerformValidation.userName(getActivity(), userName) &&
                                PerformValidation.emailId(getActivity(), email) &&
                                PerformValidation.passwords(getActivity(), password, confirmPassword)
                        ) {

                    signUpBtn.startAnimation();
                    mAuth.createUserWithEmailAndPassword(uEmail, txtConfirmPassword)
                            .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            LoginFragment.USER_PROVIDER = LoginFragment.CUSTOM_USER_PROVIDER;
                                            NewUserData newUserData = new NewUserData("", txtUserName, txtEmail, txtPassword, String.valueOf(userType));
                                            controller.createNewUser(user.getUid(), newUserData);
                                            signUpBtn.setDoneColor(Color.parseColor(LoginFragment.COLOR_GREEN));
                                            signUpBtn.revertAnimation();
                                            updateUI(user);
                                        }
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        //updateUI(null);
                                        signUpBtn.revertAnimation();
                                        PopupCollections.simpleSnackBar(currentLayout, Objects.requireNonNull(task.getException()).getLocalizedMessage(), LoginFragment.COLOR_RED);
                                    }

                                }
                            });

                }
            } else {
                PopupCollections.tooltipMessage(Objects.requireNonNull(getActivity()), studentIcon, "Please select best matching icon for you !").show();
            }
        } else {
            PopupCollections.simpleSnackBar(currentLayout, LoginFragment.NO_INTERNET_CONNECTION, LoginFragment.COLOR_GREEN);
        }



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
                controller.onSignUpBtnClick();
                break;

            case R.id.lbl_sign_in:
                controller.onSignInClick();
                break;
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

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // Set login status true
            prefManager.setUserLoggedIn(true);
            // Start homepage activity
            startActivity(new Intent(getContext(), HomePageActivity.class));
            // Finish current activity
            Objects.requireNonNull(getActivity()).finish();
        } else {
            prefManager.setUserLoggedIn(false);
            Objects.requireNonNull(getActivity()).finish();
            startActivity(new Intent(getContext(), AuthenticationActivity.class));
            Log.w(TAG, "No user");
        }

    }


}
