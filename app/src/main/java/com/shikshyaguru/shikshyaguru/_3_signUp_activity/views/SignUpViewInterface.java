package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;

import com.google.firebase.auth.FirebaseUser;

/*
 * Created by Pankaj Koirala on 4/3/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public interface SignUpViewInterface {

    void studentIconClick();

    void teacherIconClick();

    void institutionIconClick();

    void signInClick();

    void updateUI(FirebaseUser currentUser);

    void showSnackBar(String localizedMessage);

}
