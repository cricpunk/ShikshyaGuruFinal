package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 *
 *
 * This is similar to a contract between classes that dictate how they can talk to each other.
 * without giving implementation details
 */

public interface DataSourceInterface {

    // Get details of candidates for home page slider
    FirebaseRecyclerOptions<HomePageSliderListItem> getSponsorDetail();

    // Get details of candidates for home page slider
    FirebaseRecyclerOptions<NewsListItem> getNewsDetails();

    // Get list of Colleges details
    List<CollegeListItem> getListOfCollegesData();

    List<ListOfTotalInstitutions> getTotalInstitutionsHeading();

    // Get list of Consultancies details
    List<ConsultanciesListItem> getListOfConsultanciesData();

    // Get list of Institutes details
    List<InstitutesListItem> getListOfInstitutesData();

    // Get list of Abroad Study details
    List<AbroadStudyListItem> getListOfAbroadStudyData();

    // Get list of Schools details
    List<SchoolsListItem> getListOfSchoolsData();

    // Get list of Universities details
    List<UniversitiesListItem> getListOfUniversitiesData();

    //Get list of Drawer main header
    List<DrawerListItem> getListOfDrawerMainHeader();

    //Get list of Home Page Options name
    List<HomePageOptionsListItem> getListOfOptions();

    UserData getUserData();
}
