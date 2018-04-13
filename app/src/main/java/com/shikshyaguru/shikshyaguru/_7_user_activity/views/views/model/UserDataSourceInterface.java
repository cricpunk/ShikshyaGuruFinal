package com.shikshyaguru.shikshyaguru._7_user_activity.views.views.model;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserLoaderInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserMainInterface;

import java.util.HashMap;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public interface UserDataSourceInterface {

    FirebaseRecyclerOptions<UserDetails> getAllUsers(UserMainInterface loaderInterface, String category);

    void getFollowerFollowingData(HashMap<String, Boolean> list, UserMainInterface mainInterface);

    Object getUserProfileDetails(UserLoaderInterface loaderInterface, String uId);

    void makeInstitutionSuggestionToFriend(UserMainInterface mainInterface, String friendId, String institutionId);

}
