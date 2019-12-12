package com.example.mobilegameballcollector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class Ball  {

    public Bitmap bitmap;
    Context context;
    public int x;
    public int y;
    int maxX;
    int maxY;
    int speed = 10;

    Rect detectCollision;
    Random random = new Random();

    public Ball(Context context, int sizeX, int sizeY){
        bitmap= BitmapFactory.decodeResource(context.getResources(),
                R.drawable.football);

        this.context = context;
        maxX = sizeX - bitmap.getWidth();
        maxY = sizeY;
        x = 0;
        y = sizeY - bitmap.getHeight() - 80;

        detectCollision = new Rect(x, y,
                x + bitmap.getWidth(),
                y + bitmap.getHeight());
    }


    public void increaseSpeed(int boost) {
        speed += boost;
    }

    public void update(){
        y += speed;

        if(y > maxY){
            y = -80;
            x = random.nextInt(maxX) - bitmap.getWidth();
            detectCollision.left = x;
            detectCollision.right = x + bitmap.getWidth();
        }

        detectCollision.top = y;
        detectCollision.bottom = y + bitmap.getHeight();
    }
}

