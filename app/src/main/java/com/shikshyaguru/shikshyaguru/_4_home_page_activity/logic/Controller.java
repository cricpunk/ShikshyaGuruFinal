package com.shikshyaguru.shikshyaguru._4_home_page_activity.logic;

import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.DataSourceInterface;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.DrawerInterface;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.ViewInterface;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class Controller {
    private ViewInterface view;
    private DataSourceInterface dataSource;
    private DrawerInterface drawerInterface;

    public Controller(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        setUpSliderWithData();
        setUpOptionsWithData();
        setUpNewsHeadlinesWithData();
        setUpInstitutionsCollectionWithData();

    }



    public Controller(DrawerInterface drawerInterface, DataSourceInterface dataSource) {
        this.dataSource = dataSource;
        this.drawerInterface = drawerInterface;
        setUpDrawerMainHeaderWithData();
    }

    private void setUpDrawerMainHeaderWithData() {
        drawerInterface.setUpDrawerMainHeader(dataSource.getListOfDrawerMainHeader());
    }

    private void setUpSliderWithData() {
        view.setUpSliderAdapterAndView(dataSource.getListOfSliderCandidates());
    }

    private void setUpOptionsWithData() {
        view.setUpOptionsAdapterAndView(dataSource.getListOfOptions());
    }

    private void setUpNewsHeadlinesWithData() {
        view.setupNewsHeadlinesAdapterAndView(dataSource.getListOfNewsData());
    }

    private void setUpInstitutionsCollectionWithData() {
        view.setupInstitutionsCollectionAdapterAndView(dataSource.getTotalInstitutionsHeading());
    }

    public void onNewsListItemClick(NewsListItem newsListItem) {
        view.openSingleNewsActivity(newsListItem.getNews());
    }

    public void onInstitutionsItemClick(InstitutionsListItemParent il) {
        view.openInstitutionsHomeActivity(il.getInstitutionsIcon(), il.getInstitutionsName(), il.getInstitutionsRating(), il.getInstitutionCityName());
    }

    public void onAllNewsClick() {
        view.openNewsHomePage();
    }

}
