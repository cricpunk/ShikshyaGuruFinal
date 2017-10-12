package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;
/*
 * Created by Pankaj Koirala on 10/4/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class HomePageSliderListItem {

    private int image;
    private String institutionName;
    private String cityName;

    public HomePageSliderListItem(int image, String institutionName, String cityName) {
        this.image = image;
        this.institutionName = institutionName;
        this.cityName = cityName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
