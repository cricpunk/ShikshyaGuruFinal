package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseError;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerReviewInterface;

import java.util.List;

public interface InstitutionDataSourceInterface {

    FirebaseRecyclerOptions<InstitutionHomeNewsAndEventsData> getInstitutionHomeNewsAndEventData(String id);

    FirebaseRecyclerOptions<InstitutionHomeIntroData> getInstitutionHomeIntroData(String id);

    FirebaseRecyclerOptions<InstitutionReviewsData> getInstitutionReviewData(String id);

    void getInstitutionRatingsData(String id, ViewPagerReviewInterface reviewInterface);

    FirebaseRecyclerOptions<InstitutionTeachersData> getTeachersData(String id);

    FirebaseRecyclerOptions<InstitutionStaffData> getStaffData(String id);

    InstitutionProgrammesData getInstitutionProgrammesData();

    InstitutionProgrammesCoursesData getInstitutionCoursesData();

    FirebaseRecyclerOptions<InstitutionManagementData> getInstitutionManagementData(String id);

    List<InstitutionStudentAlumniData> getListOfStudentAlumniData();

    FirebaseRecyclerOptions<InstitutionGalleryData> getInstitutionGalleryData(String id);

    String getSlogan(String id);

    FirebaseRecyclerOptions<InstitutionsListItemParent> getInstitutionLists(int category);

    DatabaseError sendContactUsMessage(String id, InstitutionContactData contactData);

}
