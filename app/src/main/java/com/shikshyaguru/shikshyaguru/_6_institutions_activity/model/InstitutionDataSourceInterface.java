package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesLevelInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerReviewInterface;

import java.util.ArrayList;
import java.util.List;

public interface InstitutionDataSourceInterface {

    FirebaseRecyclerOptions<InstitutionHomeNewsAndEventsData> getInstitutionHomeNewsAndEventData(String id);

    FirebaseRecyclerOptions<InstitutionHomeIntroData> getInstitutionHomeIntroData(String id);

    FirebaseRecyclerOptions<InstitutionsListItemParent> getInstitutionLists(int category, ArrayList<String> favouriteInstitutions);

    void getInstitutionProgrammesData(ViewPagerProgrammesLevelInterface programmesInterface);

    InstitutionProgrammesCoursesData getInstitutionCoursesData();

    FirebaseRecyclerOptions<InstitutionManagementData> getInstitutionManagementData(String id);

    List<InstitutionStudentAlumniData> getListOfStudentAlumniData();

    FirebaseRecyclerOptions<InstitutionGalleryData> getInstitutionGalleryData(String id);

    FirebaseRecyclerOptions<InstitutionTeachersData> getTeachersData(String id);

    FirebaseRecyclerOptions<InstitutionStaffData> getStaffData(String id);

    DatabaseError sendContactUsMessage(String id, InstitutionContactData contactData);

    FirebaseRecyclerOptions<InstitutionReviewsData> getInstitutionReviewData(String id);

    void getInstitutionRatingsData(String id, ViewPagerReviewInterface reviewInterface);

    void postUserReview(ViewPagerReviewInterface reviewInterface, String id, String uId, int instRating, int eduRating, int infraRating, int techRating, int mgmtRating, String comment);

    String getSlogan(String id);

    void validateAndProceedReportBtn(InstitutionLoaderInterface loaderInterface, String id);

    void validateAndProceedFavBtn(InstitutionLoaderInterface loaderInterface, String id);

    void getProgrammeCourses(ViewPagerProgrammesCoursesInterface coursesFragmentInterface, String id, String level, String faculty);

    void getCourseLoaderData(ViewPagerProgrammesCoursesLoaderInterface coursesLoaderFragmentInterface, String level, String faculty, String programme);

}


