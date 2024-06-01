package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests Position.java
 */
@Tag("Model-tier")
public class PositionTest {

    private final int ROW = 1;
    private final int CELL = 2;

    Position CuT;

    /**
     * Sets up the main position to be used in the testing
     */
    @BeforeEach
    public void setup(){
        CuT = new Position(ROW, CELL);
    }

    /**
     * Tests the getRow() and getCell() functions by checking
     * if what is returned equals the variables used to create the
     * Position
     */
    @Test
    public void testGetRowAndCell(){
        assertEquals(ROW, CuT.getRow());
        assertEquals(CELL, CuT.getCell());
    }

    /**
     * Tests to see if a position is on the checkers game board
     */
    @Test
    public void testOnBoard(){
        CuT = new Position(3,3);
        Position notOnBoard = new Position(9, 9);
        assertTrue(CuT.onBoard());
        assertFalse(notOnBoard.onBoard());
    }

    /**
     * Tests getting the difference of positions
     */
    @Test
    public void testGetDifference(){
        Position end = new Position(2, 3);
        Position expected = new Position(1, 1);
        Position current = Position.getDifference(CuT, end);
        boolean pass;
        if((expected.getRow() == current.getRow()) && (expected.getCell() == current.getCell())){
            pass = true;
        }else{
            pass = false;
        }
        assertTrue(pass);
    }
    /**
     * Tests toString prints the expected wording
     */
    @Test
    public void test_toString(){
        Position CuT = new Position(ROW,CELL);
        assertEquals(CuT.toString(),"Position{row = " + ROW + ", col = " + CELL + "}");
    }

    /**
     * Tests that calling equals on null returns false
     */
    @Test
    public void test_equalsNull(){
        Position CuT = new Position(ROW,CELL);
        assertFalse(CuT.equals(null));
    }

    /**
     * Tests that calling equals on a non Position returns false
     */
    @Test
    public void test_equalsNonPosition(){
        Position CuT = new Position(ROW,CELL);
        assertFalse(CuT.equals("String"));
    }
    /**
     * Tests that calling equals on a different Position returns false
     */
    @Test
    public void test_equalsDifferentPosition(){
        Position CuT = new Position(ROW,CELL);
        assertFalse(CuT.equals(new Position(ROW+1, CELL)));
    }
    /**
     * Tests that calling equals on a Position with the same value returns true
     */
    @Test
    public void test_equalsSameValue(){
        Position CuT = new Position(ROW,CELL);
        assertTrue(CuT.equals(new Position(ROW, CELL)));
    }
    /**
     * Tests that calling equals on the same Position returns true
     */
    @Test
    public void test_equalsSameObject(){
        Position CuT = new Position(ROW,CELL);
        assertTrue(CuT.equals(CuT));
    }
    /**
     * Tests hashCode associates the expected int
     */
    @Test
    public void test_hashCode(){
        Position CuT = new Position(ROW,CELL);
        assertEquals(CuT.hashCode(), Objects.hash(ROW, CELL));
    }


}
