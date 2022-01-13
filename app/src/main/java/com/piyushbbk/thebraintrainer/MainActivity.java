package com.piyushbbk.thebraintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answer=new ArrayList<Integer>();
    int locationOfTheCorrectAnswer;
    TextView resltTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    int score=0;
    int number0fQuestions=0;
    public  void playAgain(View view){
        score=0;
        number0fQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(number0fQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resltTextView.setText("");
        new CountDownTimer(30100,100){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                resltTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();

    }
    public  void chooseAnswer(View view){
        if(Integer.toString(locationOfTheCorrectAnswer).equals(view.getTag().toString())){
            resltTextView.setText("CORRECT :)");
            score++;
        }else {
            resltTextView.setText(" WRONG :(");

        }
        number0fQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(number0fQuestions));
        newQuestion();
    }
    public void start(View view)
    {
        Log.i("info","button pressed");

        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timeTextView));

    }
    public  void newQuestion()
    {
        Random rand = new Random();
        int a= rand.nextInt(21);
        int b= rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b)+"=");
        locationOfTheCorrectAnswer= rand.nextInt(4);
        answer.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfTheCorrectAnswer)
            {
                answer.add(a+b);
            }
            else{
                int wrongAnswer= rand.nextInt(41);
                while(wrongAnswer==a+b){
                    wrongAnswer= rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }

        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         sumTextView=findViewById(R.id.sumTextView);
         button0=findViewById(R.id.button0);
         button1=findViewById(R.id.button1);
         button2=findViewById(R.id.button2);
         button3=findViewById(R.id.button3);
        resltTextView=findViewById(R.id.resltTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        timerTextView=findViewById(R.id.timeTextView);
        playAgainButton=findViewById(R.id.playAgainButton);
        gameLayout=findViewById(R.id.gameLayout);
        goButton=findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}