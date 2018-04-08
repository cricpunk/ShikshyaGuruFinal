package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/11/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.app.ActivityOptions;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionHomeIntroData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionHomeNewsAndEventsData;

public interface ViewPagerHomeInterface {

    void setUpNewsAdapterAndView(FirebaseRecyclerOptions<InstitutionHomeNewsAndEventsData> newsOption);

    void setUpHomeIntroAdapterAndView(FirebaseRecyclerOptions<InstitutionHomeIntroData> introOption);

    void openNews(InstitutionHomeNewsAndEventsData newsList, ActivityOptions options);

}
