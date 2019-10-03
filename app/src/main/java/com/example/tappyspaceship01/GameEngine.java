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
    Item item4;
    Item item5;
    Item item6;
    Item item7;

    Player player;

    int score;
    int lives = 3;



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

        item = new Item(getContext(),0,screenHeight/2-350);
        item1 = new Item(getContext(),0,screenHeight/2-150);
        item2 = new Item(getContext(),0,screenHeight/2+50);
        item3 = new Item(getContext(),0,screenHeight/2+250);



        final int min = 0;
        final int max = 500;
        int random = new Random().nextInt((max - min) + 1) + min;

        if(random < 100){
            random = 100;
        }
        else if(random>=100 || random<200){
            random =200;
        }
        else if(random>=200 || random<300){
            random =300;
        }
        item4 = new Item(getContext(),10,screenHeight/2-random);
        item5 = new Item(getContext(),10,screenHeight/2-random -200);
        item6 = new Item(getContext(),10,screenHeight/2+random);
        item7 = new Item(getContext(),10,screenHeight/2+random);


        this.player = new Player(getContext(), 1500, 200);


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



        if (this.fingerAction == "mousedown") {
            // if mousedown, then move player up
            // Make the UP movement > than down movement - this will
            // make it look like the player is moving up alot
            player.setyPosition(player.getyPosition() - 100);
            player.updateHitbox();
        }
        else if (this.fingerAction == "mouseup") {
            // if mouseup, then move player down
            player.setyPosition(player.getyPosition() + 10);
            player.updateHitbox();
        }



        this.item7.setxPosition(this.item7.getxPosition()+35);
        this.item7.updateHitbox();
        this.item6.setxPosition(this.item6.getxPosition()+15);
        this.item6.updateHitbox();
        this.item5.setxPosition(this.item5.getxPosition()+25);
        this.item5.updateHitbox();
        this.item4.setxPosition(this.item4.getxPosition()+35);
        this.item4.updateHitbox();


        if(this.item4.getxPosition() > screenWidth ){
            final int min = 0;
            final int max = 500;
            int random = new Random().nextInt((max - min) + 1) + min;
            item4 = new Item(getContext(),10,screenHeight/2-random);
        }
        if(this.item5.getxPosition() > screenWidth ){

            final int min = 0;
            final int max = 500;
            int random = new Random().nextInt((max - min) + 1) + min;
            item5 = new Item(getContext(),10,screenHeight/2-random);
        }
        if(this.item6.getxPosition() > screenWidth ){

            final int min = 0;
            final int max = 500;
            int random = new Random().nextInt((max - min) + 1) + min;
            item6 = new Item(getContext(),10,screenHeight/2-random );
        }
        if(this.item7.getxPosition() > screenWidth ){

            final int min = 0;
            final int max = 500;
            int random = new Random().nextInt((max - min) + 1) + min;
            item7 = new Item(getContext(),10,screenHeight/2-random);
        }

        if(this.player.getyPosition()<=0 || this.player.getyPosition()>= screenHeight ){
            this.player = new Player(getContext(), 1500, 200);
        }

        if (this.player.getHitbox().intersect(this.item4.getHitbox()) == true) {
            // the enemy and player are colliding

            // @TODO: What do you want to do next?


            // decrease the lives
            score = score +1;
            final int min = 0;
            final int max = 500;
            int random = new Random().nextInt((max - min) + 1) + min;
            item4 = new Item(getContext(),10,screenHeight/2-random);

        }

        if (this.player.getHitbox().intersect(this.item5.getHitbox()) == true) {
            // the enemy and player are colliding

            // @TODO: What do you want to do next?


            // decrease the lives
            lives = lives-1;
            final int min = 0;
            final int max = 500;
            int random = new Random().nextInt((max - min) + 1) + min;
            item5 = new Item(getContext(),10,screenHeight/2-random);

        }
        if (this.player.getHitbox().intersect(this.item6.getHitbox()) == true) {
            // the enemy and player are colliding

            // @TODO: What do you want to do next?


            // decrease the lives
            score = score +1;
            final int min = 0;
            final int max = 500;
            int random = new Random().nextInt((max - min) + 1) + min;
            item6 = new Item(getContext(),10,screenHeight/2-random );

        }
        if (this.player.getHitbox().intersect(this.item7.getHitbox()) == true) {
            // the enemy and player are colliding

            // @TODO: What do you want to do next?


            // decrease the lives
            score = score +1;
            final int min = 0;
            final int max = 500;
            int random = new Random().nextInt((max - min) + 1) + min;
            item7 = new Item(getContext(),10,screenHeight/2-random);

        }


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

            canvas.drawBitmap(item4.getImage(), item4.getxPosition(), item4.getyPosition(), paintbrush);
            // 2. draw the enemy's hitbox
            canvas.drawRect(item4.getHitbox(), paintbrush);
            canvas.drawBitmap(item5.getImage1(), item5.getxPosition(), item5.getyPosition(), paintbrush);
            // 2. draw the enemy's hitbox
            canvas.drawRect(item5.getHitbox(), paintbrush);
            canvas.drawBitmap(item6.getImage2(), item6.getxPosition(), item6.getyPosition(), paintbrush);
            // 2. draw the enemy's hitbox
            canvas.drawRect(item6.getHitbox(), paintbrush);
            canvas.drawBitmap(item7.getImage2(), item7.getxPosition(), item7.getyPosition(), paintbrush);
            // 2. draw the enemy's hitbox
            canvas.drawRect(item7.getHitbox(), paintbrush);


            paintbrush.setTextSize(50);
            canvas.drawText("Lives Remaining: " + lives,this.screenWidth-500,50,paintbrush);
            paintbrush.setTextSize(50);
            canvas.drawText("Score: " +this.score , 20, 50, paintbrush);



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

            fingerAction = "mousedown";
        }
        else if (userAction == MotionEvent.ACTION_UP) {

            fingerAction = "mouseup";
        }

        return true;
    }
}
