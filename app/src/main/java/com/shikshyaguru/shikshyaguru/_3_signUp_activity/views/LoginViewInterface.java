package com.shikshyaguru.shikshyaguru._3_signUp_activity.views;
/*
 * Created by Pankaj Koirala on 3/28/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.google.firebase.auth.FirebaseUser;

public interface LoginViewInterface {

    void updateUI(FirebaseUser user, String serviceProvider);

    void showSnackBar(String message, String color);

}
