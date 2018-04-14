package com.shikshyaguru.shikshyaguru._7_user_activity.presenter;

import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDataSourceInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDetails;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.ChatInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.UserLoaderInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.UserMainInterface;

import java.util.HashMap;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class UserController {

    private UserLoaderInterface loaderInterface;
    private UserMainInterface mainInterface;
    private ChatInterface chatInterface;
    private UserDataSourceInterface dataSource;

    public UserController(UserMainInterface mainInterface, UserDataSourceInterface dataSource) {
        this.mainInterface = mainInterface;
        this.dataSource = dataSource;
    }

    public UserController(UserLoaderInterface loaderInterface, UserDataSourceInterface dataSource) {
        this.loaderInterface = loaderInterface;
        this.dataSource = dataSource;
    }

    public UserController(ChatInterface chatInterface, UserDataSourceInterface dataSource) {
        this.chatInterface = chatInterface;
        this.dataSource = dataSource;
    }

    public void displayAllUser(String category) {
        mainInterface.showSpinner();
        mainInterface.setUpUsersAdapter(dataSource.getAllUsers(mainInterface, category));
    }


    public void openUserLoaderPage(UserDetails userDetails) {
        mainInterface.openUserLoaderPage(userDetails);
    }

    public void setUserProfile(String uId) {
        loaderInterface.setUserProfile(dataSource.getUserProfileDetails(loaderInterface, uId));
    }

    public void displayFollowersFollowing(HashMap<String, Boolean> list) {
        mainInterface.showSpinner();
        dataSource.getFollowerFollowingData(list, mainInterface);
    }

    public void suggestInstitutionToFriend(String friendId, String institutionId) {
        dataSource.makeInstitutionSuggestionToFriend(mainInterface, friendId, institutionId);
    }

    public void displayAllChats(String friendUID) {
        dataSource.getChatDetails(chatInterface, friendUID);
    }
}
