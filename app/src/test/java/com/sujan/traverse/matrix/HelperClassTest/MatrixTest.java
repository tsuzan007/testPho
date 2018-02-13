package com.sujan.traverse.matrix.HelperClassTest;

import com.sujan.traverse.matrix.Model.HelperClass.Matrix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by macbookpro on 1/30/18.
 */

public class MatrixTest {
    int sample1[][] = {{3, 4, 1, 2, 8, 6},
            {6, 1, 8, 2, 7, 4},
            {5, 9, 3, 9, 9, 5},
            {8, 4, 1, 3, 2, 6},
            {3, 7, 2, 8, 6, 4}
    };
    private int height = 5;
    private int width = 6;

    @Test
    public void MatrixConstruct_NotNull(){
        Matrix matrix = new Matrix(null);
        assertNotNull(matrix);
    }

    @Test
    public void MatrixConstruct_ReturnsMatrix() {

        Matrix matrix = new Matrix(sample1);
        assertEquals(sample1, matrix.getMgrid());

    }

    @Test
    public void MatrixConstruct_Null_ReturnsNull() {
        Matrix matrix = new Matrix(null);
        assertNull(matrix.getMgrid());

    }

    @Test
    public void getMaxHeight_ReturnsLength() {
        Matrix matrix = new Matrix(sample1);
        assertEquals(height, matrix.getMax_height());
    }

    @Test
    public void getMaxWidth_ReturnsWidth() {
        Matrix matrix = new Matrix(sample1);
        assertEquals(width, matrix.getMax_width());

    }

    @Test
    public void setMgrid_IsSuccessful() {
        Matrix matrix = new Matrix(null);
        matrix.setMgrid(sample1);
        assertEquals(sample1, matrix.getMgrid());

    }

    @Test
    public void setMgrid_Null_IsNull() {
        Matrix matrix = new Matrix(sample1);
        matrix.setMgrid(null);
        assertNull(matrix.getMgrid());


    }
}
