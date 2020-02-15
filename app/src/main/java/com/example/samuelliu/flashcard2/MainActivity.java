package com.example.samuelliu.flashcard2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.flashcard_questions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_questions).setVisibility(View.INVISIBLE);
                findViewById(R.id.textView).setVisibility(View.VISIBLE);
            }
        });

        findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_questions).setVisibility(View.VISIBLE);
                findViewById(R.id.textView).setVisibility(View.INVISIBLE);
            }
        });
    }
}
