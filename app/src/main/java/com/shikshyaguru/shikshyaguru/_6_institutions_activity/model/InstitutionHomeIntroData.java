package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/12/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class InstitutionHomeIntroData {

    private int image;
    private String introHeading;
    private String intro;

    public InstitutionHomeIntroData(int image, String introHeading, String intro) {
        this.image = image;
        this.introHeading = introHeading;
        this.intro = intro;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getIntroHeading() {
        return introHeading;
    }

    public void setIntroHeading(String introHeading) {
        this.introHeading = introHeading;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
