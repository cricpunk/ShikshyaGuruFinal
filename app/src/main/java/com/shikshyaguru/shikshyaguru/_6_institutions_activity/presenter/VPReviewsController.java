package com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter;

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSourceInterface;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments.ViewPagerReviewInterface;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:37 PM 25 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class VPReviewsController {

    private ViewPagerReviewInterface reviewInterface;
    private InstitutionDataSourceInterface dataSource;

    public VPReviewsController(ViewPagerReviewInterface reviewInterface, InstitutionDataSourceInterface dataSource) {
        this.reviewInterface = reviewInterface;
        this.dataSource = dataSource;

        setUpRatings();
        setUpReviews();
    }

    private void setUpRatings() {
        reviewInterface.setUpRatings(dataSource.getInstitutionRatingsData());
    }

    private void setUpReviews() {
        reviewInterface.setUpReviews(dataSource.getInstitutionReviewData());
    }

}