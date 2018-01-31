package com.sujan.traverse.matrix;

/**
 * Created by macbookpro on 1/27/18.
 */

public interface ElementValidation {
    /**
     * Checks if the string passed is an integer.
     * @param num String to check
     * @return True if string passed in an integer or else returns false.
     */
    boolean chkNonNumeric(String num);
}
