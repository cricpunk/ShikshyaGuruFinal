package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;
/*
 * Created by Pankaj Koirala on 9/27/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._3_signUp_activity.model.NewUserData;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.DrawerListItem;
import com.shikshyaguru.shikshyaguru._7_user_activity.model.UserDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface NavigationDrawerInterface {

    void setUpDrawerMainHeader(List<DrawerListItem> drawerListItems);

    void favouriteInstitutionList(ArrayList<String> favInstList);

    void followerList(HashMap<String, Boolean> followers);

    void followingList(HashMap<String, Boolean> following);

    void settingUpUserProfile(UserDetails userData);

}
