package com.shikshyaguru.shikshyaguru._3_signUp_activity.presenter;
/*
 * Created by Pankaj Koirala on 3/28/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.support.v4.app.FragmentActivity;
import android.widget.EditText;

import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.AuthUserDataSourceInterface;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.NewUserData;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginViewInterface;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.SignUpViewInterface;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class AuthenticationController {

    private LoginViewInterface loginViewInterface;
    private SignUpViewInterface signUpViewInterface;
    private AuthUserDataSourceInterface dataSource;

    public AuthenticationController(LoginViewInterface viewInterface, AuthUserDataSourceInterface dataSource) {
        this.loginViewInterface = viewInterface;
        this.dataSource = dataSource;
    }

    public AuthenticationController(SignUpViewInterface signUpViewInterface, AuthUserDataSourceInterface dataSource) {
        this.signUpViewInterface = signUpViewInterface;
        this.dataSource = dataSource;
    }

    public void onLoginBtnClick(FragmentActivity activity, CircularProgressButton loginBtn, EditText userName, EditText password) {
        //loginViewInterface.loginBtnClick();
        dataSource.loginBtnClick(loginViewInterface, activity, loginBtn, userName, password);
    }

    public void onStudentIconClickSU() {
        signUpViewInterface.studentIconClick();
    }

    public void onTeacherClickSU() {
        signUpViewInterface.teacherIconClick();
    }

    public void onInstitutionClickSU() {
        signUpViewInterface.institutionIconClick();
    }

    public void onSignUpBtnClick(FragmentActivity activity, int userType, EditText userName, EditText email, EditText password, EditText confirmPassword, CircularProgressButton signUpBtn) {
        dataSource.signUpBtnClick(signUpViewInterface, activity, userType, userName, email, password, confirmPassword, signUpBtn);
    }

    public void onSignInClick() {
        signUpViewInterface.signInClick();
    }

    public void createNewUser(String uId, NewUserData newUserData) {
        dataSource.createNewUser(uId, newUserData);
    }



}
