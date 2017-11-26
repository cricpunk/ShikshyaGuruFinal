package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerManagementInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPManagementController {

    private ViewPagerManagementInterface managementInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPManagementController(ViewPagerManagementInterface managementInterface, InstitutionDataSourceInterface dataSource) {
        this.managementInterface = managementInterface;
        this.dataSource = dataSource;

        setUpManagementList();
    }

    private void setUpManagementList() {
        managementInterface.setUpManagementAdapter(dataSource.getInstitutionManagementData());
    }

}
