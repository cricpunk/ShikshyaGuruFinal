package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/10/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class InstitutionHomeNewsAndEventsData {

    private String id;
    private String full_name;
    private String image_url;
    private String news_content;
    private String news_heading;
    private String place_name;

    public InstitutionHomeNewsAndEventsData() {
        // Empty constructor for firebase
    }

    public InstitutionHomeNewsAndEventsData(String full_name, String image_url, String news_content, String news_heading, String place_name) {
        this.full_name = full_name;
        this.image_url = image_url;
        this.news_content = news_content;
        this.news_heading = news_heading;
        this.place_name = place_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getNews_content() {
        return news_content;
    }

    public void setNews_content(String news_content) {
        this.news_content = news_content;
    }

    public String getNews_heading() {
        return news_heading;
    }

    public void setNews_heading(String news_heading) {
        this.news_heading = news_heading;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

}
