package edu.ifmo.web.lab3;

import java.io.Serializable;

public class HitResult extends HitAttempt implements Serializable {
    private boolean doesHit;

    public boolean isDoesHit() {
        return doesHit;
    }

    public void setDoesHit(boolean doesHit) {
        this.doesHit = doesHit;
    }
}
