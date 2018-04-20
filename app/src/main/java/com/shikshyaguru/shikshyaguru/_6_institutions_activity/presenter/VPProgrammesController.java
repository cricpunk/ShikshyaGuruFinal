package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import android.widget.Button;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesFragmentInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoaderFragmentInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesLevelInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPProgrammesController {

    private ViewPagerProgrammesLevelInterface programmesInterface;
    private ViewPagerProgrammesCoursesLoaderInterface coursesLoaderInterface;
    private ViewPagerProgrammesCoursesFragmentInterface coursesFragmentInterface;
    private ViewPagerProgrammesCoursesLoaderFragmentInterface coursesLoaderFragmentInterface;

    private InstitutionDataSourceInterface dataSource;

    public VPProgrammesController(ViewPagerProgrammesLevelInterface programmesInterface, InstitutionDataSourceInterface dataSource) {
        this.programmesInterface = programmesInterface;
        this.dataSource = dataSource;

        setUpProgrammesLevel();
    }

    public VPProgrammesController(ViewPagerProgrammesCoursesLoaderInterface coursesLoaderInterface, InstitutionDataSourceInterface dataSource) {
        this.coursesLoaderInterface = coursesLoaderInterface;
        this.dataSource = dataSource;
    }

    public VPProgrammesController(ViewPagerProgrammesCoursesFragmentInterface coursesFragmentInterface, InstitutionDataSourceInterface dataSource) {
        this.coursesFragmentInterface = coursesFragmentInterface;
        this.dataSource = dataSource;
    }

    public VPProgrammesController(ViewPagerProgrammesCoursesLoaderFragmentInterface coursesLoaderFragmentInterface, InstitutionDataSourceInterface dataSource) {
        this.coursesLoaderFragmentInterface = coursesLoaderFragmentInterface;
        this.dataSource = dataSource;
    }

    private void setUpProgrammesLevel() {
        dataSource.getInstitutionProgrammesData(programmesInterface);
    }

    public void onCoursesClickListener(String levelName, String courseName) {
        programmesInterface.onCoursesClickListener(levelName, courseName);
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

    public void setUpProgrammesCourses(String id, String level, String faculty) {
        dataSource.getProgrammeCourses(coursesFragmentInterface, id, level, faculty);
    }

    public void setUpCoursesLoader(String level, String faculty, String programme) {
        dataSource.getCourseLoaderData(coursesLoaderFragmentInterface, level, faculty, programme);
    }

}
