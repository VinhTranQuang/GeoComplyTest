package com.geocomply.test.model;

import java.util.ArrayList;

public class CommentResultModel {
    private ArrayList<String> mentions;
    private ArrayList<LinkModel> links;
    public ArrayList<String> getMentions() { return mentions; }
    public void setMentions(ArrayList<String> mentions) { this.mentions = mentions; }
    public ArrayList<LinkModel> getLinks() { return links; }
    public void setLinks(ArrayList<LinkModel> links) { this.links = links; }
}
