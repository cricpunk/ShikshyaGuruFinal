package com.shikshyaguru.shikshyaguru._6_institutions_activity.views.viewpager_fragments;
/*
 * Created by Pankaj Koirala on 10/8/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.devs.vectorchildfinder.VectorChildFinder;
import com.devs.vectorchildfinder.VectorDrawableCompat;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.shikshyaguru.shikshyaguru.R;
import com.shikshyaguru.shikshyaguru._4_home_page_activity.views.NavigationDrawerFragment;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionDataSource;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionRatingsData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.model.InstitutionReviewsData;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.presenter.VPReviewsController;
import com.shikshyaguru.shikshyaguru._6_institutions_activity.views.InstitutionLoaderFragment;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ViewPagerReviewsFragment extends Fragment implements ViewPagerReviewInterface, View.OnClickListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetectorCompat gestureDetector;

    private static final String instPath = "instRating";
    private static final String eduPath = "eduRating";
    private static final String infraPath = "infraRating";
    private static final String techPath = "techRating";
    private static final String mgmtPath = "mgmtRating";
    private static final String reviewPath = "review";

    private RelativeLayout swipeLayout, overallRating, educationRating, infrastructureRating,
            teachersRating, managementRating, reviews;

    RatingBar overallRatingBar, eduRatingBar, infraRatingBar, teacherRatingBar, mgmtRatingBar;
    EditText shortReview;

    private TextView submitNextFinished;
    private ImageView sliderIndicator;

    private ViewPager viewPager;
    private NestedScrollView nestedScrollView;

    private View rootView;
    private LayoutInflater inflater;
    private RecyclerView reviewRecyclerView;
    private VPReviewsController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout._6_2_11_0_view_pager_reviews, container, false);

        this.inflater = inflater;
        this.rootView = view;

        nestedScrollView = view.findViewById(R.id.root_reviews_nested_scroll);
        reviewRecyclerView = view.findViewById(R.id.rec_reviews);

        controller = new VPReviewsController(this, new InstitutionDataSource());
        //Set ratings view with data from firebase
        controller.getRatings(InstitutionLoaderFragment.id);
        // Set reviews adapter with data
        controller.setUpReviews(InstitutionLoaderFragment.id);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRatingAndReviewSection();
    }

    private void initRatingAndReviewSection() {

        ImageView userImage = rootView.findViewById(R.id.iv_inst_loader_vp_reviews_user);
        Picasso.get()
                .load(NavigationDrawerFragment.currentUser.getPhotoUrl())
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_user)
                .into(userImage);

        swipeLayout = rootView.findViewById(R.id.root_rating_section);
        viewPager = Objects.requireNonNull(getActivity()).findViewById(R.id.vp_inst_loader_frag);

        overallRating = rootView.findViewById(R.id.l_rating_overall);
        educationRating = rootView.findViewById(R.id.l_rating_education);
        infrastructureRating = rootView.findViewById(R.id.l_rating_infrastructure);
        teachersRating = rootView.findViewById(R.id.l_rating_teachers);
        managementRating = rootView.findViewById(R.id.l_rating_management);
        reviews = rootView.findViewById(R.id.l_rating_review);

        submitNextFinished = rootView.findViewById(R.id.btn_inst_loader_vp_review_submit_next_finish);
        sliderIndicator = rootView.findViewById(R.id.iv_inst_loader_vp_review_submit_next_finish);

        // Set selector indicator to first view
        selectedIndicator(instPath, eduPath);
        // Disable submit button
        submitNextFinished.setVisibility(View.INVISIBLE);
        // Set indicators visibility gone
        sliderIndicator.setVisibility(View.INVISIBLE);

        // Submit button on click listener
        submitNextFinished.setOnClickListener(this);

        overAllRatingSection();
        educationQualityRatingSection();
        infrastructureRatingSection();
        teacherRatingSection();
        managementRatingSection();
        shortReviewSection();

    }

    // This is the method which is called from datasource using interface
    @Override
    public void setUpRatings(InstitutionRatingsData ratingsData) {

        TextView overallRating = rootView.findViewById(R.id.lbl_inst_loader_vp_review_overall_rating);
        TextView totalRating = rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_rating);
        TextView totalReview = rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_review);

        TextView fiveStar = rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_five_star);
        TextView fourStar = rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_four_star);
        TextView threeStar = rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_three_star);
        TextView twoStar = rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_two_star);
        TextView oneStar = rootView.findViewById(R.id.lbl_inst_loader_vp_review_total_one_star);

        TextView educationRating = rootView.findViewById(R.id.lbl_inst_loader_vp_review_education_rating);
        TextView infrastructureRating = rootView.findViewById(R.id.lbl_inst_loader_vp_review_infrastructure_rating);
        TextView teachersRating = rootView.findViewById(R.id.lbl_inst_loader_vp_review_teachers_rating);
        TextView managementRating = rootView.findViewById(R.id.lbl_inst_loader_vp_reviews_mgmt_rating);

        ProgressBar progressBar5 = rootView.findViewById(R.id.pb_inst_loader_vp_reviews_5_star);
        ProgressBar progressBar4 = rootView.findViewById(R.id.pb_inst_loader_vp_reviews_4_star);
        ProgressBar progressBar3 = rootView.findViewById(R.id.pb_inst_loader_vp_reviews_3_star);
        ProgressBar progressBar2 = rootView.findViewById(R.id.pb_inst_loader_vp_reviews_2_star);
        ProgressBar progressBar1 = rootView.findViewById(R.id.pb_inst_loader_vp_reviews_1_star);

        ProgressBar progressBarEduQuality = rootView.findViewById(R.id.cpb_inst_loader_vp_reviews_eq);
        ProgressBar progressBarInfraStructure = rootView.findViewById(R.id.cpb_inst_loader_vp_reviews_is);
        ProgressBar progressBarTeachers = rootView.findViewById(R.id.cpb_inst_loader_vp_reviews_tec);
        ProgressBar progressBarManagement = rootView.findViewById(R.id.cpb_inst_loader_vp_reviews_mgt);

        overallRating.setText(String.valueOf(ratingsData.getOverallRating()));
        totalRating.setText(String.format("%s ratings and", String.valueOf(ratingsData.getTotalRating())));
        totalReview.setText(String.format("%s reviews", String.valueOf(ratingsData.getTotalReviews())));

        fiveStar.setText(String.valueOf(ratingsData.getFiveStar()));
        fourStar.setText(String.valueOf(ratingsData.getFourStar()));
        threeStar.setText(String.valueOf(ratingsData.getThreeStar()));
        twoStar.setText(String.valueOf(ratingsData.getTwoStar()));
        oneStar.setText(String.valueOf(ratingsData.getOneStar()));

        educationRating.setText(String.valueOf(ratingsData.getEducationRating()));
        infrastructureRating.setText(String.valueOf(ratingsData.getInfrastructureRating()));
        teachersRating.setText(String.valueOf(ratingsData.getTeachersRating()));
        managementRating.setText(String.valueOf(ratingsData.getManagementRating()));

        try {

            int progressBarTotal = 100;
            int percentFiveStar;
            int percentFourStar;
            int percentThreeStar;
            int percentTwoStar;
            int percentOneStar;

            // If total rating is zero then it will generate NaN error during runtime (Divide by 0)
            // This is the validation for that part
            if (ratingsData.getTotalRating() != 0 ) {
                percentFiveStar = (ratingsData.getFiveStar()*100)/ratingsData.getTotalRating();
                percentFourStar = (ratingsData.getFourStar()*100)/ratingsData.getTotalRating();
                percentThreeStar = (ratingsData.getThreeStar()*100)/ratingsData.getTotalRating();
                percentTwoStar = (ratingsData.getTwoStar()*100)/ratingsData.getTotalRating();
                percentOneStar = (ratingsData.getOneStar()*100)/ratingsData.getTotalRating();

            } else {
                percentFiveStar = 1;
                percentFourStar = 1;
                percentThreeStar = 1;
                percentTwoStar = 1;
                percentOneStar = 1;
            }

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

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void setUpReviews(FirebaseRecyclerOptions<InstitutionReviewsData> options) {

        reviewRecyclerView.setNestedScrollingEnabled(false);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ReviewsAdapter adapter = new ReviewsAdapter(options);
        reviewRecyclerView.setAdapter(adapter);

        adapter.startListening();

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_inst_loader_vp_review_submit_next_finish:

                if (submitNextFinished.getText().toString().toLowerCase().equals("submit")) {

                    ratingReviewsTouchListener();
                    overallRating.setVisibility(View.INVISIBLE);
                    educationRating.setVisibility(View.VISIBLE);
                    submitNextFinished.setVisibility(View.INVISIBLE);
                    selectedIndicator(eduPath, instPath);

                } else {


                    int instRating = (int) overallRatingBar.getRating();
                    int eduRating = (int) eduRatingBar.getRating();
                    int infraRating = (int) infraRatingBar.getRating();
                    int techRating = (int) teacherRatingBar.getRating();
                    int mgmtRating = (int) mgmtRatingBar.getRating();
                    String comment = shortReview.getText().toString();

                    String uId = NavigationDrawerFragment.currentUser.getUid();
                    String id = InstitutionLoaderFragment.id;

                    controller.submitReviews(id, uId, instRating, eduRating, infraRating, techRating, mgmtRating, comment);

                    //swipeLayout.setOnTouchListener(null);
                    // Put small validation for review section

                }

                break;

            default:
                break;
        }
    }

    private void overAllRatingSection(){
        overallRatingBar = rootView.findViewById(R.id.rb_inst_loader_vp_review_overall_rating);
        TextView ratingType = rootView.findViewById(R.id.lbl_inst_loader_vp_review_overall_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(overallRatingBar, ratingType);
    }

    private void educationQualityRatingSection() {
        eduRatingBar = rootView.findViewById(R.id.rb_inst_loader_vp_review_edu_quality_rating);
        TextView ratingType = rootView.findViewById(R.id.lbl_inst_loader_vp_review_edu_quality_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(eduRatingBar, ratingType);
    }

    private void infrastructureRatingSection() {
        infraRatingBar = rootView.findViewById(R.id.rb_inst_loader_vp_review_infrastructure_rating);
        TextView ratingType = rootView.findViewById(R.id.lbl_inst_loader_vp_review_infrastructure_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(infraRatingBar, ratingType);
    }

    private void teacherRatingSection() {
        teacherRatingBar = rootView.findViewById(R.id.rb_inst_loader_vp_review_teachers_rating);
        TextView ratingType = rootView.findViewById(R.id.lbl_inst_loader_vp_review_teachers_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(teacherRatingBar, ratingType);
    }

    private void managementRatingSection() {
        mgmtRatingBar = rootView.findViewById(R.id.rb_inst_loader_vp_review_management_rating);
        TextView ratingType = rootView.findViewById(R.id.lbl_inst_loader_vp_review_management_rating_type);
        ratingType.setVisibility(View.INVISIBLE);
        onRatingBarChanged(mgmtRatingBar, ratingType);
    }

    private void shortReviewSection() {
        shortReview = rootView.findViewById(R.id.et_inst_loader_vp_review_short_review);
    }

    private void onRatingBarChanged(RatingBar ratingBar, final TextView ratingType) {

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                if (rating == 0) {
                    ratingType.setVisibility(View.INVISIBLE);
                    if (ratingBar == overallRatingBar ) {
                        // Enable submit button
                        submitNextFinished.setVisibility(View.INVISIBLE);
                        // Set indicators visibility visible
                        sliderIndicator.setVisibility(View.INVISIBLE);
                    }
                } else {

                    if (ratingBar == overallRatingBar ) {
                        // Enable submit button
                        submitNextFinished.setVisibility(View.VISIBLE);
                        // Set indicators visibility visible
                        sliderIndicator.setVisibility(View.VISIBLE);
                    }

                    ratingType.setVisibility(View.VISIBLE);
                    switch ((int) rating) {
                        case 1:
                            ratingType.setText(R.string.one_star);
                            break;
                        case 2:
                            ratingType.setText(R.string.two_star);
                            break;
                        case 3:
                            ratingType.setText(R.string.three_star);
                            break;
                        case 4:
                            ratingType.setText(R.string.four_star);
                            break;
                        case 5:
                            ratingType.setText(R.string.five_star);
                            break;
                        default:
                            break;
                    }
                }

            }
        });

    }

    @SuppressLint("ClickableViewAccessibility")
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
//                            Toast.makeText(getContext(), "Down", Toast.LENGTH_SHORT).show();
                            viewPager.requestDisallowInterceptTouchEvent(false);
                            nestedScrollView.requestDisallowInterceptTouchEvent(false);
                        } else {
//                            Toast.makeText(getContext(), "Up", Toast.LENGTH_SHORT).show();
                            viewPager.requestDisallowInterceptTouchEvent(false);
                            nestedScrollView.requestDisallowInterceptTouchEvent(false);
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
                        nestedScrollView.requestDisallowInterceptTouchEvent(true);
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
            selectedIndicator(instPath, eduPath);
            submitNextFinished.setText(R.string.submit);
            submitNextFinished.setVisibility(View.VISIBLE);
        } else if (visibility(infrastructureRating)) {
            infrastructureRating.setVisibility(View.INVISIBLE);
            educationRating.setVisibility(View.VISIBLE);
            selectedIndicator(eduPath, infraPath);
        } else if (visibility(teachersRating)) {
            teachersRating.setVisibility(View.INVISIBLE);
            infrastructureRating.setVisibility(View.VISIBLE);
            selectedIndicator(infraPath, techPath);
        } else if (visibility(managementRating)) {
            managementRating.setVisibility(View.INVISIBLE);
            teachersRating.setVisibility(View.VISIBLE);
            selectedIndicator(techPath, mgmtPath);
        } else if (visibility(reviews)) {
            reviews.setVisibility(View.INVISIBLE);
            managementRating.setVisibility(View.VISIBLE);
            selectedIndicator(mgmtPath, reviewPath);
            submitNextFinished.setVisibility(View.INVISIBLE);
        }

    }

    private void leftSwipe() {
        if (visibility(overallRating)) {
            overallRating.setVisibility(View.INVISIBLE);
            educationRating.setVisibility(View.VISIBLE);
            selectedIndicator(eduPath, instPath);
            submitNextFinished.setVisibility(View.INVISIBLE);
        } else if (visibility(educationRating)) {
            educationRating.setVisibility(View.INVISIBLE);
            infrastructureRating.setVisibility(View.VISIBLE);
            selectedIndicator(infraPath, eduPath);
        } else if (visibility(infrastructureRating)) {
            infrastructureRating.setVisibility(View.INVISIBLE);
            teachersRating.setVisibility(View.VISIBLE);
            selectedIndicator(techPath, infraPath);
        } else if (visibility(teachersRating)) {
            teachersRating.setVisibility(View.INVISIBLE);
            managementRating.setVisibility(View.VISIBLE);
            selectedIndicator(mgmtPath, techPath);
        } else if (visibility(managementRating)) {
            managementRating.setVisibility(View.INVISIBLE);
            reviews.setVisibility(View.VISIBLE);
            selectedIndicator(reviewPath, mgmtPath);
            submitNextFinished.setText(R.string.btn_finish);
            submitNextFinished.setVisibility(View.VISIBLE);
        } else if (visibility(reviews)) {
            reviews.setVisibility(View.VISIBLE);
        }
    }

    private boolean visibility(RelativeLayout layout) {
        return layout.getVisibility() == View.VISIBLE;
    }

    class ReviewsAdapter extends FirebaseRecyclerAdapter<InstitutionReviewsData, ReviewsAdapter.ReviewsViewHolder> {

        /*
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        ReviewsAdapter(@NonNull FirebaseRecyclerOptions<InstitutionReviewsData> options) {
            super(options);
        }

        @NonNull
        @Override
        public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout._6_2_11_2_rec_review_item, parent, false);
            return new ReviewsViewHolder(view);
        }

        @Override
        protected void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position, @NonNull InstitutionReviewsData model) {

            holder.rating.setText(String.valueOf(model.getRating()));
            holder.reviewHeading.setText(model.getHeading());
            holder.review.setText(model.getComment());
            holder.nameAndDate.setText(String.format("%s , %s", model.getCommentedBy(), model.getPost_time()));
            holder.likeCount.setText(String.valueOf(model.getComment_like()));
            holder.dislikeCount.setText(String.valueOf(model.getComment_dislike()));

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

                rating = itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_rating);
                reviewHeading = itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_heading);
                review = itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_review);
                nameAndDate = itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_name_date);
                ivLike = itemView.findViewById(R.id.iv_inst_loader_vp_reviews_rec_like);
                likeCount = itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_like_count);
                ivDislike = itemView.findViewById(R.id.iv_inst_loader_vp_reviews_rec_dislike);
                dislikeCount = itemView.findViewById(R.id.lbl_inst_loader_vp_reviews_rec_dislike_count);
                ivMore = itemView.findViewById(R.id.iv_inst_loader_vp_reviews_rec_more);

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

    private void setUpLinearProgressBar(ProgressBar pb, int progress, int max) {
        Drawable linearDrawable = ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.linear_rating_bar);
        pb.setProgress(progress);
        pb.setMax(max);
        pb.setProgressDrawable(linearDrawable);

        ObjectAnimator animator = ObjectAnimator.ofInt(pb, "progress", 0, progress);
        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    private void setUpCircularProgressBar(ProgressBar pb, int progress, int max) {
        Drawable circularDrawable = ContextCompat.getDrawable(Objects.requireNonNull(getActivity()), R.drawable.circular_rating_bar);
        pb.setProgress(progress);
        pb.setMax(max);
        pb.setProgressDrawable(circularDrawable);

        ObjectAnimator animator = ObjectAnimator.ofInt(pb, "progress", 0, progress);
        animator.setDuration(3000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    private void selectedIndicator(String visiblePath, String invisiblePath) {

        VectorChildFinder vectorChild = new VectorChildFinder(Objects.requireNonNull(getContext()), R.drawable.ic_review_indicator, sliderIndicator);

        VectorDrawableCompat.VFullPath vPath = vectorChild.findPathByName(visiblePath);
        VectorDrawableCompat.VFullPath iPath = vectorChild.findPathByName(invisiblePath);

        vPath.setFillColor(Color.parseColor("#7c3cea"));
        iPath.setFillColor(Color.parseColor("#9E9E9E"));

        sliderIndicator.invalidate();

    }

}
