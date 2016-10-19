package com.lss.mina.simple.complexsms;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * Created by Administrator on 2016/10/16 0016.
 */
public class Context {
    private final IoBuffer innerBuffer;
    private String statusLine = "";
    private String sender = "";
    private String receiver = "";
    private String length = "";
    private String sms = "";

    private int matchCount = 0;
    private int line = 0;

    public Context() {
        innerBuffer = IoBuffer.allocate(100).setAutoExpand(true);
    }

    public void reset(){
        this.innerBuffer.clear();
        this.matchCount = 0;
        this.line = 0;
        this.statusLine = "";
        this.receiver = "";
        this.sender = "";
        this.length = "";
        this.sms = "";

    }

    public IoBuffer getInnerBuffer() {
        return innerBuffer;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
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

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public void setMatchCount(int matchCount) {
        this.matchCount = matchCount;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
