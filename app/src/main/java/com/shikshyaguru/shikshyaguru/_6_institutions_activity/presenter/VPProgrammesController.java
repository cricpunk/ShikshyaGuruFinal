package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import android.widget.Button;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPProgrammesController {

    private ViewPagerProgrammesInterface programmesInterface;
    private ViewPagerProgrammesCoursesLoaderInterface coursesLoaderInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPProgrammesController(ViewPagerProgrammesInterface programmesInterface, InstitutionDataSourceInterface dataSource) {
        this.programmesInterface = programmesInterface;
        this.dataSource = dataSource;

        setUpProgrammesLevel();
    }

    public VPProgrammesController(ViewPagerProgrammesCoursesLoaderInterface coursesLoaderInterface, InstitutionDataSourceInterface dataSource) {
        this.coursesLoaderInterface = coursesLoaderInterface;
        this.dataSource = dataSource;
    }

    private void setUpProgrammesLevel() {
        programmesInterface.setUpProgrammesLevel(dataSource.getInstitutionProgrammesData());
    }

    public void onCoursesClickListener(String courseName) {
        programmesInterface.onCoursesClickListener(courseName);
    }

    public void setUpCoursesAdapter() {
        coursesLoaderInterface.setUpOptionsAdapter(dataSource.getInstitutionCoursesData());
    }

    public void onYearBtnClickListener(String year) {
        coursesLoaderInterface.onYearBtnClickListener(year);
    }

    public void onMoreIconClickListener(Button button) {
        coursesLoaderInterface.onMoreIconClickListener(button);
    }

}
