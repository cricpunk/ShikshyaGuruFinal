package com.shikshyaguru.shikshyaguru._7_user_activity.presenter;

import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDataSourceInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDetails;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.ChatInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.MessageInterface;
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
    private MessageInterface messageInterface;
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

    public UserController(MessageInterface messageInterface, UserDataSourceInterface dataSource) {
        this.messageInterface = messageInterface;
        this.dataSource = dataSource;
    }

    public void displayAllUser(String category) {
        mainInterface.showSpinner();
        mainInterface.setUpUsersAdapter(dataSource.getAllUsers(mainInterface, category));
    }


    public void openUserLoaderPage(UserDetails userDetails) {
        dataSource.loadUserFullPage(mainInterface, userDetails);
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

    public void displayAllMessages() {
        dataSource.getAllMessages(messageInterface);
    }

    public void sendMessage(String friendUID, String message) {
        dataSource.sendMessage(friendUID, message);
    }

    public void sendFollowRequest(String friendId) {
        dataSource.sendFollowRequest(mainInterface, friendId);
    }

    public void acceptFollowingRequest(String friendId) {
        dataSource.acceptFollowingRequest(mainInterface, friendId);
    }

    public void stopFollowing(String friendId) {
        dataSource.stopFollowing(mainInterface, friendId);
    }

    public void cancelFollowingRequest(String friendId) {
        dataSource.cancelFollowingRequest(mainInterface, friendId);
    }

}
