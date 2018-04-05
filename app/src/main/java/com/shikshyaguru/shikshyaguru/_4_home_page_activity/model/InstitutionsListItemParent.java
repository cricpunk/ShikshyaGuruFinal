package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class InstitutionsListItemParent {

    private String icon_image;
    private String name;
    private String rating;
    private String city;

    InstitutionsListItemParent(String icon_image, String name, String rating, String city) {
        this.icon_image = icon_image;
        this.name = name;
        this.rating = rating;
        this.city = city;
    }

    public InstitutionsListItemParent() {
    }

    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
