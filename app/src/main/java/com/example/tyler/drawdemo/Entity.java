package com.example.tyler.drawdemo;

/**
 * Created by Tyler on 3/16/2015.
 */
public class Entity {
    int SCREEN_WIDTH = 0;
    int SCREEN_HEIGHT = 0;
    float velx = 0.0f;
    float vely = 0.0f;
    float x = 300.0f;
    float y = 500.0f;
    boolean ISPLAYER = false;
    float SCREEN_BOUNCE = 0.5f;
    int width = 100;
    int height = 100;
    static int SCALING_MOVEMENT = 30;


    public boolean isPlayer() {
        return ISPLAYER;
    }

    public void updateValues(float deltaX, float deltaY, long deltaT) {
        //TODO 2. IS DONE
        //1. Take into account the deltaT such that each velocity change is equal. This is to reduce stuttering
        //2. Create Bounds such that the rectangle does not leave the canvas, give it a bounce coefficient and such like before
        //3. Change size of rectangle to better fit the size of the screen, will need to make this dynamic with screen size eventually
        //   IE: width of player rectangle ~ 0.02*width of screen or whatever

        //Calculate the new velocity by taking the accelerometer value and multiplying by the change in time (frame rate)
        //divide by value to create a usable speed
        velx -= deltaX*deltaT/SCALING_MOVEMENT;
        vely += deltaY*deltaT/SCALING_MOVEMENT;
        //Current position is old + velocity * the frame rate and divide again by the scaling factor
        x += (velx*deltaT)/SCALING_MOVEMENT;
        y += (vely*deltaT)/SCALING_MOVEMENT;
        //if the x value is less than 0 or greater than the screen width then put it in the right place and flip the x-velocity
        if(x < 0) {
            x = 0;
            velx *= -SCREEN_BOUNCE;
        }
        else if(x > SCREEN_WIDTH - width) {
            x = SCREEN_WIDTH - width;
            velx *= -SCREEN_BOUNCE;
        }
        //if the y value is less than 0 or greater than the screen height then put it in the right place and flip the y-velocity
        if(y < 0) {
            y = 0;
            vely *= -SCREEN_BOUNCE;
        }
        else if(y > SCREEN_HEIGHT - height) {
            y = SCREEN_HEIGHT - height;
            vely *= -SCREEN_BOUNCE;
        }
    }

    public void resetVelocity() {
        velx = 0;
        vely = 0;
    }

    public void setBounds(int x, int y) {
        SCREEN_WIDTH = x;
        SCREEN_HEIGHT = y;
    }

    public void setPlayer(boolean player) {
        ISPLAYER = player;
    }
}
