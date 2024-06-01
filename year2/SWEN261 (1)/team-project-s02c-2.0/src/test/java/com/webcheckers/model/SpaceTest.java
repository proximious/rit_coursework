package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class SpaceTest {

    private final int CELL_IDX = 4;
    private final SpaceColor WHITE = SpaceColor.WHITE;
    private final SpaceColor BLACK = SpaceColor.BLACK;
    public Space CuT;

    /**
     * Sets up a space
     */
    @BeforeEach
    public void setup(){
        CuT = new Space(CELL_IDX, WHITE);
        assertNotNull(CuT);
    }

    /**
     * Tests getting the cell index from the space
     */
    @Test
    public void testGetCellIdx(){
        CuT = new Space(CELL_IDX,WHITE);
        assertEquals(CELL_IDX, CuT.getCellIdx());
    }

    /**
     * Tests getting the color from the space
     */
    @Test
    public void testGetColor(){
        assertEquals(WHITE, CuT.getColor());
    }

    /**
     * Tests if the square that the space is if it is black or white
     * False for white and True for black
     */
    @Test
    public void testIsValid(){
        assertFalse(CuT.isValid());
        Space valid = new Space(CELL_IDX, BLACK);
        assertTrue(valid.isValid());
    }

    /**
     * Tests NotNull and Equals for getter function for piece's Color
     */

    @Test
    public void test_getColor(){
        Space CuT = new Space(CELL_IDX, SpaceColor.WHITE);
        assertNotNull(CuT.getColor());
        assertEquals(CuT.getColor(), SpaceColor.WHITE);
    }

    /**
     * Tests equals for setter and getter for Single White piece
     */
    @Test
    public void testSetAndGet_SingleWhite(){
        Piece piece = new Piece(PieceType.SINGLE, PieceColor.WHITE);
        CuT.setPiece(piece);
        assertEquals(CuT.getPiece(), piece);
    }

    /**
     * Tests equals for setter and getter for Single Red piece
     */
    @Test
    public void testSetAndGet_SingleRed(){
        Piece piece = new Piece(PieceType.SINGLE, PieceColor.RED);
        CuT.setPiece(piece);
        assertEquals(CuT.getPiece(), piece);
    }

    /**
     * Tests equals for setter and getter function for King White piece
     */
    @Test
    public void testSetAndGet_KingWhite(){
        Piece piece = new Piece(PieceType.KING, PieceColor.WHITE);
        CuT.setPiece(piece);
        assertEquals(CuT.getPiece(), piece);
    }

    /**
     * Tests equals for setter and getter for King Red piece
     */
    @Test
    public void testSetAndGet_KingRed(){
        Piece piece = new Piece(PieceType.KING, PieceColor.RED);
        CuT.setPiece(piece);
        assertEquals(CuT.getPiece(), piece);
    }

}
