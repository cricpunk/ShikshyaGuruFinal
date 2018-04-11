package com.shikshyaguru.shikshyaguru._3_signUp_activity.model;

/*
 * Created by Pankaj Koirala on 4/3/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class NewUserData {

    private String name, user_name, email, password, type;

    public NewUserData() {
    }

    public NewUserData(String name, String user_name, String type) {
        this.name = name;
        this.user_name = user_name;
        this.type = type;
    }

    public NewUserData(String name, String user_name, String email, String type) {
        this.name = name;
        this.user_name = user_name;
        this.email = email;
        this.type = type;
    }

    public NewUserData(String name, String user_name, String email, String password, String type) {
        this.name = name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
