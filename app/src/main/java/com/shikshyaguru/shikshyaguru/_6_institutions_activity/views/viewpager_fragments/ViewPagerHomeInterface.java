package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/11/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionHomeIntroData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionHomeNewsAndEventsData;

import java.util.List;

public interface ViewPagerHomeInterface {

    void setUpNewsAdapterAndView(List<InstitutionHomeNewsAndEventsData> newsAndEventsData);

    void setUpHomeIntroAdapterAndView(List<InstitutionHomeIntroData> introData);
}
