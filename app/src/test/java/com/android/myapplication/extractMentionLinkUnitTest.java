package com.android.myapplication;

import com.geocomply.test.model.CommentResultModel;
import com.geocomply.test.util.CommentParser;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutionException;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class extractMentionLinkUnitTest {
    @Test
    public void haveOneMentionNoLink() throws ExecutionException, InterruptedException {
        String comment ="@billgates how are you?";
        CommentResultModel obj = CommentParser.extractMentionLink(comment);

        assertEquals(obj.getMentions().size(), 1);
        assertEquals(obj.getLinks().size(), 0);

        assertNotNull(obj.getMentions().get(0));
    }

    @Test
    public void haveTwoMentionNoLink() throws ExecutionException, InterruptedException {
        String comment ="@billgates do you know where is @elonmusk?";
        CommentResultModel obj = CommentParser.extractMentionLink(comment);

        assertEquals(obj.getMentions().size(), 2);
        assertEquals(obj.getLinks().size(), 0);

        assertNotNull(obj.getMentions().get(0));
        assertNotNull(obj.getMentions().get(1));

    }

    @Test
    public void haveOneLinkNoMention() throws ExecutionException, InterruptedException {
        String comment ="Olympics 2020 is happening; https://olympics.com/tokyo-2020/en/";
        CommentResultModel obj = CommentParser.extractMentionLink(comment);

        assertEquals(obj.getMentions().size(), 0);
        assertEquals(obj.getLinks().size(), 1);


        assertNotNull(obj.getLinks().get(0).getUrl());
        assertNotNull(obj.getLinks().get(0).getTitle());
    }
    @Test
    public void haveTwoLinkNoMention() throws ExecutionException, InterruptedException {
        String comment ="Olympics 2020 is happening; https://olympics.com/tokyo-2020/en/, Olympics 2020 is happening; https://olympics.com/tokyo-2020/en/";
        CommentResultModel obj = CommentParser.extractMentionLink(comment);

        assertEquals(obj.getMentions().size(), 0);
        assertEquals(obj.getLinks().size(), 2);

        assertNotNull(obj.getLinks().get(0).getUrl());
        assertNotNull(obj.getLinks().get(0).getTitle());

        assertNotNull(obj.getLinks().get(1).getUrl());
        assertNotNull(obj.getLinks().get(1).getTitle());

    }

    @Test
    public void haveNoMentionNoLink() throws ExecutionException, InterruptedException {
        String comment ="How is it going?";
        CommentResultModel obj = CommentParser.extractMentionLink(comment);

        assertEquals(obj.getMentions().size(), 0);
        assertEquals(obj.getLinks().size(), 0);
    }

    @Test
    public void haveOneMentionOneLink() throws ExecutionException, InterruptedException {
        String comment ="@billgates do you know the Olympics 2020 is happening; https://olympics.com/tokyo-2020/en/";
        CommentResultModel obj = CommentParser.extractMentionLink(comment);

        assertEquals(obj.getMentions().size(), 1);
        assertEquals(obj.getLinks().size(), 1);

        assertNotNull(obj.getMentions().get(0));

        assertNotNull(obj.getLinks().get(0).getUrl());
        assertNotNull(obj.getLinks().get(0).getTitle());
    }
}