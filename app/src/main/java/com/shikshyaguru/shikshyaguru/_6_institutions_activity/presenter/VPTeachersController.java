package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerTeachersInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPTeachersController {

    private ViewPagerTeachersInterface teachersInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPTeachersController(ViewPagerTeachersInterface teachersInterface, InstitutionDataSourceInterface dataSource) {
        this.teachersInterface = teachersInterface;
        this.dataSource = dataSource;

        setUpTeachersList();
    }

    private void setUpTeachersList() {
        teachersInterface.setUpTeachersList(dataSource.getTeachersData());
    }
}
