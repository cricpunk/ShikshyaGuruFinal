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
    private int category;
    private List<?> relatedInstitutionData;

    private FirebaseRecyclerOptions<?> relatedInstitutionOptions;

    ListOfTotalInstitutions(String institutionHeading, int category, FirebaseRecyclerOptions<?> relatedInstitutionOptions) {
        this.institutionHeading = institutionHeading;
        this.category = category;
        this.relatedInstitutionOptions = relatedInstitutionOptions;
    }

    public String getInstitutionHeading() {
        return institutionHeading;
    }

    public void setInstitutionHeading(String institutionHeading) {
        this.institutionHeading = institutionHeading;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public FirebaseRecyclerOptions<?> getRelatedInstitutionOptions() {
        return relatedInstitutionOptions;
    }

    public void setRelatedInstitutionOptions(FirebaseRecyclerOptions<?> relatedInstitutionOptions) {
        this.relatedInstitutionOptions = relatedInstitutionOptions;
    }

}
