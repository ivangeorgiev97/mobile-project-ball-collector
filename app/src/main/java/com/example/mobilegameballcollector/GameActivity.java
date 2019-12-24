package com.example.mobilegameballcollector;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class GameActivity extends AppCompatActivity implements Animation.AnimationListener {

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