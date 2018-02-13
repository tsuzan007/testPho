package com.sujan.traverse.matrix.Model;

/**
 * Created by macbookpro on 2/12/18.
 */

public class MatrixValidation implements IMatrixValidation {

    /**
     * Validate if the given input is matrix of mxn form.
     * @param mgrid matrix to test
     * @return True if input is a matrix, false if input is not in mxn form.
     */
    @Override
    public boolean isMatrix(int[][] mgrid) {
        int num_of_col = mgrid[0].length;
        for (int x = 1; x < mgrid.length; x++) {
            if (num_of_col != mgrid[x].length) {
                return false;
            }
        }
        return true;
    }


    /**
     * Validate if given input is under the limit
     * @param width the width of the matrix
     * @param height the height of the matrix
     * @param widthlimit the width-limit of the matrix
     * @param heightlimit the height-limit of the matrix
     * @return True and false.
     */
    @Override
    public boolean isArrayUnderLimit(int width, int height, int widthlimit, int heightlimit) {
        if ((height <heightlimit) && (width < widthlimit)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validate if cost is less than the limit
     * @param costPath the total cost to traverse
     * @param limit the maximum cost.
     * @return "YES" if total cost is under limit, "No" if not.
     */
    @Override
    public String isPathlessthanLimit(int costPath, int limit) {
        if (costPath < limit) {
            return "Yes";
        }
        return "No";
    }
}
