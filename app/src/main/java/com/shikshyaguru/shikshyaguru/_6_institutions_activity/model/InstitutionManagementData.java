package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;

/**
 * Project Name => ShikshyaGuru
 * Created by   => Pankaj Koirala
 * Created on   => 1:45 PM 15 Nov 2017
 * Email Id     => koiralapankaj007@gmail.com
 */

public class InstitutionManagementData {

    private String id;
    private String full_name;
    private String designation;
    private String qualification;
    private String university;
    private String image_url;
    private String member_content;

    public InstitutionManagementData() {
        // For firebase
    }

    public InstitutionManagementData(String full_name, String designation, String qualification, String university, String image_url, String member_content) {
        this.full_name = full_name;
        this.designation = designation;
        this.qualification = qualification;
        this.university = university;
        this.image_url = image_url;
        this.member_content = member_content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMember_content() {
        return member_content;
    }

    public void setMember_content(String member_content) {
        this.member_content = member_content;
    }

}
