package com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.model.UserDetails;

import java.util.ArrayList;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public interface UserMainInterface {

    void showSpinner();

    void removeSpinner();

    void showSnackbar(String message);

    void setUpUsersAdapter(FirebaseRecyclerOptions<UserDetails> options);

    void openUserLoaderPage(UserDetails userDetails);

    void setUpFollowersFollowing( ArrayList<UserDetails> followersFollowing);

}
