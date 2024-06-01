package com.webcheckers.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("model-tier")
public class BoardTest {
    /**
     * Tests creating a board
     */
    @Test
    public void create_board() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        assertNotNull(CuT);
    }

    /**
     * Tests assigning the active player
     */
    @Test
    public void check_active_player() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        assertEquals(CuT.getActivePlayer(), PieceColor.RED);
    }

    /**
     * Tests getting the red player from the board
     */
    @Test
    public void test_get_RedPlayer() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        assertNotNull(CuT.getRedPlayer());
        assertEquals("redPlayer", CuT.getRedPlayer());
    }

    /**
     * Tests getting the white player from the board.
     */
    @Test
    public void test_get_WhitePlayer() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        assertNotNull(CuT.getWhitePlayer());
        assertEquals("whitePlayer", CuT.getWhitePlayer());
    }

    /**
     * Tests getting the 'active' board view for the red player perspective
     */
    @Test
    public void test_ActiveBoardView_redPlayer() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        assertNotNull(CuT.getActiveBoardView());
    }

    /**
     * Tests getting the 'active' board view for the white player perspective
     */
    @Test
    public void test_ActiveBoardView_whitePlayer() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.WHITE);
        assertNotNull(CuT.getActiveBoardView());
    }

    /**
     * Tests getting the 'inactive' board view for the red player perspective
     */
    @Test
    public void test_InactiveBoardView_redPlayer() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        assertNotNull(CuT.getInactiveBoardView());
    }

    /**
     * Tests getting the 'inactive' board view for the white player perspective
     */
    @Test
    public void test_InactiveBoardView_whitePlayer() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.WHITE);
        assertNotNull(CuT.getInactiveBoardView());
    }

    /**
     * Tests setting the red player as active player
     */
    @Test
    public void test_set_RedActivePlayer() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.RED);
        assertEquals(PieceColor.RED, CuT.getActivePlayer());
    }

    /**
     * Tests setting the white player as active player
     */
    @Test
    public void test_set_WhiteActivePlayer() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.WHITE);
        assertEquals(PieceColor.WHITE, CuT.getActivePlayer());
    }

    /**
     * Tests true that there is a same color piece at given position
     */
    @Test
    public void test_inactive_piece() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        //testing a position where there is a piece
        Position test = new Position(0, 1);
        assertTrue(CuT.containsInactivePiece(test));
    }

    /**
     * Tests false that there is a same color piece at given position
     */
    @Test
    public void test_inactive_piece_no_piece() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        //testing a position where there is no piece
        Position test = new Position(0, 0);
        assertFalse(CuT.containsInactivePiece(test));
    }

    /**
     * Tests false that there is a piece at given position
     */
    @Test
    public void test_pieceIsJumpable_false() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        //testing in a place where we know there is a piece
        assertFalse(CuT.noPieceInLanding(new Position(0, 1)));
    }

    /**
     * Tests true that there is a piece at given position
     */
    @Test
    public void test_pieceIsJumpable_true() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        //testing in a place where we know there is not a piece
        assertTrue(CuT.noPieceInLanding(new Position(4, 1)));
    }

    /**
     * Tests Null at given space where there is no piece
     */
    @Test
    public void test_getPieceAt_empty_space() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        //testing with a space where there is no piece
        Position noPiece = new Position(0, 0);
        assertNull(CuT.getPieceAt(noPiece));
    }

    /**
     * Tests NotNull at given space where there is a piece
     */
    @Test
    public void test_getPieceAt_space_with_piece() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        //testing with a space where there is a piece
        Position noPiece = new Position(0, 1);
        assertNotNull(CuT.getPieceAt(noPiece));
    }

    /**
     * Tests Null at given space where red player move piece from
     * Tests NotNull at given space where red player move piece to
     */
    @Test
    public void test_move_piece_redPlayer_active() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        //testing to put to a space where there is already a piece
        Position old = new Position(5, 0);
        Position now = new Position(2, 3);
        CuT.movePiece(old, now);
        assertNull(CuT.getPieceAt(old));
        assertNotNull(CuT.getPieceAt(now));
    }

    /**
     * Tests Null at given space where white player move piece from
     * Tests NotNull at given space where white player move piece to
     */
    @Test
    public void test_move_piece_whitePlayer_active() {
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.WHITE);
        //testing to put to a space where there is already a piece
        Position old = new Position(2, 3);
        Position now = new Position(5, 0);
        CuT.movePiece(old, now);
        assertNull(CuT.getPieceAt(old));
        assertNotNull(CuT.getPieceAt(now));
    }

    /**
     * Tests jumping a piece as white and checking if the jumped piece is removed by jumping forward
     */
    @Test
    public void test_jumpWithPiece_White_Forward(){
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.WHITE);

        Position old = new Position(2,3);
        Position now = new Position(4,5);
        Position rOld = new Position(2, 5);
        Position rNow = new Position(3, 4);

        // Puts a piece in the spot that will be jumped and checks if it is there
        CuT.movePiece(rOld, rNow);
        assertNotNull(CuT.getPieceAt(rNow));

        CuT.jumpWithPiece(old, now);
        assertNull(CuT.getPieceAt(rNow));
    }

    /**
     * Tests jumping a piece as white and checking if the jumped piece is removed by jumping backward
     */
    @Test
    public void test_jumpWithPiece_White_Backward(){
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.WHITE);

        Position old = new Position(4,5);
        Position now = new Position(2,3);
        Position rOld = new Position(2, 5);
        Position rNow = new Position(3, 4);

        CuT.movePiece(rOld, rNow);
        assertNotNull(CuT.getPieceAt(rNow));

        CuT.jumpWithPiece(old, now);
        assertNull(CuT.getPieceAt(rNow));

    }

    /**
     * Tests jumping a piece as red and checking if the jumped piece is removed by jumping forward
     */
    @Test
    public void test_jumpWithPiece_Red_Forward(){
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.RED);

        Position old = new Position(2,3);
        Position now = new Position(4,5);
        Position rOld = new Position(2, 5);
        Position rNow = new Position(3, 4);

        // Puts a piece in the spot that will be jumped and checks if it is there
        CuT.movePiece(rOld, rNow);
        assertNotNull(CuT.getPieceAt(rNow));

        CuT.jumpWithPiece(old, now);
        assertNull(CuT.getPieceAt(rNow));
    }
    /**
     * Tests jumping a piece as red and checking if the jumped piece is removed by jumping backward
     */
    @Test
    public void test_jumpWithPiece_Red_Backward(){
        Board CuT = new Board("redPlayer", "whitePlayer");
        CuT.setActivePlayer(PieceColor.RED);

        Position old = new Position(4,5);
        Position now = new Position(2,3);
        Position rOld = new Position(2, 5);
        Position rNow = new Position(3, 4);

        CuT.movePiece(rOld, rNow);
        assertNotNull(CuT.getPieceAt(rNow));

        CuT.jumpWithPiece(old, now);
        assertNull(CuT.getPieceAt(rNow));
    }
}
