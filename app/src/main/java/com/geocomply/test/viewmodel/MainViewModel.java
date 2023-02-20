package com.geocomply.test.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import com.geocomply.test.model.CommentResultModel;
import com.geocomply.test.util.CommentParser;

import java.util.concurrent.ExecutionException;

public class MainViewModel extends ViewModel {
    public MutableLiveData<CommentResultModel> commentResultModelMutableLiveData = new MutableLiveData<>(null);

    public void extractComment(String comment){
        CommentResultModel result = null;
        try {
            result = CommentParser.extractMentionLink(comment);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(result!= null) {
            commentResultModelMutableLiveData.setValue(result);
        }
    }

}
