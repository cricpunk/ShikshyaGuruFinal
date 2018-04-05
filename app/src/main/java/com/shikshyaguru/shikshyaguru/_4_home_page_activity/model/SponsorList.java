package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;

/*
 * Created by Pankaj Koirala on 4/4/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class SponsorList {

    String name;
    String main_image;
    String slogan;
    String city;

    public SponsorList() {
    }

    public SponsorList(String name, String main_image, String slogan) {
        this.name = name;
        this.main_image = main_image;
        this.slogan = slogan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
