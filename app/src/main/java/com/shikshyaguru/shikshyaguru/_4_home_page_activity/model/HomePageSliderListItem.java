package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;
/*
 * Created by Pankaj Koirala on 10/4/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class HomePageSliderListItem {

    private String id;
    private String name;
    private String main_image;
    private String slogan;
    private String city;

    HomePageSliderListItem() {
        //Empty constructor for firebase
    }

    public HomePageSliderListItem(String id, String name, String main_image, String slogan, String city) {
        this.id = id;
        this.name = name;
        this.main_image = main_image;
        this.slogan = slogan;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
