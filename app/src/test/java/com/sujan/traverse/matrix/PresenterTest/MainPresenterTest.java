package com.sujan.traverse.matrix.PresenterTest;

import com.sujan.traverse.matrix.HelperClass.Position;
import com.sujan.traverse.matrix.Presenter.MainPresenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by macbookpro on 2/2/18.
 */

public class MainPresenterTest {




    @Test
    public void onClick_submit_ReturnsTrue(){
        MainPresenter.int_matrix=new int[2][2];
        MainPresenter.row=2;
        MainPresenter.col=2;
        MainPresenter.matrix=new String[][]{{"1","2"},{"3","4"}};
        MainPresenter mainPresenter=new MainPresenter();
        assertFalse(mainPresenter.onClick_submit());
    }
    @Test
    public void onClick_submit_ReturnsFalse(){
        MainPresenter.int_matrix=new int[2][2];
        MainPresenter.row=2;
        MainPresenter.col=2;
        MainPresenter.matrix=new String[][]{{"a","b"},{"c","d"}};
        MainPresenter mainPresenter=new MainPresenter();
        mainPresenter.onClick_submit();
        assertTrue(mainPresenter.onClick_submit());

    }

    @Test
    public void onTableTextChange_IsSuccess(){
        MainPresenter.int_matrix=new int[2][2];
        MainPresenter.row=2;
        MainPresenter.col=2;
        MainPresenter.matrix=new String[][]{{"1","2"},{"3","4"}};
        MainPresenter mainPresenter=new MainPresenter();
        mainPresenter.onTableTextChange(new Position(0,0),"10");
        assertEquals("10",MainPresenter.matrix[0][0]);




    }
    @Test
    public void initMatrices_IsSuccess(){
        MainPresenter.int_matrix=new int[2][2];
        MainPresenter.matrix=new String[2][2];
        MainPresenter.row=2;
        MainPresenter.col=2;

        int[][] TestMatrix=new int[][]{{0,0},{0,0}};
        String[][] Str_TestMatrix=new String[][]{{"",""},{"",""}};
        MainPresenter mainPresenter=new MainPresenter();
        mainPresenter.initMatrices();
        assertEquals(MainPresenter.int_matrix,TestMatrix);
        assertEquals(MainPresenter.matrix,Str_TestMatrix);


    }
}
