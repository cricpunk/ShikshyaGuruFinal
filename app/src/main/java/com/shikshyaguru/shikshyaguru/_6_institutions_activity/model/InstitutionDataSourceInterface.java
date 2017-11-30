package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */


import java.util.List;

public interface InstitutionDataSourceInterface {

    List<InstitutionHomeNewsAndEventsData> getInstitutionHomeNewsAndEventData();

    List<InstitutionHomeIntroData> getInstitutionHomeIntroData();

    List<InstitutionReviewsData> getInstitutionReviewData();

    InstitutionRatingsData getInstitutionRatingsData();

    List<InstitutionTeachersData> getTeachersData();

    List<InstitutionStaffData> getStaffData();

    InstitutionProgrammesData getInstitutionProgrammesData();

    InstitutionProgrammesCoursesData getInstitutionCoursesData();

    List<InstitutionManagementData> getInstitutionManagementData();

    List<InstitutionStudentAlumniData> getListOfStudentAlumniData();

    InstitutionGalleryData getInstitutionGalleryData();

    InstitutionActivitiesData getInstitutionActivitiesData();

}
