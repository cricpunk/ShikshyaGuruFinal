package com.shikshyaguru.shikshyaguru._4_home_page_activity.model;

import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

/**
 * Created by cricpunk on 9/4/17.
 * Pankaj Koirala
 * Kathmandu Nepal
 */

public class ListOfTotalInstitutions {

    private String institutionHeading;
    private int institutionHeadingId;
    private List<?> relatedInstitutionData;

    private FirebaseRecyclerOptions<?> relatedInstitutionOptions;

    ListOfTotalInstitutions(String institutionHeading, int institutionHeadingId, List<?> relatedInstitutionData) {
        this.institutionHeading = institutionHeading;
        this.institutionHeadingId = institutionHeadingId;
        this.relatedInstitutionData = relatedInstitutionData;
    }

    ListOfTotalInstitutions(String institutionHeading, int institutionHeadingId, FirebaseRecyclerOptions<?> relatedInstitutionOptions) {
        this.institutionHeading = institutionHeading;
        this.institutionHeadingId = institutionHeadingId;
        this.relatedInstitutionOptions = relatedInstitutionOptions;
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

    public FirebaseRecyclerOptions<?> getRelatedInstitutionOptions() {
        return relatedInstitutionOptions;
    }

    public void setRelatedInstitutionOptions(FirebaseRecyclerOptions<?> relatedInstitutionOptions) {
        this.relatedInstitutionOptions = relatedInstitutionOptions;
    }

}
