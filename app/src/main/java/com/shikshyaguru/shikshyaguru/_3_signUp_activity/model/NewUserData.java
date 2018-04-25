package com.shikshyaguru.shikshyaguru._3_signUp_activity.model;

/*
 * Created by Pankaj Koirala on 4/3/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */
public class NewUserData {

    private String name, user_name, email, password, image;
    private int type;

    public NewUserData() {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
