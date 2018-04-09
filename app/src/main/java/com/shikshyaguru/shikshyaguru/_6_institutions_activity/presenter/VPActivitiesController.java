package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerActivitiesInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPActivitiesController {

    private ViewPagerActivitiesInterface activitiesInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPActivitiesController(ViewPagerActivitiesInterface activitiesInterface, InstitutionDataSourceInterface dataSource) {
        this.activitiesInterface = activitiesInterface;
        this.dataSource = dataSource;

        setUpActivitiesCategoryAdapter();
    }

    private void setUpActivitiesCategoryAdapter() {
        //activitiesInterface.setUpActivitiesCategory(dataSource.getInstitutionActivitiesData());
    }

}
