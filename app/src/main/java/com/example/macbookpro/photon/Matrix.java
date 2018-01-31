package com.example.macbookpro.photon;

/**
 * Created by macbookpro on 1/26/18.
 */

public class Matrix {
    private int[][] mgrid;

    public Matrix(int[][] mgrid) {
        this.mgrid = mgrid;
    }

    public int getMax_width() {
        return this.mgrid[0].length;
    }

    public int getMax_height() {
        return mgrid.length;
    }


    public int[][] getMgrid() {
        return mgrid;
    }

    public void setMgrid(int[][] mgrid) {
        this.mgrid = mgrid;
    }


}
