package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:45 PM 15 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class InstitutionManagementData {

    private String name;
    private String post;
    private String academicQualification;
    private String institution;
    private String message;
    private int image;

    public InstitutionManagementData(String name, String post, String academicQualification, String institution, String message, int image) {
        this.name = name;
        this.post = post;
        this.academicQualification = academicQualification;
        this.institution = institution;
        this.message = message;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getAcademicQualification() {
        return academicQualification;
    }

    public void setAcademicQualification(String academicQualification) {
        this.academicQualification = academicQualification;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
