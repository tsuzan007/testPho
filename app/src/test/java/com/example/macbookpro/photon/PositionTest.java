package com.example.macbookpro.photon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Created by macbookpro on 1/30/18.
 */

public class PositionTest {

    @Test
    public void PositionConstruct_NotNull() {
        Position p = new Position(1, 1);
        assertNotNull(p);

    }

    @Test
    public void setX_IsSuccess() {
        Position p = new Position(1, 1);
        p.setX(10);
        assertEquals(10,p.getX());
    }

    @Test
    public void setY_IsSuccess() {
        Position p = new Position(1, 1);
        p.setY(10);
        assertEquals(10,p.getY());
    }

    @Test
    public void getX_ReturnsY() {
        Position p = new Position(1, 1);
        assertEquals(1,p.getX());
    }

    @Test
    public void getY_ReturnsX() {
        Position p = new Position(1, 1);
        assertEquals(1,p.getY());
    }

    @Test
    public void toString_ReturnsString(){
        Position p = new Position(1, 1);
        assertEquals("x->" + (p.getX()) + " y->" + (p.getY()),p.toString());

    }

}
