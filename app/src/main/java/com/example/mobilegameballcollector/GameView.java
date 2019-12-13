package com.example.mobilegameballscollector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.mobilegameballcollector.Ball;

import java.util.ArrayList;

public class GameView  extends SurfaceView implements Runnable {

    Ball ball;
    //Trashcan trashcan;
    boolean alive;
    int sizeX;
    int sizeY;
    Paint paint;
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Thread gameThread;
    int score = 0;

    ArrayList<Ball> balls = new ArrayList<>();

    public GameView(Context context, int sizeX, int sizeY) {
        super(context);

        alive = true;
        this.sizeX = sizeX;
        this.sizeY = sizeY;

        ball = new Ball(context, sizeX, sizeY);
        //trashcan = new Trashcan(context, sizeX, sizeY);

        surfaceHolder = getHolder();
        paint = new Paint();

        gameThread = new Thread(this);

        gameThread.start();

    }

    @Override
    public void run() {
        while(alive){
//            frameRate();
//            update();
            draw();

            score++;

            if(score % 700 == 0){
                balls.add(new
                        Ball(getContext(), sizeX, sizeY));
            }

            if(score % 267 == 0){
                for(Ball ball: balls){
                    ball.increaseSpeed(1);
                }
            }
        }
    }

    public void draw(){
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();

            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.WHITE);
            paint.setTextSize(50);

            for(Ball ball : balls){
                canvas.drawBitmap(ball.bitmap,
                        ball.x,
                        ball.y,
                        paint);
            }


            canvas.drawText("Score: " + score,
                    canvas.getWidth() / 2 ,
                    250,
                    paint);

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }
}