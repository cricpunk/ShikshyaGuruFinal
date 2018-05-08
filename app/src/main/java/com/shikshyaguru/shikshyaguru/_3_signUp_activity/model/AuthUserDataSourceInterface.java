package com.shikshyaguru.shikshyaguru._3_signUp_activity.model;
/*
 * Created by Pankaj Koirala on 3/23/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginViewInterface;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.SignUpViewInterface;

import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public interface AuthUserDataSourceInterface {


    void loginBtnClick(LoginViewInterface loginViewInterface, FragmentActivity activity, CircularProgressButton loginBtn, EditText userName, EditText password);

    void signUpBtnClick(SignUpViewInterface signUpViewInterface, FragmentActivity activity, int userType, EditText userName, EditText email, EditText password, EditText confirmPassword, CircularProgressButton signUpBtn);

    void createNewUser(String uId, Map<String, Object> newUserData);

}
