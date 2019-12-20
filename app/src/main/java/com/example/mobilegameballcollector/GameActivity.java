package com.example.mobilegameballcollector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

public class GameActivity extends AppCompatActivity implements GameFragment.OnFragmentInteractionListener {

    private int collectedBalls;
    private int currentRecord;
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

        ImageView imgView = findViewById(R.id.imgmove);
        ImageView imgView2 = findViewById(R.id.imgmove2);

        imgView.setVisibility(View.INVISIBLE);
        imgView2.setVisibility(View.INVISIBLE);

        SharedPreferences sharedPreferences =
                getSharedPreferences("MobileGameBallCollectorRecord", MODE_PRIVATE);
        currentRecord = sharedPreferences.getInt("record", 0);
    }

    private String getLeftOrRight() {
        String[] choices = new String[]{"left", "right"};
        int randomChoice = new Random().nextInt(choices.length);

        return choices[randomChoice];
    }

    private void checkChoice(String choice) {
        gameChoice = getLeftOrRight();

        ImageView imgView = findViewById(R.id.imgmove);
        ImageView imgView2 = findViewById(R.id.imgmove2);

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
}
