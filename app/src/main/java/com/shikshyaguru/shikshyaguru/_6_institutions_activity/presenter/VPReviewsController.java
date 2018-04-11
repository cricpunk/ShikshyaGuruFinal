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

    }

    public void getRatings(String id) {
        dataSource.getInstitutionRatingsData(id, reviewInterface);
    }

    public void setUpReviews(String id) {
        reviewInterface.setUpReviews(dataSource.getInstitutionReviewData(id));
    }

    public void submitReviews(String id, String uId, int instRating, int eduRating, int infraRating, int techRating, int mgmtRating, String comment) {
        dataSource.postUserReview(reviewInterface, id, uId, instRating, eduRating, infraRating, techRating, mgmtRating, comment);
    }

}
