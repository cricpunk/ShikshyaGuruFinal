package com.shikshyaguru.shikshyaguru._4_home_page_activity.views;

import android.app.ActivityOptions;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.HomePageOptionsListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.HomePageSliderListItem;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.ListOfTotalInstitutions;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.NewsListItem;

import java.util.List;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 *
 ***************************************************************************************************
 ** InstitutionsController cannot directly communicate with views. This view interface helps to make           *
 ** communication between controller and views.                                                    *
 ** Views implements methods specified in this interface and cover all logic part inside.          *
 ** InstitutionsController call method from this interface to make communication with views.                   *
 ***************************************************************************************************
 */

public interface ViewInterface {

    /**
     * Opening new activity for displaying _5_2_nhp_news_loader_fragment after clicking _5_2_nhp_news_loader_fragment headlines in recycler view
     * We are talking about _5_2_nhp_news_loader_fragment headline which is displayed in home page.
     */

    void setUpSliderAdapterAndView(FirebaseRecyclerOptions<HomePageSliderListItem> sliderOption);

    void openSliderItemDetails(String id, String image, String name, String place, String slogan, ActivityOptions options);

    void setUpOptionsAdapterAndView(List<HomePageOptionsListItem> listOfOptions);

    /**
     * Setting _5_2_nhp_news_loader_fragment recycler view adapter with data
     * This method will display _5_2_nhp_news_loader_fragment headline in main activity
     */
    void setupNewsHeadlinesAdapterAndView(FirebaseRecyclerOptions<NewsListItem> newsOption);

    void openNewsMainFragment();

    void openNewsLoaderFragment(NewsListItem newsListItem, ActivityOptions options);

    // Setting institutions collections recycler view adapter with data
    // Whole card view is displayed in a recycler view dynamically
    void setupInstitutionsCollectionAdapterAndView(List<ListOfTotalInstitutions> listOfTotalInstitutions);

    // Opening new activity for displaying institutions home page after clicking institutions in recycler view
    void openInstitutionsLoaderFragment1(String institutionsIcon, String institutionsName, String institutionsRating, String institutionsCityName);

    void openInstitutionsMainFragment();

}
