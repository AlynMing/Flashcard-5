package com.example.samuelliu.flashcard2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    FlashcardDatabase flashcardDatabase;
    int currentCardDisplayedIndex = 0;
    List<Flashcard> allFlashcards;
    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();
        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_questions)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.theEgg)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.theChicken)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.neither)).setText(allFlashcards.get(0).getWrongAnswer2());
        }
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
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });

        findViewById(R.id.hide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.theChicken).setVisibility(View.INVISIBLE);
                findViewById(R.id.theEgg).setVisibility(View.INVISIBLE);
                findViewById(R.id.neither).setVisibility(View.INVISIBLE);
                findViewById(R.id.hide).setVisibility(View.INVISIBLE);
                findViewById(R.id.open).setVisibility(View.VISIBLE);
                findViewById(R.id.theChicken).setBackgroundColor(Color.parseColor("#C5F2FF"));
                findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#C5F2FF"));
                findViewById(R.id.neither).setBackgroundColor(Color.parseColor("#C5F2FF"));
            }
        });

        findViewById(R.id.open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.theChicken).setVisibility(View.VISIBLE);
                findViewById(R.id.theEgg).setVisibility(View.VISIBLE);
                findViewById(R.id.neither).setVisibility(View.VISIBLE);
                findViewById(R.id.hide).setVisibility(View.VISIBLE);
                findViewById(R.id.open).setVisibility(View.INVISIBLE);
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                String string1 = ((TextView) findViewById(R.id.flashcard_questions)).getText().toString();
                String string2 = ((TextView) findViewById(R.id.theEgg)).getText().toString();
                String string3 = ((TextView) findViewById(R.id.theChicken)).getText().toString();
                String string4 = ((TextView) findViewById(R.id.neither)).getText().toString();
                intent.putExtra("stringKey1", string1);
                intent.putExtra("stringKey2", string2);
                intent.putExtra("stringKey3", string3);
                intent.putExtra("stringKey4", string4);
                MainActivity.this.startActivityForResult(intent, 100);

            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex > allFlashcards.size()-1) {
                    currentCardDisplayedIndex = 0;
                }
                int randNum = getRandomNumber(0,currentCardDisplayedIndex);
                ((TextView) findViewById(R.id.flashcard_questions)).setText(allFlashcards.get(randNum).getQuestion());
                ((TextView) findViewById(R.id.theEgg)).setText(allFlashcards.get(randNum).getAnswer());
                ((TextView) findViewById(R.id.theChicken)).setText(allFlashcards.get(randNum).getWrongAnswer1());
                ((TextView) findViewById(R.id.neither)).setText(allFlashcards.get(randNum).getWrongAnswer2());
                findViewById(R.id.theChicken).setBackgroundColor(Color.parseColor("#C5F2FF"));
                findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#C5F2FF"));
                findViewById(R.id.neither).setBackgroundColor(Color.parseColor("#C5F2FF"));


            }
        });

        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_questions)).getText().toString());
                allFlashcards = flashcardDatabase.getAllCards();
                currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }
                if(allFlashcards.size() == 0) {
                    ((TextView) findViewById(R.id.flashcard_questions)).setText("No cards left. Please add a card");
                    ((TextView) findViewById(R.id.theEgg)).setText("No cards left.");
                    ((TextView) findViewById(R.id.theChicken)).setText("No cards left.");
                    ((TextView) findViewById(R.id.neither)).setText("No cards left.");
                }
                else{
                    ((TextView)findViewById(R.id.flashcard_questions)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView)findViewById(R.id.theEgg)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    ((TextView)findViewById(R.id.theChicken)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                    ((TextView)findViewById(R.id.neither)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                }
            }

        });

        //Samuel told me


    }
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        if(requestCode == 100 && data!= null){
            String string1 = data.getExtras().getString("String 1");
            String string2 = data.getExtras().getString("String 2");
            String string3 = data.getExtras().getString("String 3");
            String string4 = data.getExtras().getString("String 4");
            ((TextView)findViewById(R.id.flashcard_questions)).setText(string1);
            ((TextView)findViewById(R.id.theEgg)).setText(string2);
            ((TextView)findViewById(R.id.theChicken)).setText(string3);
            ((TextView)findViewById(R.id.neither)).setText(string4);
            Snackbar.make((findViewById(R.id.flashcard_questions)),"Card created!",Snackbar.LENGTH_SHORT).show();
            flashcardDatabase.insertCard(new Flashcard(string1, string2, string3, string4));
        }
        allFlashcards = flashcardDatabase.getAllCards();
    }



}
