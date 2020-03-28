package com.example.kaofa.flashcard2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    private void startTimer() {
        countDownTimer.cancel();
        countDownTimer.start();
    }
    FlashcardDatabase flashcardDatabase;
    int currentCardDisplayedIndex = 0;
    List<Flashcard> allFlashcards;
    public int getRandomNumber(int minNumber, int maxNumber) {
        Random rand = new Random();
        return rand.nextInt((maxNumber - minNumber) + 1) + minNumber;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startTimer();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();
        countDownTimer = new CountDownTimer(16000, 1000) {
            public void onTick(long millisUntilFinished) {
                ((TextView) findViewById(R.id.timer)).setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
            }
        };
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
                overridePendingTransition(R.anim.right_in, R.anim.left_in);
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
                overridePendingTransition(R.anim.right_in, R.anim.left_in);

            }
        });

        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(),R.anim.left_in);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);
                findViewById(R.id.flashcard_questions).startAnimation(leftOutAnim);
                findViewById(R.id.theChicken).startAnimation(leftOutAnim);
                findViewById(R.id.theEgg).startAnimation(leftOutAnim);
                findViewById(R.id.neither).startAnimation(leftOutAnim);
                leftOutAnim.setAnimationListener(new Animation.AnimationListener(){
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // this method is called when the animation first starts
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // this method is called when the animation is finished playing
                        int localSize = allFlashcards.size()-1;
                        if(localSize<0)localSize=0;
                        currentCardDisplayedIndex = getRandomNumber(0,localSize);
                        if (currentCardDisplayedIndex > allFlashcards.size()-1) {
                            currentCardDisplayedIndex = 0;
                        }

                        if(allFlashcards.size()!=0){
                            ((TextView) findViewById(R.id.flashcard_questions)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                            ((TextView) findViewById(R.id.theEgg)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                            ((TextView) findViewById(R.id.theChicken)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                            ((TextView) findViewById(R.id.neither)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                        }

                        findViewById(R.id.theChicken).setBackgroundColor(Color.parseColor("#C5F2FF"));
                        findViewById(R.id.theEgg).setBackgroundColor(Color.parseColor("#C5F2FF"));
                        findViewById(R.id.neither).setBackgroundColor(Color.parseColor("#C5F2FF"));

                        findViewById(R.id.flashcard_questions).startAnimation(rightInAnim);
                        findViewById(R.id.theChicken).startAnimation(rightInAnim);
                        findViewById(R.id.theEgg).startAnimation(rightInAnim);
                        findViewById(R.id.neither).startAnimation(rightInAnim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // we don't need to worry about this method
                    }
                });




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

//hi

}
