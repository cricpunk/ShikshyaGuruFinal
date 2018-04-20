package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 11/1/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import java.util.HashMap;
import java.util.List;

public class InstitutionProgrammesData {

    private List<String> programmesLevelNameList;
    private List<String> programmesCourses;
    private HashMap<String, List<String>> programmesCoursesNameList;

    private String[] programmesLevelName;
    private HashMap<String, String[]> programmesCoursesName;

    public String[] getProgrammesLevelName() {
        return programmesLevelName;
    }

    public void setProgrammesLevelName(String[] programmesLevelName) {
        this.programmesLevelName = programmesLevelName;
    }

    public HashMap<String, String[]> getProgrammesCoursesName() {
        return programmesCoursesName;
    }

    public void setProgrammesCoursesName(HashMap<String, String[]> programmesCoursesName) {
        this.programmesCoursesName = programmesCoursesName;
    }

    public List<String> getProgrammesLevelNameList() {
        return programmesLevelNameList;
    }

    public void setProgrammesLevelNameList(List<String> programmesLevelNameList) {
        this.programmesLevelNameList = programmesLevelNameList;
    }

    public HashMap<String, List<String>> getProgrammesCoursesNameList() {
        return programmesCoursesNameList;
    }

    public void setProgrammesCoursesNameList(HashMap<String, List<String>> programmesCoursesNameList) {
        this.programmesCoursesNameList = programmesCoursesNameList;
    }

    public List<String> getProgrammesCourses() {
        return programmesCourses;
    }

    public void setProgrammesCourses(List<String> programmesCourses) {
        this.programmesCourses = programmesCourses;
    }
}
