package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.CallbackManagerImpl;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.TwitterAuthProvider;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._0_6_widgets.InternetConnection;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._0_6_widgets.Styles;
import com.shikshyaguru.shikshyaguru._0_7_shared_preferences.PrefManager;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.NewUserData;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.AuthAuthUserDataSource;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.presenter.AuthenticationController;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.HomePageActivity;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

import java.util.Arrays;
import java.util.Objects;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * Created by cricpunk on 7/12/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class LoginFragment extends Fragment implements LoginViewInterface, View.OnClickListener{

    public static String USER_PROVIDER = null;
    public static final String CUSTOM_USER_PROVIDER = "custom";

    public static final String NO_INTERNET_CONNECTION = "Please check your internet connection !";
    public static final String SELECT_USER_TYPE = "Please select from icons which define you most (Student/Teacher/Institution).";
    public static final String COLOR_GREEN = "#4CAF50";
    public static final String COLOR_RED = "#F44336";

    // Shared preferences file name
    public static final String PREF_NAME = "login_activity";
    public static final String TAG = "LOGIN FRAGMENT";
    public static final int GOOGLE_SIGN_IN = 9000;

    // To check either user is student, teacher or institution
    private int userType = 0;
    RelativeLayout currentLayout;
    EditText userName, password;
    CircularProgressButton loginBtn;
    TextView signUpTv, forgetPasswordTv;
    ImageView studentIcon, teacherIcon, institutionIcon;
    ImageView facebookIcon, twitterIcon, googlePlusIcon;
    CardView userTypeCardView, socialTypeCardView;

    private PrefManager prefManager;
    private AuthenticationController controller;
    static CallbackManager mCallbackManager;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    static TwitterAuthClient mTwitterAuthClient;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout._3_5_login_fragment_new, container, false);

        //Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(Objects.requireNonNull(getContext()), PREF_NAME);

        // Firebase authentication initialization
        mAuth = FirebaseAuth.getInstance();
        // Facebook callback manager initialization for login
        mCallbackManager = CallbackManager.Factory.create();

        configureGoogle();
        configureTwitter();

        initComponents(view);

        if (prefManager.isUserLoggedIn()) {
            // If user is already logged in then update UI
            updateUI(mAuth.getCurrentUser());
        }

        if (prefManager.isUserTypeSet()) {
            userTypeCardView.setVisibility(View.GONE);
            // Set top margin for social type cardview
            socialTypeCardView.setLayoutParams(Styles.sSetMargin(socialTypeCardView, 0, 100, 0, 0));

        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        controller = new AuthenticationController(this, new AuthAuthUserDataSource());
    }

    // Initialize views
    private void initComponents(View view) {

        currentLayout = view.findViewById(R.id.login_fragment);
        userName = view.findViewById(R.id.et_login_user_name);
        password = view.findViewById(R.id.et_login_password);
        loginBtn = view.findViewById(R.id.btn_login);
        signUpTv = view.findViewById(R.id.lbl_sign_up);
        forgetPasswordTv = view.findViewById(R.id.lbl_forget_password);
        studentIcon = view.findViewById(R.id.ico_student);
        teacherIcon = view.findViewById(R.id.ico_teacher);
        institutionIcon = view.findViewById(R.id.ico_institution);
        facebookIcon = view.findViewById(R.id.ico_facebook);
        twitterIcon = view.findViewById(R.id.ico_twitter);
        googlePlusIcon = view.findViewById(R.id.ico_google_plus);
        userTypeCardView = view.findViewById(R.id.cv_user_type);
        socialTypeCardView = view.findViewById(R.id.cv_social_type);

        userName.setOnFocusChangeListener(Styles.textViewAnimation);
        password.setOnFocusChangeListener(Styles.textViewAnimation);

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

    // Twitter login configuration
    private void configureTwitter() {

        TwitterAuthConfig twitterAuthConfig = new TwitterAuthConfig(
                "S4RKfOsIvkP2aUcSP8XoCfRBB",
                "tJVMmUOERhUcYc1Lg6l2pM7yCyGbDSE403StCj3Y8O3bvkW8RN"
        );

        TwitterConfig twitterConfig = new TwitterConfig.Builder(Objects.requireNonNull(getActivity()))
                .twitterAuthConfig(twitterAuthConfig)
                .build();

        Twitter.initialize(twitterConfig);

    }

    // Google login configuration
    private void configureGoogle() {

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(Objects.requireNonNull(getContext()), gso);

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

    // Click handlers, implemented LoginViewInterface
    @Override
    public void loginBtnClick() {

        String un = userName.getText().toString();
        String email = userName.getText().toString() + "@shikshyaguru.com";
        String pass = password.getText().toString();

        if (!(un.trim().equals("")) && !(pass.trim().equals(""))) {

            loginBtn.startAnimation();
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                USER_PROVIDER = CUSTOM_USER_PROVIDER;
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                //updateUI(null);
                                loginBtn.revertAnimation();
                                PopupCollections.simpleSnackBar(currentLayout, Objects.requireNonNull(task.getException()).getLocalizedMessage(), COLOR_RED);
                            }
                        }

                    });
        } else {
            PopupCollections.simpleSnackBar(currentLayout, "Fields cannot be empty !", COLOR_GREEN);
        }

    }

    @Override
    public void signUpClick() {
        Intent intent = new Intent(getContext(), AuthenticationActivity.class);
        intent.putExtra("REQUEST_CODE", "sign_up");
        startActivity(intent);
        Objects.requireNonNull(getActivity()).finish();
    }

    @Override
    public void forgetPasswordClick() {

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
    public void facebookIconClick() {

        // Check internet connectivity
        if (InternetConnection.hasInternetConnection(Objects.requireNonNull(getContext()))) {

            if (prefManager.isUserTypeSet()) {

                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        //Display progress dialogue
                        PopupCollections.authenticationProgress(getContext()).show();
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        updateUI(null);
                        PopupCollections.simpleSnackBar(currentLayout, "Cancel login process..", COLOR_GREEN);
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        updateUI(null);
                        PopupCollections.simpleSnackBar(currentLayout, error.getMessage(), COLOR_RED);
                    }

                });

            } else if (userType != 0 ) {
                // This part will called only once. If user type is not set then this part will be trigger
                // Insert data from this part into firebase database
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "facebook:onSuccess:" + loginResult);
                        //Display progress dialogue
                        PopupCollections.authenticationProgress(getContext()).show();
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        updateUI(null);
                        PopupCollections.simpleSnackBar(currentLayout, "Cancel login process..", COLOR_GREEN);
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        updateUI(null);
                        PopupCollections.simpleSnackBar(currentLayout, error.getMessage(), COLOR_RED);
                    }

                });
            } else {
                PopupCollections.simpleSnackBar(currentLayout, SELECT_USER_TYPE, COLOR_GREEN);
            }

        } else {
            PopupCollections.simpleSnackBar(currentLayout, NO_INTERNET_CONNECTION, COLOR_GREEN);
        }

    }

    @Override
    public void twitterIconClick() {

        // Check internet connectivity
        if (InternetConnection.hasInternetConnection(Objects.requireNonNull(getContext()))) {

            // If user type is set there is no need to validate user type icon selection
            if (prefManager.isUserTypeSet()) {
                mTwitterAuthClient = new TwitterAuthClient();
                mTwitterAuthClient.authorize(Objects.requireNonNull(getActivity()), new Callback<TwitterSession>() {

                    @Override
                    public void success(Result<TwitterSession> result) {
                        Log.d(TAG, "twitterLogin:success" + result);
                        // Display authentication progress bar
                        PopupCollections.authenticationProgress(getContext()).show();
                        handleTwitterSession(result.data);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.w(TAG, "twitterLogin:failure", exception);
                        updateUI(null);
                        PopupCollections.simpleSnackBar(currentLayout, exception.getMessage(), COLOR_RED);
                    }
                });
            } else if (userType != 0 ) {
                // This part will called only once. If user type is not set then this part will be trigger
                // Insert data from this part into firebase database
                mTwitterAuthClient = new TwitterAuthClient();
                mTwitterAuthClient.authorize(Objects.requireNonNull(getActivity()), new Callback<TwitterSession>() {

                    @Override
                    public void success(Result<TwitterSession> result) {
                        Log.d(TAG, "twitterLogin:success" + result);
                        // Display authentication progress bar
                        PopupCollections.authenticationProgress(getContext()).show();
                        handleTwitterSession(result.data);
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Log.w(TAG, "twitterLogin:failure", exception);
                        updateUI(null);
                        PopupCollections.simpleSnackBar(currentLayout, exception.getMessage(), COLOR_RED);
                    }
                });
            } else {
                PopupCollections.simpleSnackBar(currentLayout, SELECT_USER_TYPE, COLOR_GREEN);
            }
        } else {
            PopupCollections.simpleSnackBar(currentLayout, NO_INTERNET_CONNECTION, COLOR_GREEN);
        }


    }

    @Override
    public void googleIconClick() {

        if (InternetConnection.hasInternetConnection(Objects.requireNonNull(getContext()))) {

            // If user type is set there is no need to validate user type icon selection
            if (prefManager.isUserTypeSet()) {
                Intent googleSignInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(googleSignInIntent, GOOGLE_SIGN_IN);
            } else if (userType != 0 ) {
                // This part will called only once. If user type is not set then this part will be trigger
                // Insert data from this part into firebase database
                Intent googleSignInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(googleSignInIntent, GOOGLE_SIGN_IN);
            } else {
                PopupCollections.simpleSnackBar(currentLayout, SELECT_USER_TYPE, COLOR_GREEN);
            }

        } else {
            PopupCollections.simpleSnackBar(currentLayout, NO_INTERNET_CONNECTION, COLOR_GREEN);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CallbackManagerImpl.RequestCodeOffset.Login.toRequestCode()) {

            mCallbackManager.onActivityResult(requestCode, resultCode, data);

        } else if (requestCode == GOOGLE_SIGN_IN){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                PopupCollections.authenticationProgress(getContext()).show();
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                updateUI(null);
                PopupCollections.simpleSnackBar(currentLayout, e.getMessage(), COLOR_RED);
            }

        } else if (requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE) {

            mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);

        }

    }

    // Facebook authentication
    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            USER_PROVIDER = "facebook.com";
                            createNewUser(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                            PopupCollections.simpleSnackBar(currentLayout, String.valueOf(task.getException()), COLOR_RED);
                        }

                        PopupCollections.authenticationProgress(getContext()).dismiss();
                    }
                });
    }

    // Twitter authentication
    private void handleTwitterSession(TwitterSession session) {
        Log.d(TAG, "handleTwitterSession:" + session);

        AuthCredential credential = TwitterAuthProvider.getCredential(
                session.getAuthToken().token,
                session.getAuthToken().secret);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            USER_PROVIDER = "twitter.com";
                            createNewUser(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                            PopupCollections.simpleSnackBar(currentLayout, String.valueOf(task.getException()), COLOR_RED);

                        }

                        PopupCollections.authenticationProgress(getContext()).dismiss();
                    }
                });
    }

    // Google authentication
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            USER_PROVIDER = "google.com";
                            createNewUser(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                            PopupCollections.simpleSnackBar(currentLayout, String.valueOf(task.getException()), COLOR_RED);
                        }

                        PopupCollections.authenticationProgress(getContext()).dismiss();
                    }
                });
    }

    private void updateUI(FirebaseUser currentUser) {

        if (currentUser != null) {
            // Set current user
            NavigationDrawerFragment.currentUser = currentUser;
            // Set login status true
            prefManager.setUserLoggedIn(true);
            // Set user type selection true
            prefManager.setUserType(true);
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

    private void createNewUser(FirebaseUser user) {

        if (userTypeCardView.getVisibility() != View.GONE  && !USER_PROVIDER.equals("twitter.com")) {

            if (user != null) {

                String[] emailArr = Objects.requireNonNull(user.getEmail()).split("@");
                String userName = emailArr[0];
                String txtEmail = user.getEmail();

                NewUserData newUserData = new NewUserData(user.getDisplayName(), userName, txtEmail, String.valueOf(userType));
                controller.createNewUser(user.getUid(), newUserData);

            }

        } else if (userTypeCardView.getVisibility() != View.GONE  && USER_PROVIDER.equals("twitter.com")) {

            if (user != null) {

                String userName = Objects.requireNonNull(user.getDisplayName()).replaceAll(" ", ".");
                NewUserData newUserData = new NewUserData(user.getDisplayName(), userName, String.valueOf(userType));
                controller.createNewUser(user.getUid(), newUserData);

            }

        }

    }

}
