package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;

import java.util.List;

/**
 * Created by cricpunk on 9/4/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class ListOfInstitutionsHeading {

    private String institutionHeading;
    private int institutionHeadingId;
    private List<?> relatedInstitutionData;

    ListOfInstitutionsHeading(String institutionHeading, int institutionHeadingId, List<?> relatedInstitutionData) {
        this.institutionHeading = institutionHeading;
        this.institutionHeadingId = institutionHeadingId;
        this.relatedInstitutionData = relatedInstitutionData;
    }

    public String getInstitutionHeading() {
        return institutionHeading;
    }

    public void setInstitutionHeading(String institutionHeading) {
        this.institutionHeading = institutionHeading;
    }

    public int getInstitutionHeadingId() {
        return institutionHeadingId;
    }

    public void setInstitutionHeadingId(int institutionHeadingId) {
        this.institutionHeadingId = institutionHeadingId;
    }

    public List<?> getRelatedInstitutionData() {
        return relatedInstitutionData;
    }

    public void setRelatedInstitutionData(List<?> relatedInstitutionData) {
        this.relatedInstitutionData = relatedInstitutionData;
    }
}
