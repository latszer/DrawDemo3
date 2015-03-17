package com.example.tyler.drawdemo;
 import android.content.Context;
 import android.graphics.Canvas;
 import android.graphics.Color;
 import android.graphics.Paint;
 import android.view.Display;
 import android.view.View;

 public class DrawView extends View {
 int SCREEN_WIDTH = 0;
 int SCREEN_HEIGHT = 0;
 Entity player;
 Paint paint = new Paint();
 //SCALING_MOVEMENT tested values
 //10 is much too fast for player or probably CPU movement. Lots of choppiness
 //30 seems like a good speed for a "FAST" movement
 //70 seems to be a good average value maybe even need to make that a bit slower
 public DrawView(Context context) {
 super(context);
 }

 public void init() {
     player = new Entity();
     player.setBounds(SCREEN_WIDTH,SCREEN_HEIGHT);
     player.setPlayer(true);
 }
 public void updateValues(float deltaX, float deltaY, long deltaT) {
     player.updateValues(deltaX,deltaY,deltaT);
     invalidate();
 }

 public void setBounds(int x, int y) {
    SCREEN_WIDTH = x;
    SCREEN_HEIGHT = y;
 }

 public void onDraw(Canvas canvas) {
     paint.setColor(Color.BLACK);
     //Draw all the entities that we have
     canvas.drawRect((int)player.x,(int)player.y,(int)player.x+player.width,(int)player.y+player.height,paint);
 }

 }
