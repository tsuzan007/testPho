package com.sujan.traverse.matrix.ModelTest;

import com.sujan.traverse.matrix.Model.HelperClass.Matrix;
import com.sujan.traverse.matrix.Model.IMatrixValidation;
import com.sujan.traverse.matrix.Model.ITraverse;
import com.sujan.traverse.matrix.Model.MatrixTraverse;
import com.sujan.traverse.matrix.Model.MatrixValidation;
import com.sujan.traverse.matrix.Model.Traverse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



public class MatrixTraverseTest {

    private int sampleT[][] =
            {{3, 4, 1, 2, 8, 6},
             {6, 1, 8, 2, 7, 4},
             {5, 9, 3, 9, 9, 5},
             {8, 4, 1, 3, 2, 6},
             {3, 7, 2, 8, 6, 4}
    };

    private  int sampleF[][] =
                    {{1, 1, 1, 1},
                     {6, 1, 8, 2, 7, 4},
                     {5, 9, 5},
                     {8, 4, 1, 3, 2, 6},
                     {3, 7, 2, 8, 6, 4}
            };

    private int sampleE[][]={{}};

    private String INVALID_MATRIX="Invalid Matrix";
    IMatrixValidation iMatrixValidation;
    ITraverse iTraverse;

    @Before
    public void initialize(){
        iMatrixValidation=mock(IMatrixValidation.class);
        iTraverse=mock(ITraverse.class);

    }

    @Test
    public void MatrixTraverseConstruct_NotNull() {
        when(iMatrixValidation.isMatrix(sampleT)).thenReturn(true);
        MatrixTraverse matrixTraverse=new MatrixTraverse(sampleT,iTraverse,iMatrixValidation);
        assertNotNull(matrixTraverse);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void MatrixTraverseConstruct_IrregularMatrix_ThrowError() {
        MatrixTraverse matrixTraverse = new MatrixTraverse(sampleT,iTraverse,iMatrixValidation);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void MatrixTraverseConstruct_ThrowsNullPointerException() {
        MatrixTraverse matrixTraverse = new MatrixTraverse(null,iTraverse,iMatrixValidation);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void traverse_RowNColZero_returnsMessage(){
        MatrixTraverse matrixTraverse=new MatrixTraverse(sampleE,iTraverse,iMatrixValidation);

    }
    @Test
    public void isMatrix_ifMatrix_ReturnsTrue(){
        IMatrixValidation IMatrixValidation =mock(IMatrixValidation.class);
        when(IMatrixValidation.isMatrix(sampleT)).thenReturn(true);
        Assert.assertTrue((IMatrixValidation).isMatrix(sampleT));
        verify(IMatrixValidation,times(1)).isMatrix(sampleT);


    }

}