package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;
/*
 * Created by Pankaj Koirala on 9/27/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.DrawerListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.UserData;

import java.util.List;

public interface DrawerInterface {

    void onUserProfileClickListener(UserData userData);

    void setUpDrawerMainHeader(List<DrawerListItem> drawerListItems);
}
