package com.shikshyaguru.shikshyaguru._7_user_activity.views.views.model;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserLoaderInterface;
import com.shikshyaguru.shikshyaguru._7_user_activity.views.views.views.UserMainInterface;

/*
 * Created by Pankaj Koirala on 4/11/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public interface UserDataSourceInterface {

    FirebaseRecyclerOptions<UserDetails> displayAll(UserMainInterface loaderInterface, String category);

    Object getUserProfileDetails(UserLoaderInterface loaderInterface, String uId);

}
