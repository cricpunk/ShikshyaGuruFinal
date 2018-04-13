package com.shikshyaguru.shikshyaguru._7_user_activity.views.views.presenter;

import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.model.UserDataSourceInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.model.UserDetails;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserLoaderInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserMainInterface;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class UserController {

    private UserLoaderInterface loaderInterface;
    private UserMainInterface mainInterface;
    private UserDataSourceInterface dataSource;

    public UserController(UserMainInterface mainInterface, UserDataSourceInterface dataSource) {
        this.mainInterface = mainInterface;
        this.dataSource = dataSource;
    }

    public UserController(UserLoaderInterface loaderInterface, UserDataSourceInterface dataSource) {
        this.loaderInterface = loaderInterface;
        this.dataSource = dataSource;
    }


    public void displayAllUser(String category) {
        mainInterface.showSpinner();
        mainInterface.setUpUsersAdapter(dataSource.displayAll(mainInterface, category));
    }


    public void openUserLoaderPage(UserDetails userDetails) {
        mainInterface.openUserLoaderPage(userDetails);
    }

    public void setUserProfile(String uId) {
        loaderInterface.setUserProfile(dataSource.getUserProfileDetails(loaderInterface, uId));
    }
}
