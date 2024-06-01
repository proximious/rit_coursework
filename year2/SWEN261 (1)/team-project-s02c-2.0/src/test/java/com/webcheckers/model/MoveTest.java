package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-Tier")
public class MoveTest {
    // Constants for use in tests
    private final int START_ROW = 1;
    private final int START_CELL = 1;
    private final int END_ROW = 3;
    private final int END_CELL = 3;

    private Position start;
    private Position end;

    private Move CuT;

    /**
     * Sets up some positions and a move
     */
    @BeforeEach
    public void setup(){
        start = new Position(START_ROW, START_CELL);
        end = new Position(END_ROW, END_CELL);
        CuT = new Move(start, end);
    }

    /**
     * tests getting the start position from a move
     */
    @Test
    public void testGetStart(){
        assertEquals(start, CuT.getStart());
    }

    /**
     * Tests getting the end position from a move
     */
    @Test
    public void testGetEnd(){
        assertEquals(end, CuT.getEnd());
    }

    /**
     * Tests getting the start row from a move
     */
    @Test
    public void testGetStartRow(){
        assertEquals(START_ROW, CuT.getStartRow());
    }

    /**
     * Tests getting the start cell from a move
     */
    @Test
    public void testGetStartCell(){
        assertEquals(START_CELL, CuT.getStartCell());
    }

    /**
     * Tests getting the end row from a move
     */
    @Test
    public void testGetEndRow(){
        assertEquals(END_ROW, CuT.getEndRow());
    }

    /**
     * Tests getting the end cell from a move
     */
    @Test
    public void testGetEndCell(){
        assertEquals(END_CELL, CuT.getEndCell());
    }

    /**
     * Tests determining the move type of a simple move
     */
    @Test
    public void testDetermineMoveTypeSimple(){
        Position start = new Position(4, 3);
        Position end = new Position(3,4);

        Move CuT = new Move(start, end);
        assertEquals(MoveType.SIMPLE_MOVE, CuT.determineMoveType());
    }
    /**
     * Tests determining the move type of a backwards simple move
     */
    @Test
    public void testDetermineMoveTypeBackwardsSimple(){
        Position end = new Position(4, 3);
        Position start = new Position(3,4);

        Move CuT = new Move(start, end);
        assertEquals(MoveType.SIMPLE_MOVE_BACK, CuT.determineMoveType());
    }
    /**
     * Tests determining the move type of a forward jump
     */
    @Test
    public void testDetermineMoveTypeJump(){
        Position start = new Position(4, 3);
        Position end = new Position(2,5);

        Move CuT = new Move(start, end);
        assertEquals(MoveType.JUMP_MOVE, CuT.determineMoveType());
    }
    /**
     * Tests determining the move type of a backward jump
     */
    @Test
    public void testDetermineMoveTypeBackwardsJump(){
        Position end = new Position(4, 3);
        Position start = new Position(2,5);

        Move CuT = new Move(start, end);
        assertEquals(MoveType.JUMP_MOVE_BACK, CuT.determineMoveType());
    }
    /**
     * Tests determining the move type of kinging a piece
     */
    @Test
    public void testDetermineMoveTypeKing(){
        Position start = new Position(1, 2);
        Position end = new Position(0,3);

        Move CuT = new Move(start, end);
        assertEquals(MoveType.KING_PIECE, CuT.determineMoveType());
    }
}
