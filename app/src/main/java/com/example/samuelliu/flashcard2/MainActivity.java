package com.example.samuelliu.flashcard2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.theEgg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#98fb98"));
                findViewById(R.id.theChicken).setBackgroundColor(Color.parseColor("#C5F2FF"));
                findViewById(R.id.neither).setBackgroundColor(Color.parseColor("#C5F2FF"));
            }
        });

        findViewById(R.id.theChicken).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.theChicken).setBackgroundColor(Color.parseColor("#ff0000"));
                findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#98fb98"));
                findViewById(R.id.neither).setBackgroundColor(Color.parseColor("#C5F2FF"));
            }
        });

        findViewById(R.id.neither).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.neither).setBackgroundColor(Color.parseColor("#ff0000"));
                findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#98fb98"));
                findViewById(R.id.theChicken).setBackgroundColor(Color.parseColor("#C5F2FF"));
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent,100);
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                String string1 = ((TextView)findViewById(R.id.flashcard_questions)).getText().toString();
                String string2 = ((TextView)findViewById(R.id.theEgg)).getText().toString();
                intent.putExtra("stringKey1", string1);
                intent.putExtra("stringKey2", string2);
                MainActivity.this.startActivityForResult(intent,100);

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 100 && data!= null){
            String string1 = data.getExtras().getString("String 1");
            String string2 = data.getExtras().getString("String 2");
            ((TextView)findViewById(R.id.flashcard_questions)).setText(string1);
            ((TextView)findViewById(R.id.theEgg)).setText(string2);
            Snackbar.make((findViewById(R.id.flashcard_questions)),"Card created!",Snackbar.LENGTH_SHORT).show();
        }

    }


}
