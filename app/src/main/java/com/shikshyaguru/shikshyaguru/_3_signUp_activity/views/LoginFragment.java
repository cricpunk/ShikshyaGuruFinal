package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_7_shared_preferences.PrefManager;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.UsersDataSource;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.presenter.AuthenticationController;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.HomePageActivity;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by cricpunk on 7/12/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class LoginFragment extends Fragment implements AuthenticationViewInterface, View.OnClickListener{

    // Shared preferences file name
    public static final String PREF_NAME = "login_activity";

    private final String TAG = "LOGIN FRAGMENT";
    Button loginBtn;
    TextView signUpTv, forgetPasswordTv;
    ImageView studentIcon, teacherIcon, institutionIcon;
    ImageView facebookIcon, twitterIcon, googlePlusIcon;

    private PrefManager prefManager;
    private AuthenticationController controller;
    private CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout._3_5_login_fragment_new, container, false);

        //Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(Objects.requireNonNull(getContext()), PREF_NAME);

        mCallbackManager = CallbackManager.Factory.create();
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        if (prefManager.isUserLoggedIn()) {
            // Check if user is signed in
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponents(view);

        controller = new AuthenticationController(this, new UsersDataSource());
    }

    // Initialize views
    private void initComponents(View view) {

        loginBtn = view.findViewById(R.id.btn_login);
        signUpTv = view.findViewById(R.id.lbl_sign_up);
        forgetPasswordTv = view.findViewById(R.id.lbl_forget_password);
        studentIcon = view.findViewById(R.id.ico_student);
        teacherIcon = view.findViewById(R.id.ico_teacher);
        institutionIcon = view.findViewById(R.id.ico_institution);
        facebookIcon = view.findViewById(R.id.ico_facebook);
        twitterIcon = view.findViewById(R.id.ico_twitter);
        googlePlusIcon = view.findViewById(R.id.ico_google_plus);

        loginBtn.setOnClickListener(this);
        signUpTv.setOnClickListener(this);
        forgetPasswordTv.setOnClickListener(this);
        studentIcon.setOnClickListener(this);
        teacherIcon.setOnClickListener(this);
        institutionIcon.setOnClickListener(this);
        facebookIcon.setOnClickListener(this);
        twitterIcon.setOnClickListener(this);
        googlePlusIcon.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_login :
                controller.onLoginBtnClick();
                break;

            case R.id.lbl_sign_up :
                controller.onSignUpClick();
                break;

            case R.id.lbl_forget_password :
                controller.onForgetPasswordClick();
                break;

            case R.id.ico_student :
                controller.onStudentIconClick();
                break;

            case R.id.ico_teacher :
                controller.onTeacherIconClick();
                break;

            case R.id.ico_institution :
                controller.onInstitutionIconClick();
                break;

            case R.id.ico_facebook :
                controller.onFacebookIconClick();
                break;

            case R.id.ico_twitter :
                controller.onTwitterIconClick();
                break;

            case R.id.ico_google_plus :
                controller.onGoogleIconClick();
                break;

            default:
                break;
        }
    }

    // Click handlers, implemented AuthenticationViewInterface
    @Override
    public void loginBtnClick() {
        startActivity(new Intent(getContext(), HomePageActivity.class));
    }

    @Override
    public void signUpClick() {
        Intent intent = new Intent(getContext(), AuthenticationActivity.class);
        intent.putExtra("REQUEST_CODE", "sign_up");
        startActivity(intent);
    }

    @Override
    public void forgetPasswordClick() {

    }

    @Override
    public void studentIconClick() {

    }

    @Override
    public void teacherIconClick() {

    }

    @Override
    public void institutionIconClick() {

    }

    @Override
    public void facebookIconClick() {

        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "===========================================");
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                Log.d(TAG, "===========================================");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "===========================================");
                Log.d(TAG, "facebook:onCancel");
                Log.d(TAG, "===========================================");
                updateUI(null);
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "===========================================");
                Log.d(TAG, "facebook:onError", error);
                Log.d(TAG, "===========================================");
                updateUI(null);
                // ...
            }
        });

    }

    @Override
    public void twitterIconClick() {

    }

    @Override
    public void googleIconClick() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {

        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "===========================================");
                            Log.d(TAG, "signInWithCredential:success");
                            Log.d(TAG, "===========================================");

                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                updateUI(user);
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            prefManager.setUserLoggedIn(true);
            startActivity(new Intent(getContext(), HomePageActivity.class));
            Objects.requireNonNull(getActivity()).finish();
        } else {
            prefManager.setUserLoggedIn(false);
            startActivity(new Intent(getContext(), AuthenticationActivity.class));
            Log.w(TAG, "No user");
        }

    }



}
