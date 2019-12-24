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

    ImageView imageIcon;
    Button leftButton;

    // Animation
    Animation animMoveToTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageIcon = (ImageView) findViewById(R.id.icon);
        leftButton = (Button) findViewById(R.id.leftButton);

        // load the animation
        animMoveToTop = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);

        // set animation listener
        animMoveToTop.setAnimationListener(this);

        // button click event
        leftButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                imageIcon.setVisibility(View.VISIBLE);

                // start the animation
                imageIcon.startAnimation(animMoveToTop);
            }
        });
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation
        // check for move animation
        if (animation == animMoveToTop) {
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