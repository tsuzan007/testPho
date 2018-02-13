package com.sujan.traverse.matrix.ModelTest;

import org.junit.Test;
import org.mockito.Mock;

import com.sujan.traverse.matrix.Model.IMatrixValidation;
import com.sujan.traverse.matrix.Model.MatrixValidation;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by macbookpro on 2/12/18.
 */

public class MatrixValidationTest {
    private int sampleT[][] =
            {{3, 4, 1, 2, 8, 6},
                    {6, 1, 8, 2, 7, 4},
                    {5, 9, 3, 9, 9, 5},
                    {8, 4, 1, 3, 2, 6},
                    {3, 7, 2, 8, 6, 4}
            };

    private int sampleF[][] =
            {{1, 1, 1, 1},
                    {6, 1, 8, 2, 7, 4},
                    {5, 9, 5},
                    {8, 4, 1, 3, 2, 6},
                    {3, 7, 2, 8, 6, 4}
            };


    @Test
    public void isMatrix_ifNotMatrix_ReturnsTrue() {
        MatrixValidation matrixValidation = new MatrixValidation();
        assertTrue(matrixValidation.isMatrix(sampleT));
    }

    @Test
    public void isMatrix_ifNotMatrix_ReturnsFalse() {
        MatrixValidation matrixValidation = new MatrixValidation();
        assertFalse(matrixValidation.isMatrix(sampleF));
    }

    @Test
    public void isArrayUnderLimit_ReturnsTrue() {
        MatrixValidation matrixValidation = new MatrixValidation();
        assertTrue(matrixValidation.isArrayUnderLimit(10, 10, 20, 20));


    }

    @Test
    public void isArrayUnderLimit_ReturnsFalse() {
        MatrixValidation matrixValidation = new MatrixValidation();
        assertFalse(matrixValidation.isArrayUnderLimit(30, 30, 20, 20));
        assertFalse(matrixValidation.isArrayUnderLimit(30, 10, 20, 20));
        assertFalse(matrixValidation.isArrayUnderLimit(10, 30, 20, 20));

    }


    @Test
    public void isPathlessthanLimit_ReturnsYes() {
        MatrixValidation matrixValidation = new MatrixValidation();
        assertEquals(matrixValidation.isPathlessthanLimit(40, 50), "Yes");

    }

    @Test
    public void isPathlessthanLimit_ReturnsNo() {
        MatrixValidation matrixValidation = new MatrixValidation();
        assertEquals(matrixValidation.isPathlessthanLimit(50, 50), "No");
        assertEquals(matrixValidation.isPathlessthanLimit(51, 50), "No");

    }


}
