package com.sujan.traverse.matrix.PresenterTest;

import com.sujan.traverse.matrix.Model.HelperClass.Position;
import com.sujan.traverse.matrix.Presenter.MainPresenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by macbookpro on 2/2/18.
 */

public class MainPresenterTest {
    MainPresenter mainPresenter;

    @Before
    public void initMainPresenter() {
        mainPresenter = new MainPresenter();
        mainPresenter.setInt_matrix(new int[2][2]);
        mainPresenter.setRow(2);
        mainPresenter.setCol(2);

    }


    @Test
    public void onClick_submit_ReturnsTrue() {

        mainPresenter.setMatrix(new String[][]{{"1", "2"}, {"3", "4"}});
        assertFalse(mainPresenter.onClick_submit());
    }

    @Test
    public void onClick_submit_ReturnsFalse() {
        mainPresenter.setMatrix(new String[][]{{"a", "b"}, {"c", "d"}});
        mainPresenter.onClick_submit();
        assertTrue(mainPresenter.onClick_submit());

    }

    @Test
    public void onTableTextChange_IsSuccess() {
        mainPresenter.setMatrix(new String[][]{{"1", "2"}, {"3", "4"}});

        mainPresenter.onTableTextChange(new Position(0, 0), "10");
        assertEquals("10", mainPresenter.getMatrix()[0][0]);


    }

    @Test
    public void initMatrices_IsSuccess() {
        mainPresenter.setMatrix(new String[2][2]);

        int[][] TestMatrix = new int[][]{{0, 0}, {0, 0}};
        String[][] Str_TestMatrix = new String[][]{{"", ""}, {"", ""}};

        mainPresenter.initMatrices();
        assertEquals(mainPresenter.getInt_matrix(), TestMatrix);
        assertEquals(mainPresenter.getMatrix(), Str_TestMatrix);


    }

    @Test
    public void getRow_ReturnsRow() {
        Assert.assertEquals(mainPresenter.getRow(), 2);
    }

    @Test
    public void getCol_ReturnsCol() {
        Assert.assertEquals(mainPresenter.getCol(), 2);
    }

}
