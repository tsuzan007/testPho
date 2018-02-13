package com.sujan.traverse.matrix.Model;


import com.sujan.traverse.matrix.Model.HelperClass.Matrix;
import com.sujan.traverse.matrix.Model.HelperClass.Path;

import java.util.ArrayList;
import java.util.List;


public class MatrixTraverse extends Matrix {
    public static final int MAX_WIDTH_LIMIT = 20;
    public static final int MAX_HEIGHT_LIMIT = 20;
    public static final int MAX_COST = 50;
    public static int resultCost;
    public static int max_width, max_height;
    int  finalCost = 0;
    private List<String> traverseResult;
    ITraverse iTraverse;
    IMatrixValidation iMatrixValidation;


    /**
     * Constructor of the class. Initialize matrix and other parameters.
     *
     * @param mgrid matrix to traverse
     */
    public MatrixTraverse(int[][] mgrid,ITraverse iTraverse, IMatrixValidation iMatrixValidation) {
        super(mgrid);
        this.iMatrixValidation=iMatrixValidation;
        this.iTraverse=iTraverse;
        if (iMatrixValidation.isMatrix(mgrid)) {
            initialize();
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    /**
     * Initializes class variables.
     */
    private void initialize() {
        max_width = getMax_width();
        max_height = getMax_height();
        resultCost = Integer.MAX_VALUE;
        traverseResult = new ArrayList<>();

    }


    /**
     * Main method to find the path of the lowest cost.
     * Traverse along the matrix and find the path
     *
     * @return Lists of integer that represent rows traversed to find the path
     */
    public List<String> traverse() {
      if (!iMatrixValidation.isArrayUnderLimit(max_width, max_height, MAX_WIDTH_LIMIT, MAX_HEIGHT_LIMIT)) {
            traverseResult.add("Invalid Matrix");
            traverseResult.add("");
            traverseResult.add("");
            return traverseResult;
        } else {
            traverseResult = iTraverse.traverse(getMgrid());
            traverseResult.add(iMatrixValidation.isPathlessthanLimit(finalCost, MAX_COST));
            System.out.println(traverseResult.get(0));
            System.out.println(traverseResult.get(1));
            System.out.println(traverseResult.get(2));
            return traverseResult;

        }

    }



}
