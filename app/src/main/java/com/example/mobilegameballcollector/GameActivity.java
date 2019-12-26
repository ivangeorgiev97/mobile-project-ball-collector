package com.example.mobilegameballcollector;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class GameActivity extends AppCompatActivity implements Animation.AnimationListener {

    private int collectedBalls;
    private int currentRecord;
    private String leftOrRight;
    private String gameChoice;


    ImageView imgmove;
    TextView collectedTextView;
    TextView newRecordTextView;
    TextView youLostTextView;

    // Animation
    Animation animMoveToTop;
    ImageView firstImgMove;
    ImageView secondImgMove;
    Button leftButton;
    Button rightButton;

    // Animation
    Animation animMoveToBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        firstImgMove = (ImageView) findViewById(R.id.firstImgMove);
        secondImgMove = (ImageView) findViewById(R.id.secondImgMove);
        leftButton = (Button) findViewById(R.id.leftButton);
        rightButton = (Button) findViewById(R.id.rightButton);


        // load the animation
        animMoveToBottom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);

        // set animation listener
        animMoveToBottom.setAnimationListener(this);

        // button click event
        leftButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                firstImgMove.setVisibility(View.VISIBLE);

                // start the animation
                firstImgMove.startAnimation(animMoveToBottom);
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                secondImgMove.setVisibility(View.VISIBLE);

                // start the animation
                secondImgMove.startAnimation(animMoveToBottom);
            }
        });

//        leftButton = findViewById(R.id.leftButton);
//        rightButton = findViewById(R.id.rightButton);
//        collectedTextView = findViewById(R.id.collectedTextView);
//        newRecordTextView = findViewById(R.id.newRecordTextView);
//        youLostTextView = findViewById(R.id.youLostTextView);
//
//        leftButton.setOnClickListener(onClickListener);
//        rightButton.setOnClickListener(onClickListener);
//
//        ImageView imgView = findViewById(R.id.firstImgMove);
//        ImageView imgView2 = findViewById(R.id.secondImgMove);
//
//        imgView.setVisibility(View.INVISIBLE);
//        imgView2.setVisibility(View.INVISIBLE);
//
//        SharedPreferences sharedPreferences =
//                getSharedPreferences("MobileGameBallCollectorRecord", MODE_PRIVATE);
//        currentRecord = sharedPreferences.getInt("record", 0);

    }
    private String getLeftOrRight() {
        String[] choices = new String[]{"left", "right"};
        int randomChoice = new Random().nextInt(choices.length);

        return choices[randomChoice];
    }

    private void checkChoice(String choice) {
        gameChoice = getLeftOrRight();

        ImageView imgView = findViewById(R.id.firstImgMove);
        ImageView imgView2 = findViewById(R.id.secondImgMove);

        imgView.setVisibility(View.INVISIBLE);
        imgView2.setVisibility(View.INVISIBLE);

        leftButton.setEnabled(false);
        rightButton.setEnabled(false);

        if (gameChoice == "left") {
            imgView.setVisibility(View.VISIBLE);
        } else {
            imgView2.setVisibility(View.VISIBLE);
        }

        rightButton.setEnabled(true);
        leftButton.setEnabled(true);

        if (choice == gameChoice) {
            addAnotherCollectedBall();
        } else {
            imgView.setVisibility(View.INVISIBLE);
            imgView2.setVisibility(View.INVISIBLE);
            showGameEnded();
        }
    }

    private void addAnotherCollectedBall() {
        collectedBalls++;

        if (collectedBalls > currentRecord) {
            currentRecord = collectedBalls;

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

    public void onFragmentInteraction(Uri uri) {
        Log.i("Tag", "onFragmentInteraction called");
    }


    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation
        // check for move animation
        if (animation == animMoveToBottom) {
            Toast.makeText(getApplicationContext(), "Animation Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub
    }


}