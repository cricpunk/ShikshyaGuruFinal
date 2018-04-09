package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;

/*
 * Created by Pankaj Koirala on 4/9/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class InstitutionContactData {

    private String name;
    private String phone;
    private String email;
    private String message;

    public InstitutionContactData(String name, String phone, String email, String message) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
