package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;
/*
 * Created by Pankaj Koirala on 9/27/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.DrawerListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DrawerInterface {

    void onUserProfileClickListener();

    void setUpDrawerMainHeader(List<DrawerListItem> drawerListItems);

    void favouriteInstitutionList(ArrayList<String> favInstList);

    void followerList(HashMap<String, Boolean> followers);

    void followingList(HashMap<String, Boolean> following);

}
