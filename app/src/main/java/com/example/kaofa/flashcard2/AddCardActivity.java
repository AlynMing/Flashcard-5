package com.example.kaofa.flashcard2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string1 = ((EditText)findViewById(R.id.questionText)).getText().toString();
                String string2 = ((EditText)findViewById(R.id.answerText)).getText().toString();
                String string3 = ((EditText)findViewById(R.id.wrongAnswer1)).getText().toString();
                String string4 = ((EditText)findViewById(R.id.wrongAnswer2)).getText().toString();
                Intent data = new Intent();
                data.putExtra("String 1", string1);
                data.putExtra("String 2", string2);
                data.putExtra("String 3", string3);
                data.putExtra("String 4", string4);
                setResult(RESULT_OK, data);
                if(((TextView)findViewById(R.id.questionText)).getText().toString().equals("") ||
                        (((TextView)findViewById(R.id.answerText)).getText().toString().equals(""))){
                    Toast toast = Toast.makeText(getApplicationContext(), "Must enter both question and answer!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
                else {
                    finish();
                }
            }
        });
        String s1 = getIntent().getStringExtra("stringKey1");
        String s2 = getIntent().getStringExtra("stringKey2");
        String s3 = getIntent().getStringExtra("stringKey3");
        String s4 = getIntent().getStringExtra("stringKey4");
        ((TextView)findViewById(R.id.questionText)).setText(s1);
        ((TextView)findViewById(R.id.answerText)).setText(s2);
        ((TextView)findViewById(R.id.wrongAnswer1)).setText(s3);
        ((TextView)findViewById(R.id.wrongAnswer2)).setText(s4);

    }
}
