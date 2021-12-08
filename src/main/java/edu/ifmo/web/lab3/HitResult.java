package edu.ifmo.web.lab3;

import java.io.Serializable;

public class HitResult implements Serializable {
    private float x = 0;
    private float y = 0;
    private float r = 1;
    private boolean doesHit;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public boolean isDoesHit() {
        return doesHit;
    }

    public void setDoesHit(boolean doesHit) {
        this.doesHit = doesHit;
    }
}
