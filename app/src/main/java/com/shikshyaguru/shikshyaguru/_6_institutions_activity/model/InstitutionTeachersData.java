package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/23/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class InstitutionTeachersData {

    private String id;
    private String full_name;
    private String image_url;
    private String designation;
    private String experiance;
    private String phone_1;
    private String phone_2;
    private String email_1;
    private String email_2;
    private String rating;

    public InstitutionTeachersData() {
        //For firebase
    }

    public InstitutionTeachersData(String full_name, String image_url, String designation, String experiance, String phone_1, String phone_2, String email_1, String email_2, String rating) {
        this.full_name = full_name;
        this.image_url = image_url;
        this.designation = designation;
        this.experiance = experiance;
        this.phone_1 = phone_1;
        this.phone_2 = phone_2;
        this.email_1 = email_1;
        this.email_2 = email_2;
        this.rating = rating;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getExperiance() {
        return experiance;
    }

    public void setExperiance(String experiance) {
        this.experiance = experiance;
    }

    public String getPhone_1() {
        return phone_1;
    }

    public void setPhone_1(String phone_1) {
        this.phone_1 = phone_1;
    }

    public String getPhone_2() {
        return phone_2;
    }

    public void setPhone_2(String phone_2) {
        this.phone_2 = phone_2;
    }

    public String getEmail_1() {
        return email_1;
    }

    public void setEmail_1(String email_1) {
        this.email_1 = email_1;
    }

    public String getEmail_2() {
        return email_2;
    }

    public void setEmail_2(String email_2) {
        this.email_2 = email_2;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

}
