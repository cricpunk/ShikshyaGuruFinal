package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 11/5/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import java.util.List;

public class InstitutionsProgrammesCoursesData {

    private String[] compulsorySubjects;
    private List<String[]> subjectOptionsCollection;

    public InstitutionsProgrammesCoursesData(String[] compulsorySubjects, List<String[]> subjectOptionsCollection) {
        this.compulsorySubjects = compulsorySubjects;
        this.subjectOptionsCollection = subjectOptionsCollection;
    }

    public String[] getCompulsorySubjects() {
        return compulsorySubjects;
    }

    public void setCompulsorySubjects(String[] compulsorySubjects) {
        this.compulsorySubjects = compulsorySubjects;
    }

    public List<String[]> getSubjectOptionsCollection() {
        return subjectOptionsCollection;
    }

    public void setSubjectOptionsCollection(List<String[]> subjectOptionsCollection) {
        this.subjectOptionsCollection = subjectOptionsCollection;
    }
}
