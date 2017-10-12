package com.shikshyaguru.shikshyaguru._6_institutions_activity.views;
/*
 * Created by Pankaj Koirala on 10/11/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.CollegeHomeIntroData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.CollegesHomeNewsAndEventsData;

import java.util.List;

public interface InstitutionViewInterface {

    void setUpNewsAdapterAndView(List<CollegesHomeNewsAndEventsData> newsAndEventsData);

    void setUpHomeIntroAdapterAndView(List<CollegeHomeIntroData> introData);
}
