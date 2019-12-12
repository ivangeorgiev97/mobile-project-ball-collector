package com.example.mobilegameballcollector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Trashcan {

    Context context;
    int maxX;
    int maxY;
    public int x;
    public int y;

     public Bitmap bitmap;
    Rect detectCollision;

    public Trashcan(Context context, int sizeX, int sizeY){
        bitmap= BitmapFactory.decodeResource(context.getResources(),
                R.drawable.trashcan);

        this.context = context;
        maxX = sizeX - bitmap.getWidth();
        maxY = sizeY;
        x = 0;
        y = sizeY - bitmap.getHeight() - 80;

        detectCollision = new Rect(x, y,
                x + bitmap.getWidth(),
                y + bitmap.getHeight());
    }

//    public void update(int move){
//        x += move;
//
//        if(x < 0){
//            x = 0;
//        }else if(x > maxX){
//            x = maxX;
//        }
//
//        detectCollision.left = x;
//        detectCollision.right = x + bitmap.getWidth();
//    }
}
