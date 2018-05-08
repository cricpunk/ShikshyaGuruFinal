package com.shikshyaguru.shikshyaguru._4_home_page_activity.presenter;

import android.app.ActivityOptions;

import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.DataSourceHomePageInterface;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.HomePageInterface;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerInterface;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class HomePageController {

    private HomePageInterface view;
    private DataSourceHomePageInterface dataSource;
    private NavigationDrawerInterface navigationDrawerInterface;

    public HomePageController(HomePageInterface view, DataSourceHomePageInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

//        setUpSliderWithData();
//        setUpOptionsWithData();
//        setUpNewsHeadlinesWithData();
//        setUpInstitutionsCollectionWithData();

    }

    public HomePageController(NavigationDrawerInterface navigationDrawerInterface, DataSourceHomePageInterface dataSource) {
        this.dataSource = dataSource;
        this.navigationDrawerInterface = navigationDrawerInterface;
        dataSource.setUpDrawerWithData(navigationDrawerInterface);
    }

    public void setUpSliderWithData() {
        view.setUpSliderAdapterAndView(dataSource.getSponsorDetail(view));
    }

    public void onInstitutionItemClick(String id, String image, String name, String place, String slogan, ActivityOptions options) {
        view.openSliderItemDetails(id, image, name, place, slogan, options);
    }

    public String getSlogan(String id) {
        return dataSource.getSlogan(id);
    }

    public void setUpOptionsWithData() {
        view.setUpOptionsAdapterAndView(dataSource.getListOfOptions());
    }

    public void setUpNewsHeadlinesWithData() {
        view.setupNewsHeadlinesAdapterAndView(dataSource.getNewsDetails());
    }

    public void onAllNewsClick() {
        view.openNewsMainFragment();
    }

    public void onNewsListItemClick(NewsListItem newsListItem, ActivityOptions options) {
        view.openNewsLoaderFragment(newsListItem, options);
    }

    public void setUpInstitutionsCollectionWithData() {
        view.setupInstitutionsCollectionAdapterAndView(dataSource.getTotalInstitutionsHeading());
    }

    public void onAllInstitutionsClick(int instCategory, String title) {
        view.openInstitutionsMainFragment(instCategory, title);
    }

    public void getUserDetails() {
        dataSource.getUserDetails(navigationDrawerInterface);
    }

}
