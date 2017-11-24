package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

    /*
     * CONTENTS OF THE CLASS INSTITUTIONS CONTROLLER
     * ==============================================
     * (01) Constructor for View pager home page interface.
     * (02) Constructor for View pager programmes page interface.
     * (02-01) Constructor for View pager programmes courses loader interface.
     * (03) Constructor for View pager students page interface.
     * (04) Constructor for View pager management page interface.
     * (05) Constructor for View pager gallery page interface.
     * (05-01) Constructor for View pager gallery loader interface.
     * (06) Constructor for View pager teachers page interface.
     * (07) Constructor for View pager staff page interface.
     * (08) Constructor for View pager activities page interface.
     * (09) Constructor for View pager about page interface.
     * (10) Constructor for View pager contact page interface.
     * (11) Constructor for View pager contact page interface.
     *
     *
     */

import android.widget.Button;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerAboutInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerActivitiesInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerContactInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerGalleryLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerHomeInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerManagementInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerReviewInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStaffInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStudentsInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerTeachersInterface;

public class InstitutionsController {

    private ViewPagerHomeInterface homeInterface;
    private ViewPagerProgrammesInterface programmesInterface;
    private ViewPagerProgrammesCoursesLoaderInterface coursesLoaderInterface;
    private ViewPagerStudentsInterface studentsInterface;
    private ViewPagerManagementInterface managementInterface;
    private ViewPagerGalleryInterface galleryInterface;
    private ViewPagerGalleryLoaderInterface galleryLoaderInterface;
    private ViewPagerTeachersInterface teachersInterface;
    private ViewPagerStaffInterface staffInterface;
    private ViewPagerActivitiesInterface activitiesInterface;
    private ViewPagerAboutInterface aboutInterface;
    private ViewPagerContactInterface contactInterface;
    private ViewPagerReviewInterface reviewInterface;
    private InstitutionDataSourceInterface dataSource;

    // (01) Constructor for View pager home page interface.
    public InstitutionsController(ViewPagerHomeInterface homeInterface, InstitutionDataSourceInterface dataSource) {
        this.homeInterface = homeInterface;
        this.dataSource = dataSource;

        setUpNewsAndEvents();
        setUpHomeIntro();
    }

    // (02) Constructor for View pager programmes page interface.
    public InstitutionsController(ViewPagerProgrammesInterface programmesInterface, InstitutionDataSourceInterface dataSource) {
        this.programmesInterface = programmesInterface;
        this.dataSource = dataSource;

        setUpProgrammesLevel();
    }

    // (02-01) Constructor for View pager programmes course loader page interface.
    public InstitutionsController(ViewPagerProgrammesCoursesLoaderInterface coursesLoaderInterface, InstitutionDataSourceInterface dataSource) {
        this.coursesLoaderInterface = coursesLoaderInterface;
        this.dataSource = dataSource;
    }

    // (03) Constructor for View pager students page interface.
    public InstitutionsController(ViewPagerStudentsInterface studentsInterface, InstitutionDataSourceInterface dataSource) {
        this.studentsInterface = studentsInterface;
        this.dataSource = dataSource;

        setUpStudentAlumni();
    }

    // (04) Constructor for View pager management page interface.
    public InstitutionsController(ViewPagerManagementInterface managementInterface, InstitutionDataSourceInterface dataSource) {
        this.managementInterface = managementInterface;
        this.dataSource = dataSource;

        setUpManagementList();
    }

    // (05) Constructor for View pager gallery page interface.
    public InstitutionsController(ViewPagerGalleryInterface galleryInterface, InstitutionDataSourceInterface dataSource) {
        this.galleryInterface = galleryInterface;
        this.dataSource = dataSource;

        setUpGalleryCategory();
    }

    // (05-01) Constructor for View pager gallery page interface.
    public InstitutionsController(ViewPagerGalleryLoaderInterface galleryLoaderInterface, InstitutionDataSourceInterface dataSource) {
        this.galleryLoaderInterface = galleryLoaderInterface;
        this.dataSource = dataSource;

        setUpGallery();
    }

