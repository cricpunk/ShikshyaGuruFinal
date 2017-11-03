package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 11/1/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

import java.util.HashMap;

public class InstitutionProgrammesData {

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
}
