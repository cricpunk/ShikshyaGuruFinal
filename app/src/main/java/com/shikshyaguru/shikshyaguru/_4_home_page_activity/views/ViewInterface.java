package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;

import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.HomePageOptionsListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.HomePageSliderListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.ListOfInstitutionsHeading;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;

import java.util.List;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 *
 ***************************************************************************************************
 ** Controller cannot directly communicate with views. This view interface helps to make           *
 ** communication between controller and views.                                                    *
 ** Views implements methods specified in this interface and cover all logic part inside.          *
 ** Controller call method from this interface to make communication with views.                   *
 ***************************************************************************************************
 */

public interface ViewInterface {

    /**
     * Opening new activity for displaying _5_2_nhp_news_loader_fragment after clicking _5_2_nhp_news_loader_fragment headlines in recycler view
     * We are talking about _5_2_nhp_news_loader_fragment headline which is displayed in home page.
     */
    void openSingleNewsActivity(String news);

    void openNewsHomePage();

    /**
     * Setting _5_2_nhp_news_loader_fragment recycler view adapter with data
     * This method will display _5_2_nhp_news_loader_fragment headline in main activity
     */
    void setupNewsHeadlinesAdapterAndView(List<NewsListItem> listOfNewsData);

    // Opening new activity for displaying institutions home page after clicking institutions in recycler view
    void openInstitutionsHomeActivity(int institutionsIcon, String institutionsName, String institutionsRating, String institutionsCityName);

    // Setting institutions collections recycler view adapter with data
    // Whole card view is displayed in a recycler view dynamically
    void setupInstitutionsCollectionAdapterAndView(List<ListOfInstitutionsHeading> listOfInstitutionsHeadings);

    void setUpSliderAdapterAndView(List<HomePageSliderListItem> listOfSliderCandidates);

    void setUpOptionsAdapterAndView(List<HomePageOptionsListItem> listOfOptions);

}
