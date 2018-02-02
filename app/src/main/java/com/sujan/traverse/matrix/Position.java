package com.sujan.traverse.matrix;

/**
 * Created by macbookpro on 1/26/18.
 */

public class Position {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {

        return "x->" + (x) + " y->" + (y);
    }
}