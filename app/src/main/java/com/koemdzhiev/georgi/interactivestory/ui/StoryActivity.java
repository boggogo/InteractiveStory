package com.koemdzhiev.georgi.interactivestory.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koemdzhiev.georgi.interactivestory.R;
import com.koemdzhiev.georgi.interactivestory.model.Page;
import com.koemdzhiev.georgi.interactivestory.model.Story;

import org.w3c.dom.Text;

import java.util.Objects;


public class StoryActivity extends Activity {
    public static final String TAG = StoryActivity.class.getSimpleName();
    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mName;
    private Page mCurrentPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.key_name));
        //good practice when extracting a key-value from an intent. name. Name...
        if(mName == null || mName.equals("")){
            mName = "friend";
        }
        Log.d(TAG, mName);
        //Toast.makeText(StoryActivity.this,name,Toast.LENGTH_LONG).show();

        mImageView = (ImageView)findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.choiceButton1);
        mChoice2 = (Button) findViewById(R.id.choiceButton2);

        loadPage(0);

    }

    private void loadPage(int choice){
         mCurrentPage = mStory.getPage(choice);
        //-------setting the ImageView to the mCurrentPage's imageId------
        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        mImageView.setImageDrawable(drawable);
        //add name if placeholder included. Wont add if no placeholder
        String pageText = mCurrentPage.getText();
        pageText = String.format(pageText,mName);

        //if this is the past page, go back to mainActivity
        if(mCurrentPage.isFinal()){
            mTextView.setText(mCurrentPage.getText());
            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("Play AGAIN");
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //same action as going back physical button
                    finish();
                }
            });
        }else {
            mTextView.setText(pageText);
            mChoice1.setText(mCurrentPage.getChoice1().getText());
            mChoice2.setText(mCurrentPage.getChoice2().getText());

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }
    }


}
