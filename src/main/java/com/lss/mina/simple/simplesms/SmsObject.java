package com.lss.mina.simple.simplesms;

/**
 * Created by Sean.liu on 2016/10/14.
 */
public class SmsObject {

    private String sender;
    private String receiver;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
