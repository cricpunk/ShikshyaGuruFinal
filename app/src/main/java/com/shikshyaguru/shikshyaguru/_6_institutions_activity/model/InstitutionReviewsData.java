package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/17/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class InstitutionReviewsData {

    private String commentId;
    private String heading;
    private String comment;
    private String post_time;
    private String commentedBy;
    private int comment_like;
    private int comment_dislike;
    private int rating;

    InstitutionReviewsData() {
        // For firebase
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPost_time() {
        return post_time;
    }

    public void setPost_time(String post_time) {
        this.post_time = post_time;
    }

    public int getComment_like() {
        return comment_like;
    }

    public void setComment_like(int comment_like) {
        this.comment_like = comment_like;
    }

    public String getCommentedBy() {
        return commentedBy;
    }

    public void setCommentedBy(String commentedBy) {
        this.commentedBy = commentedBy;
    }

    public int getComment_dislike() {
        return comment_dislike;
    }

    public void setComment_dislike(int comment_dislike) {
        this.comment_dislike = comment_dislike;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
