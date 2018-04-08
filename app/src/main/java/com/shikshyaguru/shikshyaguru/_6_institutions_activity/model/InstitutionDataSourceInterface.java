package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.model.InstitutionsListItemParent;

import java.util.List;

public interface InstitutionDataSourceInterface {

    FirebaseRecyclerOptions<InstitutionHomeNewsAndEventsData> getInstitutionHomeNewsAndEventData(String id);

    FirebaseRecyclerOptions<InstitutionHomeIntroData> getInstitutionHomeIntroData(String id);

    List<InstitutionReviewsData> getInstitutionReviewData();

    InstitutionRatingsData getInstitutionRatingsData();

    List<InstitutionTeachersData> getTeachersData();

    List<InstitutionStaffData> getStaffData();

    InstitutionProgrammesData getInstitutionProgrammesData();

    InstitutionProgrammesCoursesData getInstitutionCoursesData();

    FirebaseRecyclerOptions<InstitutionManagementData> getInstitutionManagementData(String id);

    List<InstitutionStudentAlumniData> getListOfStudentAlumniData();

    InstitutionGalleryData getInstitutionGalleryData();

    InstitutionActivitiesData getInstitutionActivitiesData();

    String getSlogan(String id);

    FirebaseRecyclerOptions<InstitutionsListItemParent> getInstitutionLists(int category);

}
