package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;

/**
 * Created by cricpunk on 8/30/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class InstitutionsListItemParent {
    private int institutionsIcon;
    private String institutionsName;
    private String institutionsRating;
    private String institutionCityName;

    InstitutionsListItemParent(int institutionsIcon, String institutionsName, String institutionsRating, String institutionCityName) {
        this.institutionsIcon = institutionsIcon;
        this.institutionsName = institutionsName;
        this.institutionsRating = institutionsRating;
        this.institutionCityName = institutionCityName;
    }

    public InstitutionsListItemParent() {
    }

    public int getInstitutionsIcon() {
        return institutionsIcon;
    }

    public void setInstitutionsIcon(int institutionsIcon) {
        this.institutionsIcon = institutionsIcon;
    }

    public String getInstitutionsName() {
        return institutionsName;
    }

    public void setInstitutionsName(String institutionsName) {
        this.institutionsName = institutionsName;
    }

    public String getInstitutionsRating() {
        return institutionsRating;
    }

    public void setInstitutionsRating(String institutionsRating) {
        this.institutionsRating = institutionsRating;
    }

    public String getInstitutionCityName() {
        return institutionCityName;
    }

    public void setInstitutionCityName(String institutionCityName) {
        this.institutionCityName = institutionCityName;
    }
}
