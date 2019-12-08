package com.example.mobilegameballcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private int collectedBalls;
    private String leftOrRight;
    private String gameChoice;

    Button leftButton;
    Button rightButton;
    TextView collectedTextView;
    TextView newRecordTextView;
    TextView youLostTextView;

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
    }

    private String getLeftOrRight() {
        String[] choices = new String[]{"left", "right"};
        int randomChoice = new Random().nextInt(choices.length);

        return choices[randomChoice];
    }

    private void checkChoice(String choice) {
        gameChoice = getLeftOrRight();

        // TODO- show animation where the ball goes- left or right basket using gameChoice value

        if (choice == gameChoice) {
         addAnotherCollectedBall();
        } else {
         showGameEnded();
        }
    }

    private void addAnotherCollectedBall() {
        collectedBalls++;

        // TODO- check for new record and make text for new record visible

        this.collectedTextView.setText(String.valueOf(collectedBalls));
    }

    private void showGameEnded() {
        // TODO- show fragment with lost game and total collected balls using collectedBalls value

        collectedBalls = 0;

        this.collectedTextView.setText(String.valueOf(collectedBalls));
        youLostTextView.setVisibility(View.VISIBLE);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            youLostTextView.setVisibility(View.INVISIBLE);
            // TODO- hide new record message too

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
