package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStaffInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPStaffController {

    private ViewPagerStaffInterface staffInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPStaffController(ViewPagerStaffInterface staffInterface, InstitutionDataSourceInterface dataSource) {
        this.staffInterface = staffInterface;
        this.dataSource = dataSource;

        setupStaffList();
    }

    private void setupStaffList() {
        staffInterface.setUpStaffList(dataSource.getStaffData());
    }

}
