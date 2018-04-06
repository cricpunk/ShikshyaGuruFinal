package com.shikshyaguru.shikshyaguru._4_home_page_activity.presenter;

import android.app.ActivityOptions;

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

public class HomePageController {
    private ViewInterface view;
    private DataSourceInterface dataSource;
    private DrawerInterface drawerInterface;

    public HomePageController(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        setUpSliderWithData();
        setUpOptionsWithData();
        setUpNewsHeadlinesWithData();
        setUpInstitutionsCollectionWithData();

    }

    public HomePageController(DrawerInterface drawerInterface, DataSourceInterface dataSource) {
        this.dataSource = dataSource;
        this.drawerInterface = drawerInterface;
        setUpDrawerMainHeaderWithData();
    }

    private void setUpDrawerMainHeaderWithData() {
        drawerInterface.setUpDrawerMainHeader(dataSource.getListOfDrawerMainHeader());
    }

    public void onUserProfileClick() {
        drawerInterface.onUserProfileClickListener();
    }

    private void setUpSliderWithData() {
        view.setUpSliderAdapterAndView(dataSource.getSponsorDetail());
    }

    public void onInstitutionItemClick(String id, String image, String name, String place, String slogan, ActivityOptions options) {
        view.openSliderItemDetails(id, image, name, place, slogan, options);
    }

    public String getSlogan(String id) {
        return dataSource.getSlogan(id);
    }

    private void setUpOptionsWithData() {
        view.setUpOptionsAdapterAndView(dataSource.getListOfOptions());
    }

    private void setUpNewsHeadlinesWithData() {
        view.setupNewsHeadlinesAdapterAndView(dataSource.getNewsDetails());
    }

    public void onAllNewsClick() {
        view.openNewsMainFragment();
    }

    public void onNewsListItemClick(NewsListItem newsListItem, ActivityOptions options) {
        view.openNewsLoaderFragment(newsListItem, options);
    }

    private void setUpInstitutionsCollectionWithData() {
        view.setupInstitutionsCollectionAdapterAndView(dataSource.getTotalInstitutionsHeading());
    }

    public void onAllInstitutionsClick() {
        view.openInstitutionsMainFragment();
    }

    public void onInstitutionsItemClick(InstitutionsListItemParent il) {
        view.openInstitutionsLoaderFragment1(il.getIcon_image(), il.getName(), il.getRating(), il.getCity());
    }



}
