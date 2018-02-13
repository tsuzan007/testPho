package com.sujan.traverse.matrix.Model;

/**
 * Created by macbookpro on 1/27/18.
 */

public interface IMatrixValidation {


    boolean isMatrix(int[][] mgrid);

    boolean isArrayUnderLimit(int width, int height, int widthlimit, int heightlimit);

    String isPathlessthanLimit(int costPath, int limit);
}
