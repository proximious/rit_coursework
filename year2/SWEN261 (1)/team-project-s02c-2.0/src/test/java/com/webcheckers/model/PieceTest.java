package com.webcheckers.model;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UI-tier")
public class PieceTest {
    /**
     * Tests the constructor for creating the piece
     */
    @Test
    public void constructor_whiteKing() {
        Piece CuT = new Piece(PieceType.KING, PieceColor.WHITE);
        assertNotNull(CuT);
    }

    /**
     * Tests the getter function for piece color
     */
    @Test
    public void test_getColor() {
        Piece CuT = new Piece(PieceType.KING, PieceColor.WHITE);
        assertNotNull(CuT.getColor());
        assertEquals(CuT.getColor(), PieceColor.WHITE);
    }

    /**
     * Tests the getter function for piece type
     */
    @Test
    public void test_getType() {
        Piece CuT = new Piece(PieceType.SINGLE, PieceColor.RED);
        assertNotNull(CuT.getType());
        assertEquals(CuT.getType(), PieceType.SINGLE);
    }

    /**
     * Tests the setter function for piece type
     */
    @Test
    public void test_setType() {
        Piece CuT = new Piece(PieceType.SINGLE, PieceColor.RED);
        CuT.setType(PieceType.KING);
        assertNotNull(CuT.getType());
        assertEquals(PieceType.KING, CuT.getType());
    }
}
