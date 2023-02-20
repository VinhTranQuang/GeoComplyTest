package com.geocomply.test.model;

public class LinkModel {
    private String url;
    private String title;
    public LinkModel(String url, String title) {
        this.url = url;
        this.title = title;
    }
    public String getUrl() { return url; }
    public String getTitle() { return title; }
}
