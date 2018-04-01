package com.shikshyaguru.shikshyaguru._3_signUp_activity.presenter;
/*
 * Created by Pankaj Koirala on 3/28/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.UserDataSourceInterface;
import com.shikshyaguru.shikshyaguru._3_signUp_activity.views.AuthenticationViewInterface;

public class AuthenticationController {

    private AuthenticationViewInterface viewInterface;
    private UserDataSourceInterface dataSource;

    public AuthenticationController(AuthenticationViewInterface viewInterface, UserDataSourceInterface dataSource) {
        this.viewInterface = viewInterface;
        this.dataSource = dataSource;
    }

    public void onLoginBtnClick() {
        viewInterface.loginBtnClick();
    }

    public void onSignUpClick() {
        viewInterface.signUpClick();
    }

    public void onForgetPasswordClick() {
        viewInterface.forgetPasswordClick();
    }

    public void onStudentIconClick() {
        viewInterface.studentIconClick();
    }

    public void onTeacherIconClick() {
        viewInterface.teacherIconClick();
    }

    public void onInstitutionIconClick() {
        viewInterface.institutionIconClick();
    }

    public void onFacebookIconClick() {
        viewInterface.facebookIconClick();
    }

    public void onTwitterIconClick() {
        viewInterface.twitterIconClick();
    }

    public void onGoogleIconClick() {
        viewInterface.googleIconClick();
    }

}
