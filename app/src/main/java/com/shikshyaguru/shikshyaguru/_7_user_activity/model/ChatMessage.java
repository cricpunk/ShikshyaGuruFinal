package com.shikshyaguru.shikshyaguru._7_user_activity.model;

/*
 * Created by Pankaj Koirala on 4/14/2018.
 * Kathmandu, Nepal
 * Koiralapankaj007@gmail.com
 */

public class ChatMessage {

    private String sender, receiver, message;

    public ChatMessage() {
    }

    public ChatMessage(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
