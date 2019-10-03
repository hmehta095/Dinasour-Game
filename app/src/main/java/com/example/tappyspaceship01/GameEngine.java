package com.example.tappyspaceship01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // Android debug variables
    final static String TAG="DINO-RAINBOWS";

    // screen size
    int screenHeight;
    int screenWidth;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;
    Item item;
    Item item1;
    Item item2;
    Item item3;

    Player player;



    // -----------------------------------
    // GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------



    // represent the TOP LEFT CORNER OF THE GRAPHIC

    // ----------------------------
    // ## GAME STATS
    // ----------------------------


    public GameEngine(Context context, int w, int h) {
        super(context);

        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;

        item = new Item(getContext(),0,screenHeight/2-400);
        item1 = new Item(getContext(),0,screenHeight/2-200);
        item2 = new Item(getContext(),0,screenHeight/2);
        item3 = new Item(getContext(),0,screenHeight/2+200);


        this.player = new Player(getContext(), 1500, 350);


        this.printScreenInfo();
    }



    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);
    }

    private void spawnPlayer() {
        //@TODO: Start the player at the left side of screen
    }
    private void spawnEnemyShips() {
        Random random = new Random();

        //@TODO: Place the enemies in a random location

    }

    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }


    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------

    public void updatePositions() {
    }

    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,255,255,255));
            paintbrush.setColor(Color.WHITE);


            // DRAW THE PLAYER HITBOX
            // ------------------------
            // 1. change the paintbrush settings so we can see the hitbox
            paintbrush.setColor(Color.BLUE);
            paintbrush.setStyle(Paint.Style.STROKE);
            paintbrush.setStrokeWidth(5);


            // draw player graphic on screen
            canvas.drawBitmap(player.getImage(), player.getxPosition(), player.getyPosition(), paintbrush);
            // draw the player's hitbox
            canvas.drawRect(player.getHitbox(), paintbrush);

            paintbrush.setColor(Color.BLACK);

            canvas.drawRect(item.getxPosition(),item.getyPosition(),
                    item.getxPosition()+1400,item.getyPosition()+20,paintbrush);
            canvas.drawRect(item1.getxPosition(),item1.getyPosition(),
                    item1.getxPosition()+1400,item1.getyPosition()+20,paintbrush);
            canvas.drawRect(item2.getxPosition(),item2.getyPosition(),
                    item2.getxPosition()+1400,item2.getyPosition()+20,paintbrush);
            canvas.drawRect(item3.getxPosition(),item3.getyPosition(),
                    item3.getxPosition()+1400,item3.getyPosition()+20,paintbrush);

//




            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    public void setFPS() {
        try {
            gameThread.sleep(120);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------


    String fingerAction = "";

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {

        }
        else if (userAction == MotionEvent.ACTION_UP) {

        }

        return true;
    }
}
