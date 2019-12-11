package com.example.mobilegameballcollector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.validation.TypeInfoProvider;

public class GameActivity extends AppCompatActivity {

    private int collectedBalls;
    private String leftOrRight;
    private String gameChoice;

    Button leftButton;
    Button rightButton;
    TextView collectedTextView;
    TextView newRecordTextView;
    TextView youLostTextView;

    // Screen size
    private int screenWidth;
    private int screenHeight;

    // Images
    private ImageView ballDown;

    // Position
    private float ballDownX;
    private float ballDownY;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        leftButton = findViewById(R.id.leftButton);
        rightButton = findViewById(R.id.rightButton);
        collectedTextView = findViewById(R.id.collectedTextView);
        newRecordTextView = findViewById(R.id.newRecordTextView);
        youLostTextView = findViewById(R.id.youLostTextView);

        leftButton.setOnClickListener(onClickListener);
        rightButton.setOnClickListener(onClickListener);


//        GameFragment gameFragment = new GameFragment();
//
//        FragmentManager fm = getSupportFragmentManager();
//
//        FragmentTransaction transaction = fm.beginTransaction();
//        transaction.replace(R.id.placeholder, gameFragment);
//
//        transaction.commit();

        ballDown = (ImageView)findViewById(R.id.ballDown);

        // Get Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        // Move to out of screen
        ballDown.setX(-80.0f);
        ballDown.setY(screenHeight  + 80.0f);

        // start the timer
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        },0,20);
    }

    public void changePos(){

        ballDownY += 10;
        if(ballDown.getY() > screenHeight){
            ballDownX = (float)Math.floor(Math.random() * (screenWidth- ballDown.getMaxWidth()));
            ballDownY = -100.0f;
        }
        ballDown.setX(ballDownX);
        ballDown.setY(ballDownY);
    }

    private String getLeftOrRight() {
        String[] choices = new String[]{"left", "right"};
        int randomChoice = new Random().nextInt(choices.length);

        return choices[randomChoice];
    }

    // TODO- show animation where the ball goes- left or right basket using gameChoice value
    private void checkChoice(String choice) {
        gameChoice = getLeftOrRight();


        if (choice == gameChoice) {
         addAnotherCollectedBall();
        } else {
         showGameEnded();
        }
    }

    private void addAnotherCollectedBall() {
        collectedBalls++;

        SharedPreferences sharedPreferences =
                getSharedPreferences("MobileGameBallCollectorRecord", MODE_PRIVATE);

        int record = sharedPreferences.getInt("record", 0);

        if(collectedBalls > record) {
            SharedPreferences.Editor sharedPreferencesEditor =
                    getSharedPreferences("MobileGameBallCollectorRecord", MODE_PRIVATE).edit();

            sharedPreferencesEditor.putInt("record", collectedBalls);

            sharedPreferencesEditor.apply();
            sharedPreferencesEditor.commit();

            newRecordTextView.setText("N E W  R E C O R D : " + collectedBalls);
            newRecordTextView.setVisibility(View.VISIBLE);
        }

        this.collectedTextView.setText(String.valueOf(collectedBalls));
    }

    private void showGameEnded() {
        collectedBalls = 0;

        this.collectedTextView.setText(String.valueOf(collectedBalls));
        youLostTextView.setVisibility(View.VISIBLE);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            youLostTextView.setVisibility(View.INVISIBLE);
            newRecordTextView.setVisibility(View.INVISIBLE);

            if (v.getId() == R.id.leftButton) {
                leftOrRight = "left";

                checkChoice(leftOrRight);
            } else if (v.getId() == R.id.rightButton) {
                leftOrRight = "right";

                checkChoice(leftOrRight);
            }

        }
    };

}