    // (06) Constructor for View pager teachers page interface.
    public InstitutionsController(ViewPagerTeachersInterface teachersInterface, InstitutionDataSourceInterface dataSource) {
        this.teachersInterface = teachersInterface;
        this.dataSource = dataSource;

        setUpTeachersList();
    }

    // (07) Constructor for View pager staff page interface.
    public InstitutionsController(ViewPagerStaffInterface staffInterface, InstitutionDataSourceInterface dataSource) {
        this.staffInterface = staffInterface;
        this.dataSource = dataSource;

        setupStaffList();
    }

    // (08) Constructor for View pager activities page interface.
    public InstitutionsController(ViewPagerActivitiesInterface activitiesInterface, InstitutionDataSourceInterface dataSource) {
        this.activitiesInterface = activitiesInterface;
        this.dataSource = dataSource;
    }

    // (09) Constructor for View pager about page interface.
    public InstitutionsController(ViewPagerAboutInterface aboutInterface, InstitutionDataSourceInterface dataSource) {
        this.aboutInterface = aboutInterface;
        this.dataSource = dataSource;
    }

    // (10) Constructor for View pager contact page interface.
    public InstitutionsController(ViewPagerContactInterface contactInterface, InstitutionDataSourceInterface dataSource) {
        this.contactInterface = contactInterface;
        this.dataSource = dataSource;
    }

    // (11) Constructor for View pager reviews page interface.
    public InstitutionsController(ViewPagerReviewInterface reviewInterface, InstitutionDataSourceInterface dataSource) {
        this.reviewInterface = reviewInterface;
        this.dataSource = dataSource;

        setUpRatings();
        setUpReviews();
    }

    private void setUpNewsAndEvents() {
        homeInterface.setUpNewsAdapterAndView(dataSource.getInstitutionHomeNewsAndEventData());
    }

    private void setUpHomeIntro() {
        homeInterface.setUpHomeIntroAdapterAndView(dataSource.getInstitutionHomeIntroData());
    }

    private void setUpProgrammesLevel() {
        programmesInterface.setUpProgrammesLevel(dataSource.getInstitutionProgrammesData());
    }

    public void onCoursesClickListener(String courseName) {
        programmesInterface.onCoursesClickListener(courseName);
    }

    public void setUpCoursesAdapter() {
        coursesLoaderInterface.setUpOptionsAdapter(dataSource.getInstitutionCoursesData());
    }

    public void onYearBtnClickListener(String year) {
        coursesLoaderInterface.onYearBtnClickListener(year);
    }

    public void onMoreIconClickListener(Button button) {
        coursesLoaderInterface.onMoreIconClickListener(button);
    }

    private void setUpStudentAlumni() {
        studentsInterface.setUpStudentAlumni(dataSource.getListOfStudentAlumniData());
    }

    private void setUpManagementList() {
        managementInterface.setUpManagementAdapter(dataSource.getInstitutionManagementData());
    }

    private void setUpGalleryCategory() {
        galleryInterface.setUpGalleryCategory(dataSource.getInstitutionGalleryData());
    }

    private void setUpGallery() {
        galleryLoaderInterface.setUpGallery(dataSource.getInstitutionGalleryData());
    }

    public void onGalleryCategoryClick(String category) {
        galleryInterface.onGalleryCategoryClick(category);
    }

    private void setUpTeachersList() {
        teachersInterface.setUpTeachersList(dataSource.getTeachersData());
    }

    private void setupStaffList() {
        staffInterface.setUpStaffList(dataSource.getStaffData());
    }

    private void setUpRatings() {
        reviewInterface.setUpRatings(dataSource.getInstitutionRatingsData());
    }

    private void setUpReviews() {
        reviewInterface.setUpReviews(dataSource.getInstitutionReviewData());
    }

}

