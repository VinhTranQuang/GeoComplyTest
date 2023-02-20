package com.geocomply.test.util;


import com.geocomply.test.model.CommentResultModel;
import com.geocomply.test.model.LinkModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommentParser {
    public static CommentResultModel extractMentionLink(String comment) throws ExecutionException, InterruptedException {
        CommentResultModel result = new CommentResultModel();
        // Find mentions in the comment
        Pattern mentionPattern = Pattern.compile("@(\\w+)");
        if (mentionPattern != null) {
            Matcher mentionMatcher = mentionPattern.matcher(comment);
            ArrayList<String> mentions = new ArrayList<>();
            while (mentionMatcher.find()) {
                mentions.add(mentionMatcher.group());
            }
            result.setMentions(mentions);
        }
        // Find url in the comment
        String urlRegex = "((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher webUrlMatcher = pattern.matcher(comment);
        ArrayList<LinkModel> links = new ArrayList<>();
        Future<ArrayList<LinkModel>> linksFuture = null;
        while (webUrlMatcher.find()) {
            String link = webUrlMatcher.group();
            try {
                // Fetch link title using Jsoup library
                ExecutorService es = Executors.newSingleThreadExecutor();
                linksFuture = es.submit(() -> {
                    Document doc = Jsoup.connect(link).get();
                    String title = doc.title();
                    links.add(new LinkModel(link, title));
                    return links;
                });
            } catch (Exception e) {
            }
        }
        if(linksFuture!= null && linksFuture.get()!= null) {
            ArrayList<LinkModel> linkModels;
            linkModels = linksFuture.get();
            result.setLinks(linkModels);
        }
        return result;
    }
}
