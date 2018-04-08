package com.shikshyaguru.shikshyaguru._6_institutions_activity.model;
/*
 * Created by Pankaj Koirala on 10/12/2017.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class InstitutionHomeIntroData {

    private String id;
    private String image_url;
    private String message_heading;
    private String message_content;

    public InstitutionHomeIntroData() {
        // For firebase
    }

    public InstitutionHomeIntroData(String image_url, String message_heading, String message_content) {
        this.image_url = image_url;
        this.message_heading = message_heading;
        this.message_content = message_content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMessage_heading() {
        return message_heading;
    }

    public void setMessage_heading(String message_heading) {
        this.message_heading = message_heading;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }
}
