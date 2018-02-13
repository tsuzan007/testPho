package com.sujan.traverse.matrix.ModelTest;

import com.sujan.traverse.matrix.Model.IMatrixValidation;
import com.sujan.traverse.matrix.Model.ITraverse;
import com.sujan.traverse.matrix.Model.MatrixTraverse;
import com.sujan.traverse.matrix.Model.MatrixValidation;
import com.sujan.traverse.matrix.Model.Traverse;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created by macbookpro on 2/12/18.
 */

public class MatrixTraverseDataTest {
    IMatrixValidation iMatrixValidation;
    ITraverse iTraverse;



    @Before
    public void initialize(){
        iMatrixValidation=new MatrixValidation();
        iTraverse=new Traverse();
    }

    @Test
    public void traverseFirst() {

        int sample1[][] = {{3, 4, 1, 2, 8, 6},
                {6, 1, 8, 2, 7, 4},
                {5, 9, 3, 9, 9, 5},
                {8, 4, 1, 3, 2, 6},
                {3, 7, 2, 8, 6, 4}
        };

        MatrixTraverse mat = new MatrixTraverse(sample1,iTraverse,iMatrixValidation);

        List<String> trv = mat.traverse();

        String trcost = trv.get(0);
        assertEquals("16", trcost);
    }


    @Test
    public void traverseSecond() {

        int sample[][] = {{19, 10, 19, 10, 19}, {21, 23, 20, 19, 12}, {20, 12, 20, 11, 10}};
        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();

        String trcost = trv.get(0);

        assertEquals("48", trcost);
    }

    @Test
    public void traverseThird() {
        int sample[][] = {{5, 8, 5, 3, 5}};

           MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);



        List<String> trv = mat.traverse();

        String trcost = trv.get(0);
        assertEquals("26", trcost);
    }

    @Test
    public void traverseFour() {
        int sample[][] = {{5},
                {8},
                {5},
                {3},
                {5}};
        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();

        String trcost = trv.get(0);

        assertEquals("3", trcost);
    }

    @Test
    public void traverseFive() {

        int sample[][] = {{69, 10, 19, 10, 19},
                {51, 23, 20, 19, 12},
                {60, 12, 20, 11, 10}};
        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();

        String trcost = trv.get(0);
        assertEquals("0", trcost);
    }

    @Test
    public void traverseSix() {
        int sample[][] = {{60, 3, 3, 6},
                {6, 3, 7, 9},
                {5, 6, 8, 3}};
        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();

        String trcost = trv.get(0);
        assertEquals("14", trcost);
    }

    @Test
    public void traverseSeven() {

        int sample[][] = {{6, 3, -5, 9},
                {-5, 2, 4, 10},
                {3, -2, 6, 10},
                {6, -1, -2, 10}};
        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();

        String trcost = trv.get(0);
        assertEquals("0", trcost);
    }

    @Test
    public void traverseEight() {

        int sample[][] = {{51, 51},
                {0, 51},
                {51, 51},
                {5, 5}
        };
        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();

        String trcost = trv.get(0);

        assertEquals("10", trcost);
    }

    @Test
    public void traversenine() {
        int sample[][] = {
                {51, 51, 51},
                {0, 51, 51},
                {51, 51, 51},
                {5, 5, 51}
        };

        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();

        String trcost = trv.get(0);
        assertEquals("10", trcost);
    }

    @Test
    public void traversenten() {
        int sample[][] = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };

        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();
        String trcost = trv.get(0);
        assertEquals("10", trcost);

    }

    @Test
    public void traverse_ReturnInvalidMatrix() {
        int sample[][] = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1,1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2, 2, 2,2, 2, 2, 2, 2, 2, 2, 2, 2, 2,2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        };

        MatrixTraverse mat = new MatrixTraverse(sample,iTraverse,iMatrixValidation);


        List<String> trv = mat.traverse();
        String trcost = trv.get(0);
        assertEquals("Invalid Matrix", trcost);

    }



}
