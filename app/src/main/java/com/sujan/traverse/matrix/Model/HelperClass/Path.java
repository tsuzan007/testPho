package com.sujan.traverse.matrix.Model.HelperClass;

/**
 * Created by macbookpro on 1/26/18.
 */

public class Path implements PathCost {
    private String path;
    private int cost = 0;

    public Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public void setCost(int cost) {
        this.cost = cost;

    }
}
