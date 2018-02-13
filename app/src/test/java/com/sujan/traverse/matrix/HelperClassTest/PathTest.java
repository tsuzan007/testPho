package com.sujan.traverse.matrix.HelperClassTest;

import com.sujan.traverse.matrix.Model.HelperClass.Path;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by macbookpro on 2/1/18.
 */

public class PathTest {

    @Test
    public void PathConstruct_NotNull() {
        Path p = new Path("1 2 3");
        assertNotNull(p);

    }

    @Test
    public void setPath_IsSuccess() {
        Path p = new Path("1 2 3");
        p.setPath("4 5 6");
        assertEquals("4 5 6", p.getPath());
    }


    @Test
    public void getPath_ReturnsPath() {
        Path p = new Path("1 2 3");
        assertEquals("1 2 3", p.getPath());
    }

    @Test
    public void setCost_IsSuccess() {
        Path p = new Path("1 2 3");
        p.setCost(1);
        assertEquals(1, p.getCost());
    }


    @Test
    public void getCost_ReturnsCost() {
        Path p = new Path("1 2 3");
        assertEquals(0, p.getCost());
    }


}
