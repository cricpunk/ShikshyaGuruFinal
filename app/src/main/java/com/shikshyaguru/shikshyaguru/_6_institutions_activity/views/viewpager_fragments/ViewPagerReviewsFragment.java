package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.animation.ObjectAnimator;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionFakeDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionRatingsData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionReviewsData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.InstitutionsController;

import java.util.List;

public class ViewPagerReviewsFragment extends Fragment implements ViewPagerReviewInterface {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetectorCompat gestureDetector;

    private RelativeLayout swipeLayout, overallRating, educationRating, infrastructureRating,
            teachersRating, managementRating, reviews;

    private TextView submitNextFinished;
    private ImageView sliderIndicator;

    private ViewPager viewPager;
    private NestedScrollView nestedScrollView;

    private View rootView;
    private LayoutInflater inflater;
    private List<InstitutionReviewsData> reviewData;
    private RecyclerView reviewRecyclerView;
    private InstitutionsController controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout._6_2_11_0_view_pager_reviews, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.rootView = view;

        nestedScrollView = (NestedScrollView) getActivity().findViewById(R.id.root_reviews_nested_scroll);
        reviewRecyclerView = (RecyclerView) view.findViewById(R.id.rec_reviews);

        controller = new InstitutionsController(this, new InstitutionFakeDataSource());
        initRatingAndReviewSection();
    }

    @Override
    public void setUpRatings(InstitutionRatingsData ratingsData) {

        TextView overallRating = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_overall_rating);
        TextView totalRating = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_rating);
        TextView totalReview = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_review);

        TextView fiveStar = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_five_star);
        TextView fourStar = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_four_star);
        TextView threeStar = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_three_star);
        TextView twoStar = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_two_star);
        TextView oneStar = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_one_star);

        TextView educationRating = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_education_rating);
        TextView infrastructureRating = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_infrastructure_rating);
        TextView teachersRating = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_teachers_rating);
        TextView managementRating = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_reviews_mgmt_rating);

        ProgressBar progressBar5 = (ProgressBar) rootView.findViewById(R.id.pb_inst_loader_vp_reviews_5_star);
        ProgressBar progressBar4 = (ProgressBar) rootView.findViewById(R.id.pb_inst_loader_vp_reviews_4_star);
        ProgressBar progressBar3 = (ProgressBar) rootView.findViewById(R.id.pb_inst_loader_vp_reviews_3_star);
        ProgressBar progressBar2 = (ProgressBar) rootView.findViewById(R.id.pb_inst_loader_vp_reviews_2_star);
        ProgressBar progressBar1 = (ProgressBar) rootView.findViewById(R.id.pb_inst_loader_vp_reviews_1_star);

        ProgressBar progressBarEduQuality = (ProgressBar) rootView.findViewById(R.id.cpb_inst_loader_vp_reviews_eq);
        ProgressBar progressBarInfraStructure = (ProgressBar) rootView.findViewById(R.id.cpb_inst_loader_vp_reviews_is);
        ProgressBar progressBarTeachers = (ProgressBar) rootView.findViewById(R.id.cpb_inst_loader_vp_reviews_tec);
        ProgressBar progressBarManagement = (ProgressBar) rootView.findViewById(R.id.cpb_inst_loader_vp_reviews_mgt);

        overallRating.setText(String.valueOf(ratingsData.getOverallRating()));
        totalRating.setText(String.valueOf(ratingsData.getTotalRating()) + " " + "ratings and");
        totalReview.setText(String.valueOf(ratingsData.getTotalReviews()) + " " + "reviews");

        fiveStar.setText(String.valueOf(ratingsData.getFiveStar()));
        fourStar.setText(String.valueOf(ratingsData.getFourStar()));
        threeStar.setText(String.valueOf(ratingsData.getThreeStar()));
        twoStar.setText(String.valueOf(ratingsData.getTwoStar()));
        oneStar.setText(String.valueOf(ratingsData.getOneStar()));

        educationRating.setText(String.valueOf(ratingsData.getEducationRating()));
        infrastructureRating.setText(String.valueOf(ratingsData.getInfrastructureRating()));
        teachersRating.setText(String.valueOf(ratingsData.getTeachersRating()));
        managementRating.setText(String.valueOf(ratingsData.getManagementRating()));


        int progressBarTotal = 100;
        int percentFiveStar = (ratingsData.getFiveStar()*100)/ratingsData.getTotalRating();
        int percentFourStar = (ratingsData.getFourStar()*100)/ratingsData.getTotalRating();
        int percentThreeStar = (ratingsData.getThreeStar()*100)/ratingsData.getTotalRating();
        int percentTwoStar = (ratingsData.getTwoStar()*100)/ratingsData.getTotalRating();
        int percentOneStar = (ratingsData.getOneStar()*100)/ratingsData.getTotalRating();
        int cEducation = (int) (ratingsData.getEducationRating()*100)/5;
        int cInfrastructure = (int) (ratingsData.getInfrastructureRating()*100)/5;
        int cTeachers = (int) (ratingsData.getTeachersRating()*100)/5;
        int cManagement = (int) (ratingsData.getManagementRating()*100)/5;

        setUpLinearProgressBar(progressBar5, percentFiveStar, progressBarTotal);
        setUpLinearProgressBar(progressBar4, percentFourStar, progressBarTotal);
        setUpLinearProgressBar(progressBar3, percentThreeStar, progressBarTotal);
        setUpLinearProgressBar(progressBar2, percentTwoStar, progressBarTotal);
        setUpLinearProgressBar(progressBar1, percentOneStar, progressBarTotal);

        setUpCircularProgressBar(progressBarEduQuality, cEducation, progressBarTotal);
        setUpCircularProgressBar(progressBarInfraStructure, cInfrastructure, progressBarTotal);
        setUpCircularProgressBar(progressBarTeachers, cTeachers, progressBarTotal);
        setUpCircularProgressBar(progressBarManagement, cManagement, progressBarTotal);

    }

    private void setUpLinearProgressBar(ProgressBar pb, int progress, int max) {
        Drawable linearDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.linear_rating_bar);
        pb.setProgress(progress);
        pb.setMax(max);
        pb.setProgressDrawable(linearDrawable);

        ObjectAnimator animator = ObjectAnimator.ofInt(pb, "progress", 0, progress);
        animator.setDuration(8000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    private void setUpCircularProgressBar(ProgressBar pb, int progress, int max) {
        Drawable circularDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.circular_rating_bar);
        pb.setProgress(progress);
        pb.setMax(max);
        pb.setProgressDrawable(circularDrawable);

        ObjectAnimator animator = ObjectAnimator.ofInt(pb, "progress", 0, progress);
        animator.setDuration(8000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    private void initRatingAndReviewSection() {
        swipeLayout = (RelativeLayout) rootView.findViewById(R.id.root_rating_section);
        viewPager = (ViewPager) getActivity().findViewById(R.id.vp_inst_loader_frag);

        overallRating = (RelativeLayout) rootView.findViewById(R.id.l_rating_overall);
        educationRating = (RelativeLayout) rootView.findViewById(R.id.l_rating_education);
        infrastructureRating = (RelativeLayout) rootView.findViewById(R.id.l_rating_infrastructure);
        teachersRating = (RelativeLayout) rootView.findViewById(R.id.l_rating_teachers);
        managementRating = (RelativeLayout) rootView.findViewById(R.id.l_rating_management);
        reviews = (RelativeLayout) rootView.findViewById(R.id.l_rating_review);

        submitNextFinished = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_submit_next_finish);
        sliderIndicator = (ImageView) rootView.findViewById(R.id.iv_inst_loader_vp_review_submit_next_finish);

        ratingReviewsTouchListener();
        overAllRatingSection();
        educationQualityRatingSection();
        infrastructureRatingSection();
        teacherRatingSection();
        managementRatingSection();
        shortReviewSection();
    }

    private void ratingReviewsTouchListener() {
        gestureDetector = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                rightSwipe();
                            } else {
                                leftSwipe();
                            }
                        }
                        result = true;
                    } else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            Toast.makeText(getContext(), "Down", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Up", Toast.LENGTH_SHORT).show();
                        }
                    }
                    result = true;

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }

        });
        swipeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        viewPager.requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        viewPager.requestDisallowInterceptTouchEvent(false);
                        nestedScrollView.requestDisallowInterceptTouchEvent(false);
                        break;
                }
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });
    }

    private void rightSwipe() {

        if (visibility(overallRating)) {
            overallRating.setVisibility(View.VISIBLE);
        } else if (visibility(educationRating)) {
            educationRating.setVisibility(View.INVISIBLE);
            overallRating.setVisibility(View.VISIBLE);
        } else if (visibility(infrastructureRating)) {
            infrastructureRating.setVisibility(View.INVISIBLE);
            educationRating.setVisibility(View.VISIBLE);
        } else if (visibility(teachersRating)) {
            teachersRating.setVisibility(View.INVISIBLE);
            infrastructureRating.setVisibility(View.VISIBLE);
        } else if (visibility(managementRating)) {
            managementRating.setVisibility(View.INVISIBLE);
            teachersRating.setVisibility(View.VISIBLE);
        } else if (visibility(reviews)) {
            reviews.setVisibility(View.INVISIBLE);
            managementRating.setVisibility(View.VISIBLE);
        }
    }

    private void leftSwipe() {
        if (visibility(overallRating)) {
            overallRating.setVisibility(View.INVISIBLE);
            educationRating.setVisibility(View.VISIBLE);
        } else if (visibility(educationRating)) {
            educationRating.setVisibility(View.INVISIBLE);
            infrastructureRating.setVisibility(View.VISIBLE);
        } else if (visibility(infrastructureRating)) {
            infrastructureRating.setVisibility(View.INVISIBLE);
            teachersRating.setVisibility(View.VISIBLE);
        } else if (visibility(teachersRating)) {
            teachersRating.setVisibility(View.INVISIBLE);
            managementRating.setVisibility(View.VISIBLE);
        } else if (visibility(managementRating)) {
            managementRating.setVisibility(View.INVISIBLE);
            reviews.setVisibility(View.VISIBLE);
        } else if (visibility(reviews)) {
            reviews.setVisibility(View.VISIBLE);
        }
    }

    private boolean visibility(RelativeLayout layout) {
        return layout.getVisibility() == View.VISIBLE;
    }

    private void overAllRatingSection(){
        RatingBar overallRating = (RatingBar) rootView.findViewById(R.id.rb_inst_loader_vp_review_overall_rating);
        TextView ratingType = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_overall_rating_type);
        ratingType.setVisibility(View.INVISIBLE);


        onRatingBarChanged(overallRating, ratingType);
    }

    private void educationQualityRatingSection() {
        RatingBar eduRating = (RatingBar) rootView.findViewById(R.id.rb_inst_loader_vp_review_edu_quality_rating);
        TextView ratingType = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_edu_quality_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(eduRating, ratingType);
    }

    private void infrastructureRatingSection() {
        RatingBar infraRating = (RatingBar) rootView.findViewById(R.id.rb_inst_loader_vp_review_infrastructure_rating);
        TextView ratingType = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_infrastructure_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(infraRating, ratingType);
    }

    private void teacherRatingSection() {
        RatingBar teacherRating = (RatingBar) rootView.findViewById(R.id.rb_inst_loader_vp_review_teachers_rating);
        TextView ratingType = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_teachers_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(teacherRating, ratingType);
    }

    private void managementRatingSection() {
        RatingBar mgmtRating = (RatingBar) rootView.findViewById(R.id.rb_inst_loader_vp_review_management_rating);
        TextView ratingType = (TextView) rootView.findViewById(R.id.lbl_inst_loader_vp_review_management_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(mgmtRating, ratingType);
    }

    private void shortReviewSection() {
        EditText shortReview = (EditText) rootView.findViewById(R.id.et_inst_loader_vp_review_short_review);
    }

    private void onRatingBarChanged(RatingBar ratingBar, final TextView ratingType) {

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    ratingType.setVisibility(View.INVISIBLE);
                } else {
                    ratingType.setVisibility(View.VISIBLE);
                    switch ((int) rating) {
                        case 1:
                            ratingType.setText("Hated it");
                            break;
                        case 2:
                            ratingType.setText("bad");
                            break;
                        case 3:
                            ratingType.setText("Not bad");
                            break;
                        case 4:
                            ratingType.setText("Good");
                            break;
                        case 5:
                            ratingType.setText("Super");
                            break;
                        default:
                            break;
                    }
                }

            }
        });
    }

    @Override
    public void setUpReviews(List<InstitutionReviewsData> reviewsData) {
        this.reviewData = reviewsData;
        reviewRecyclerView.setNestedScrollingEnabled(false);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ReviewsAdapter adapter = new ReviewsAdapter();
        reviewRecyclerView.setAdapter(adapter);
    }

    class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

        @Override
        public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.rec_review_item, parent, false);
            return new ReviewsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ReviewsViewHolder holder, int position) {
            InstitutionReviewsData currentItem = reviewData.get(position);
            int reviewId = Integer.parseInt(currentItem.getReview());

            holder.rating.setText(currentItem.getOverallRating());
            holder.reviewHeading.setText(currentItem.getReviewHeading());
            // While loading data from database replace intro id by currentItem.getIntro()
            holder.review.setText(getContext().getResources().getString(reviewId));

            holder.nameAndDate.setText(currentItem.getName() + " " + currentItem.getDate());
            holder.likeCount.setText(String.valueOf(currentItem.getLikeCount()));
            holder.dislikeCount.setText(String.valueOf(currentItem.getDislikeCount()));

        }

        @Override
        public int getItemCount() {
            return reviewData.size()-6;
        }

        class ReviewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView rating;
            private TextView reviewHeading;
            private TextView review;
            private TextView nameAndDate;
            private ImageView ivLike;
            private TextView likeCount;
            private ImageView ivDislike;
            private TextView dislikeCount;
            private ImageView ivMore;

            ReviewsViewHolder(View itemView) {
                super(itemView);

                rating = (TextView) itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_rating);
                reviewHeading = (TextView) itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_heading);
                review = (TextView) itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_review);
                nameAndDate = (TextView) itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_name_date);
                ivLike = (ImageView) itemView.findViewById(R.id.iv_inst_loader_vp_reviews_rec_like);
                likeCount = (TextView) itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_like_count);
                ivDislike = (ImageView) itemView.findViewById(R.id.iv_inst_loader_vp_reviews_rec_dislike);
                dislikeCount = (TextView) itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_dislike_count);
                ivMore = (ImageView) itemView.findViewById(R.id.iv_inst_loader_vp_reviews_rec_more);

                ivLike.setOnClickListener(this);
                ivDislike.setOnClickListener(this);
                ivMore.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_inst_loader_vp_reviews_rec_like:
                        Toast.makeText(getContext(),"Like",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.iv_inst_loader_vp_reviews_rec_dislike:
                        Toast.makeText(getContext(),"Dislike",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.iv_inst_loader_vp_reviews_rec_more:
                        Toast.makeText(getContext(),"More",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
