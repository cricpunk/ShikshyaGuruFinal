package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 11/5/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import java.util.List;

public class InstitutionProgrammesCoursesData {

    private String[] compulsorySubjectsXi;
    private List<String[]> subjectOptionsCollectionXi;

    private String[] compulsorySubjectsXii;
    private List<String[]> subjectOptionsCollectionXii;

    public InstitutionProgrammesCoursesData() {
    }

    public String[] getCompulsorySubjectsXi() {
        return compulsorySubjectsXi;
    }

    public void setCompulsorySubjectsXi(String[] compulsorySubjectsXi) {
        this.compulsorySubjectsXi = compulsorySubjectsXi;
    }

    public List<String[]> getSubjectOptionsCollectionXi() {
        return subjectOptionsCollectionXi;
    }

    public void setSubjectOptionsCollectionXi(List<String[]> subjectOptionsCollectionXi) {
        this.subjectOptionsCollectionXi = subjectOptionsCollectionXi;
    }

    public String[] getCompulsorySubjectsXii() {
        return compulsorySubjectsXii;
    }

    public void setCompulsorySubjectsXii(String[] compulsorySubjectsXii) {
        this.compulsorySubjectsXii = compulsorySubjectsXii;
    }

    public List<String[]> getSubjectOptionsCollectionXii() {
        return subjectOptionsCollectionXii;
    }

    public void setSubjectOptionsCollectionXii(List<String[]> subjectOptionsCollectionXii) {
        this.subjectOptionsCollectionXii = subjectOptionsCollectionXii;
    }

}
