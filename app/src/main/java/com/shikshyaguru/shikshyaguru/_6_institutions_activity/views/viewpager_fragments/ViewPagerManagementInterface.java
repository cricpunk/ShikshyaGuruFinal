package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionManagementData;

/**
 * Project Name => Shikshya Guru
 * Created by   => Pankaj Koirala
 * Created on   => 1:38 PM, 15, Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public interface ViewPagerManagementInterface {

    void setUpManagementAdapter(FirebaseRecyclerOptions<InstitutionManagementData> options);

}
