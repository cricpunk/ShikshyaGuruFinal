package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class NewsListItem {

    private String heading;
    private String image;
    private String news;
    private String place;
    private String writer;
    private String time;

    public NewsListItem() {
        // Empty constructor required for firebase
    }

    public NewsListItem(String heading, String image, String news, String place, String writer) {
        this.heading = heading;
        this.image = image;
        this.news = news;
        this.place = place;
        this.writer = writer;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
