package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import android.app.ActivityOptions;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionHomeNewsAndEventsData;
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
    }

    public void setUpNewsAndEvents(String id) {
        homeInterface.setUpNewsAdapterAndView(dataSource.getInstitutionHomeNewsAndEventData(id));
    }

    public void setUpHomeIntro(String id) {
        homeInterface.setUpHomeIntroAdapterAndView(dataSource.getInstitutionHomeIntroData(id));
    }

    public void onNewsListItemClick(InstitutionHomeNewsAndEventsData newsList, ActivityOptions options) {
        homeInterface.openNews(newsList, options);
    }

}
