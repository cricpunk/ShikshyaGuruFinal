package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/17/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class InstitutionReviewsData {
    private String overallRating;
    private String reviewHeading;
    private String review;
    private String name;
    private String date;
    private int likeCount;
    private int dislikeCount;

    public String getOverallRating() {
        return overallRating;
    }

    public void setOverallRating(String overallRating) {
        this.overallRating = overallRating;
    }

    public String getReviewHeading() {
        return reviewHeading;
    }

    public void setReviewHeading(String reviewHeading) {
        this.reviewHeading = reviewHeading;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public InstitutionReviewsData(String overallRating, String reviewHeading, String review, String name, String date, int likeCount, int dislikeCount) {
        this.overallRating = overallRating;
        this.reviewHeading = reviewHeading;
        this.review = review;
        this.name = name;
        this.date = date;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;


    }
}
