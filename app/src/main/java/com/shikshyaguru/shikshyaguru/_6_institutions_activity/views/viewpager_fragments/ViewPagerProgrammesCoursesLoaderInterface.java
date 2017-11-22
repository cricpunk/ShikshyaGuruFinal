package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 11/5/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.widget.Button;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionProgrammesCoursesData;

public interface ViewPagerProgrammesCoursesLoaderInterface {

    void setUpOptionsAdapter(InstitutionProgrammesCoursesData coursesData);

    void onMoreIconClickListener(Button button);

    void onYearBtnClickListener(String year);

}
