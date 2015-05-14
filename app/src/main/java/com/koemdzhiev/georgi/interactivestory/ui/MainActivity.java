package com.koemdzhiev.georgi.interactivestory.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.koemdzhiev.georgi.interactivestory.R;


public class MainActivity extends Activity {
    private EditText mNameField;
    private Button startButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mNameField = (EditText) findViewById(R.id.nameEditText);
        this.startButton = (Button) findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameField.getText().toString();

                //good to know! About using this  with MainActivity
                //Toast.makeText(MainActivity.this,name,Toast.LENGTH_LONG).show();
                startStory(name);

            }
        });
    }

    private void startStory(String name){
        Intent intent = new Intent(MainActivity.this,StoryActivity.class);
        intent.putExtra(getString(R.string.key_name),name);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mNameField.setText("");
    }
}
