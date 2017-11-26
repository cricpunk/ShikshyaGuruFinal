package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerHomeInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPHomeController {

    private ViewPagerHomeInterface homeInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPHomeController(ViewPagerHomeInterface homeInterface, InstitutionDataSourceInterface dataSource) {
        this.homeInterface = homeInterface;
        this.dataSource = dataSource;

        setUpNewsAndEvents();
        setUpHomeIntro();

    }

    private void setUpNewsAndEvents() {
        homeInterface.setUpNewsAdapterAndView(dataSource.getInstitutionHomeNewsAndEventData());
    }

    private void setUpHomeIntro() {
        homeInterface.setUpHomeIntroAdapterAndView(dataSource.getInstitutionHomeIntroData());
    }

}
