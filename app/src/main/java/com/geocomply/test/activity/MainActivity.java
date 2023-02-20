package com.geocomply.test.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import com.android.myapplication.R;
import com.geocomply.test.viewmodel.MainViewModel;
import com.google.gson.Gson;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btnComment) Button btnComment;
    @BindView(R.id.edtComment) EditText edtComment;
    @BindView(R.id.tvMention) TextView tvMention;
    @BindView(R.id.tvLink) TextView tvLink;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        ButterKnife.bind(this);

        viewModel.commentResultModelMutableLiveData.observe(this, resultModel -> {
            if(resultModel!= null) {
                Gson gson = new Gson();
                if(resultModel.getMentions()!= null && resultModel.getMentions().size() >0) {
                    String mentions = gson.toJson(resultModel.getMentions());
                    tvMention.setText(mentions);
                }
                if(resultModel.getLinks()!= null && resultModel.getLinks().size()>0) {
                    String links = gson.toJson(resultModel.getLinks());
                    tvLink.setText(links);
                }
            }
        });
    }
    @OnClick(R.id.btnComment)
    public void submit(View view) {
        if (edtComment.getText().toString().length() > 0) {
            String comment = edtComment.getText().toString().trim();
            viewModel.extractComment(comment);
        }
    }
}