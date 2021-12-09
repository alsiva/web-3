package edu.ifmo.web.lab3;

import java.io.Serializable;

public class HitResult implements Serializable {
    private double x;
    private double y;
    private double r;
    private boolean doesHit;

    public HitResult(double x, double y, double r, boolean doesHit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.doesHit = doesHit;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isDoesHit() {
        return doesHit;
    }

    public void setDoesHit(boolean doesHit) {
        this.doesHit = doesHit;
    }
}
