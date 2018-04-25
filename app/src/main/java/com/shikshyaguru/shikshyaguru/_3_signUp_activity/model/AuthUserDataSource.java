package com.shikshyaguru.shikshyaguru._3_signUp_activity.model;
/*
 * Created by Pankaj Koirala on 3/23/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shikshyaguru.shikshyaguru._0_6_widgets.PopupCollections;
import com.shikshyaguru.shikshyaguru._0_8_validation.PerformValidation;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginFragment;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginViewInterface;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.SignUpViewInterface;

import java.util.Objects;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class AuthUserDataSource implements AuthUserDataSourceInterface {

    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;

    private SignUpViewInterface signUpViewInterface;
    private LoginViewInterface loginViewInterface;
    private Activity activity;

    private CircularProgressButton loginBtn;
    private CircularProgressButton signUpBtn;

    private int userType;
    private EditText userName;
    private EditText email;

    private String txtUserName;
    private String txtEmail;
    private String txtPassword;
    private String txtConfirmPassword;
    // Signup section end

    @Override
    public void loginBtnClick(LoginViewInterface pLoginViewInterface, FragmentActivity pActivity, CircularProgressButton pLoginBtn, EditText pUserName, EditText pPassword) {

        this.loginViewInterface = pLoginViewInterface;
        this.activity = pActivity;
        this.loginBtn = pLoginBtn;
        this.userName = pUserName;

        mAuth = FirebaseAuth.getInstance();

        String email = this.userName.getText().toString() + "@shikshyaguru.com";
        String pass = pPassword.getText().toString();

        System.out.println("Result testing : " + email + pass );

        PerformValidation validation = new PerformValidation(activity);

        if (validation.userName(userName) && validation.password(pPassword)) {

            try {
                loginBtn.startAnimation();
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    LoginFragment.USER_PROVIDER = LoginFragment.CUSTOM_USER;
                                    loginViewInterface.updateUI(user, LoginFragment.CUSTOM_USER);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    loginBtn.revertAnimation();
                                    loginViewInterface.showSnackBar(Objects.requireNonNull(task.getException()).getLocalizedMessage(), LoginFragment.COLOR_GREEN);
                                }
                            }

                        });
            } catch (Exception e) {
                e.printStackTrace();
                loginBtn.revertAnimation();
                loginViewInterface.showSnackBar("Something went wrong !", LoginFragment.COLOR_RED);
            }
        }

    }

    @Override
    public void signUpBtnClick(SignUpViewInterface pInterface, FragmentActivity pActivity, int userType, EditText pUserName, EditText pEmail, EditText pPassword, EditText pConfirmPassword, CircularProgressButton pSignUpBtn) {

        this.signUpViewInterface = pInterface;
        this.activity = pActivity;
        this.userType = userType;
        this.userName = pUserName;
        this.email = pEmail;
        this.signUpBtn = pSignUpBtn;

        txtUserName = userName.getText().toString();
        txtEmail = email.getText().toString();
        txtPassword = pPassword.getText().toString();
        txtConfirmPassword = pConfirmPassword.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        PerformValidation validation = new PerformValidation(activity);

        if (validation.userName(userName) && validation.emailId(email) && validation.passwords(pPassword, pConfirmPassword)) {

            signUpBtn.startAnimation();
            Query userNameQuery = mDatabase.getReference().child("users").orderByChild("profile/user_name").equalTo(userName.getText().toString().trim());
            userNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if (dataSnapshot.getChildrenCount() != 0) {
                        PopupCollections.tooltipMessage(activity, userName, "This user name has been already taken ! ").show();
                        signUpBtn.revertAnimation();
                    } else {
                        validateEmail();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }


    }

    // Validate email existence in database
    private void validateEmail() {

        signUpBtn.startAnimation();
        Query emailQuery = mDatabase.getReference().child("users").orderByChild("profile/email").equalTo(email.getText().toString().trim());
        emailQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.getChildrenCount() != 0) {
                    PopupCollections.tooltipMessage(activity, email, "This email has already got access privilege. Signup with another account or go back to login page ! ").show();
                    signUpBtn.revertAnimation();
                } else {
                    // Call method to finished sign up process
                    signUpUser();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void signUpUser() {

        signUpBtn.startAnimation();

        String uEmail = txtUserName.trim() + "@shikshyaguru.com";
        System.out.println("print this result : " + txtUserName + txtEmail + txtPassword + txtConfirmPassword + uEmail);

        mAuth.createUserWithEmailAndPassword(uEmail, txtConfirmPassword)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                //LoginFragment.USER_PROVIDER = LoginFragment.CUSTOM_USER_PROVIDER;
                                //NewUserData newUserData = new NewUserData("New user", txtUserName, txtEmail, txtPassword, userType);

                                NewUserData newUserData = new NewUserData();
                                newUserData.setName("New user");
                                newUserData.setUser_name(txtUserName);
                                newUserData.setEmail(txtEmail);
                                newUserData.setPassword(txtPassword);
                                newUserData.setImage("https://firebasestorage.googleapis.com/v0/b/shikshyaguru-182814.appspot.com/o/users%2Fdefault%2Fman.svg?alt=media&token=14128d70-3f29-419e-8282-a5dc51efc4b7");
                                newUserData.setType(userType);

                                createNewUser(user.getUid(), newUserData);
                                signUpBtn.setDoneColor(Color.parseColor(LoginFragment.COLOR_GREEN));
                                signUpBtn.revertAnimation();
                                signUpViewInterface.updateUI(user);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            signUpBtn.revertAnimation();
                            signUpViewInterface.showSnackBar(Objects.requireNonNull(task.getException()).getLocalizedMessage());
                        }

                    }
                });

    }

    @Override
    public void createNewUser(String uId, NewUserData newUserData) {

        mDatabase.getReference().child("users").child(uId).child("profile").setValue(newUserData);

    }

}
