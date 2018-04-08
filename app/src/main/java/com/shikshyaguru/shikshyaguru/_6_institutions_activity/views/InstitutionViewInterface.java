package com.shikshyaguru.shikshyaguru._6_institutions_activity.views;

import android.app.ActivityOptions;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;

/*
 * Created by Pankaj Koirala on 4/7/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public interface InstitutionViewInterface {

    void setUpInstitutionAdapter(FirebaseRecyclerOptions<InstitutionsListItemParent> options);

    void openInstitutionDetails(InstitutionsListItemParent instDetails, ActivityOptions options);

}
