package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/23/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionTeachersData;

public interface ViewPagerTeachersInterface {

    void setUpTeachersList(FirebaseRecyclerOptions<InstitutionTeachersData> options);

}
