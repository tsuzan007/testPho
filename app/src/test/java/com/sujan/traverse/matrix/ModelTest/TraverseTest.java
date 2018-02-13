package com.sujan.traverse.matrix.ModelTest;

import com.sujan.traverse.matrix.Model.HelperClass.Position;
import com.sujan.traverse.matrix.Model.MatrixTraverse;
import com.sujan.traverse.matrix.Model.Traverse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by macbookpro on 2/12/18.
 */

public class TraverseTest {

    private int sampleT[][] =
            {{3, 4, 1, 2, 8, 6},
                    {6, 1, 8, 2, 7, 4},
                    {5, 9, 3, 9, 9, 5},
                    {8, 4, 1, 3, 2, 6},
                    {3, 7, 2, 8, 6, 4}
            };
    MatrixTraverse matrixTraverse;

    @Before
    public void initialize(){
        matrixTraverse=mock(MatrixTraverse.class);
        MatrixTraverse.max_width=6;
        MatrixTraverse.max_height=5;
        MatrixTraverse.resultCost=Integer.MAX_VALUE;


    }

    @Test
    public void  traverse_ReturnsResult(){
        Traverse traverse=new Traverse();
        List<String> result=traverse.traverse(sampleT);
        Assert.assertEquals(result.get(0),"16");


    }

    @Test
    public void getAdjPosition_ReturnsAdjacentPositions(){
        Traverse traverse=new Traverse();
        List<Position> list = new ArrayList<>();
        list=traverse.getAdjPosition(sampleT,0,0);
        Assert.assertEquals(list.get(0).getX(),0);
        Assert.assertEquals(list.get(0).getY(),1);
        Assert.assertEquals(list.get(1).getX(),1);
        Assert.assertEquals(list.get(1).getY(),1);
        Assert.assertEquals(list.get(2).getX(),4);
        Assert.assertEquals(list.get(2).getY(),1);

    }

    @Test
    public void getAdjPosition_ifRowAndCol_AreNeg_ReturnsAdjacentPositions(){
        Traverse traverse=new Traverse();
        List<Position> list = new ArrayList<>();
        list=traverse.getAdjPosition(sampleT,-1,-1);
        Assert.assertEquals(list.size(),0);
    }







}
