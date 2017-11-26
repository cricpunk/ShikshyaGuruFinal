package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStudentsInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPStudentsController {

    private ViewPagerStudentsInterface studentsInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPStudentsController(ViewPagerStudentsInterface studentsInterface, InstitutionDataSourceInterface dataSource) {
        this.studentsInterface = studentsInterface;
        this.dataSource = dataSource;

        setUpStudentAlumni();
    }

    private void setUpStudentAlumni() {
        studentsInterface.setUpStudentAlumni(dataSource.getListOfStudentAlumniData());
    }

}
