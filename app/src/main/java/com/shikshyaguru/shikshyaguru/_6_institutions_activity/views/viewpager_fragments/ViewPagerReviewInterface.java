package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/17/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionRatingsData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionReviewsData;

import java.util.List;

public interface ViewPagerReviewInterface {

    void setUpReviews(List<InstitutionReviewsData> reviewsData);

    void setUpRatings(InstitutionRatingsData ratingsData);

}
