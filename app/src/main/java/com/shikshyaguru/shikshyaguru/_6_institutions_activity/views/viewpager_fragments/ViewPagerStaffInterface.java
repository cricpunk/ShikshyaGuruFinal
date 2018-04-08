package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/25/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionStaffData;

public interface ViewPagerStaffInterface {

    void setUpStaffList(FirebaseRecyclerOptions<InstitutionStaffData> options);

}
