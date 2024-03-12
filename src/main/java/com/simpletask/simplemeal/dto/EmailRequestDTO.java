package com.simpletask.simplemeal.dto;

import java.util.Arrays;

public class EmailRequestDTO {
    private String from;
    private String[] to;
    private String subject;
    private String body;

    public EmailRequestDTO() {
    }

    public EmailRequestDTO(String from, String[] to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "EmailRequestDTO{" +
                "from='" + from + '\'' +
                ", to=" + Arrays.toString(to) +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
