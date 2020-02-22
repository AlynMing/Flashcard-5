package com.example.samuelliu.flashcard2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.theEgg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#98fb98"));
            }
        });

        findViewById(R.id.theChicken).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.theChicken).setBackgroundColor(Color.parseColor("#ff0000"));
                findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#98fb98"));
            }
        });

        findViewById(R.id.neither).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.neither).setBackgroundColor(Color.parseColor("#ff0000"));
                findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#98fb98"));
            }
        });
    }
}
