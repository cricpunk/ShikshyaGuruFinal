package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.widget.Button;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerHomeInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesCoursesLoaderInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerProgrammesInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerReviewInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerStaffInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerTeachersInterface;

public class InstitutionsController {

    private ViewPagerHomeInterface homeInterface;
    private ViewPagerProgrammesInterface programmesInterface;
    private ViewPagerProgrammesCoursesLoaderInterface coursesLoaderInterface;
    private ViewPagerTeachersInterface teachersInterface;
    private ViewPagerStaffInterface staffInterface;
    private ViewPagerReviewInterface reviewInterface;
    private InstitutionDataSourceInterface dataSource;

    public InstitutionsController(ViewPagerHomeInterface homeInterface, InstitutionDataSourceInterface dataSource) {
        this.homeInterface = homeInterface;
        this.dataSource = dataSource;

        setUpNewsAndEvents();
        setUpHomeIntro();
    }

    public InstitutionsController(ViewPagerProgrammesInterface programmesInterface, InstitutionDataSourceInterface dataSource) {
        this.programmesInterface = programmesInterface;
        this.dataSource = dataSource;

        setUpProgrammesLevel();
    }

    public InstitutionsController(ViewPagerProgrammesCoursesLoaderInterface coursesLoaderInterface, InstitutionDataSourceInterface dataSource) {
        this.coursesLoaderInterface = coursesLoaderInterface;
        this.dataSource = dataSource;

        setUpCoursesAdapter();
    }

    public InstitutionsController(ViewPagerReviewInterface reviewInterface, InstitutionDataSourceInterface dataSource) {
        this.reviewInterface = reviewInterface;
        this.dataSource = dataSource;

        setUpRatings();
        setUpReviews();
    }

    public InstitutionsController(ViewPagerTeachersInterface teachersInterface, InstitutionDataSourceInterface dataSource) {
        this.teachersInterface = teachersInterface;
        this.dataSource = dataSource;

        setUpTeachersList();
    }

    public InstitutionsController(ViewPagerStaffInterface staffInterface, InstitutionDataSourceInterface dataSource) {
        this.staffInterface = staffInterface;
        this.dataSource = dataSource;

        setupStaffList();
    }

    private void setUpNewsAndEvents(){
        homeInterface.setUpNewsAdapterAndView(dataSource.getInstitutionHomeNewsAndEventData());
    }

    private void setUpHomeIntro() {
        homeInterface.setUpHomeIntroAdapterAndView(dataSource.getInstitutionHomeIntroData());
    }

    private void setUpProgrammesLevel() {
        programmesInterface.setUpProgrammesLevel(dataSource.getInstitutionProgrammesData());
    }

    public void onCoursesClickListener() {
        programmesInterface.onCoursesClickListener();
    }

    private void setUpCoursesAdapter() {
        coursesLoaderInterface.setUpOptionsAdapter(dataSource.getInstitutionCoursesData());
    }

    public void onYearBtnClickListener(String year) {
        coursesLoaderInterface.onYearBtnClickListener(year);
    }

    public void onMoreIconClickListener(Button button) {
        coursesLoaderInterface.onMoreIconClickListener(button);
    }

    private void setUpRatings() {
        reviewInterface.setUpRatings(dataSource.getInstitutionRatingsData());
    }

    private void setUpReviews() {
        reviewInterface.setUpReviews(dataSource.getInstitutionReviewData());
    }

    private void setUpTeachersList() {
        teachersInterface.setUpTeachersList(dataSource.getTeachersData());
    }

    private void setupStaffList() {
        staffInterface.setUpStaffList(dataSource.getStaffData());
    }

}

