package com.shikshyaguru.shikshyaguru._3_signUp_activity.presenter;
/*
 * Created by Pankaj Koirala on 3/28/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.NewUserData;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.AuthUserDataSourceInterface;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.LoginViewInterface;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.SignUpViewInterface;

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

    public void onLoginBtnClick() {
        loginViewInterface.loginBtnClick();
    }

    public void onSignUpClick() {
        loginViewInterface.signUpClick();
    }

    public void onForgetPasswordClick() {
        loginViewInterface.forgetPasswordClick();
    }

    public void onStudentIconClick() {
        loginViewInterface.studentIconClick();
    }

    public void onTeacherIconClick() {
        loginViewInterface.teacherIconClick();
    }

    public void onInstitutionIconClick() {
        loginViewInterface.institutionIconClick();
    }

    public void onFacebookIconClick() {
        loginViewInterface.facebookIconClick();
    }

    public void onTwitterIconClick() {
        loginViewInterface.twitterIconClick();
    }

    public void onGoogleIconClick() {
        loginViewInterface.googleIconClick();
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

    public void onSignUpBtnClick() {
        signUpViewInterface.signUpBtnClick();
    }

    public void onSignInClick() {
        signUpViewInterface.signInClick();
    }

    public void createNewUser(String uId, NewUserData newUserData) {
        dataSource.createNewUser(uId, newUserData);
    }

}
