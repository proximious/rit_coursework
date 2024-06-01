package com.webcheckers.util;

import com.webcheckers.model.*;
import com.webcheckers.ui.BoardView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("util-tier")
public class ValidateTest {

    private Board board;
    private BoardView testBoardView;
    private ArrayList<Row> testRowList;
    private Row testRow;
    private ArrayList<Space> testSpaceList;
    private Space testSpace;

    private static final Position START1 = new Position(5, 2);
    private static final Position END1 = new Position(4, 1);
    private static final Position END2 = new Position(4, 3);
    private static final Position END3 = new Position(6, 1);
    private static final Position END4 = new Position(6, 3);
    private static final Move SIMPLE_LEFT = new Move(START1, END1);
    private static final Move SIMPLE_RIGHT = new Move(START1, END2);

    @BeforeEach
    public void setup(){
        board = mock(Board.class);
        testBoardView = mock(BoardView.class);
        when(board.getActiveBoardView()).thenReturn(testBoardView);

        testRowList = new ArrayList<>();
        testRow = mock(Row.class);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        when(testBoardView.getRows()).thenReturn(testRowList);

        testSpaceList = new ArrayList<>();
        testSpace = mock(Space.class);
        testSpaceList.add(testSpace);
        testSpaceList.add(testSpace);
        testSpaceList.add(testSpace);
        when(testRow.getSpaces()).thenReturn(testSpaceList);
    }

    /**
     * testing the constructor for a validation instance
     */
    @Test
    public void test_constructor() {
        Validate CuT = new Validate(board);
        assertNotNull(CuT);
    }

    /**
     * testing the getter for a board space
     */
    @Test
    public void test_getSpace() {
        Validate CuT = new Validate(board);
        int ROW = 0;
        int COL = 0;

        assertEquals(testSpace, CuT.getSpace(ROW, COL));
        verify(board).getActiveBoardView();
        verify(testBoardView).getRows();
        verify(testRow).getSpaces();
    }

    /**
     * Testing justValidateForwardSimpleMove with valid left and right moves
     */
    @Test
    public void test_ValidSimpleMove() {
        Validate CuT = new Validate(board);
        String expected = "Valid simple move";

        assertTrue(CuT.justValidateForwardSimpleMove(SIMPLE_LEFT));
        assertEquals(expected, CuT.getMessageString());

        assertTrue(CuT.justValidateForwardSimpleMove(SIMPLE_RIGHT));
        assertEquals(expected, CuT.getMessageString());
    }
    /**
     * Testing justValidateForwardSimpleMove move when the piece is going across too many columns
     */
    @Test
    public void test_SimpleMove_tooManyCols() {
        Validate CuT = new Validate(board);
        Position tooLargeEnd = new Position(4,5);
        Move tooManyCols = new Move(START1, tooLargeEnd);
        assertFalse(CuT.justValidateForwardSimpleMove(tooManyCols));

        String expected = "A simple move can only move one row and one column to the left or right";
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateForwardSimpleMove move when the piece is going across too many rows
     */
    @Test
    public void test_SimpleMove_tooManyRows() {
        Validate CuT = new Validate(board);
        Position tooLargeEnd = new Position(2,3);
        Move tooManyRows = new Move(START1, tooLargeEnd);
        assertFalse(CuT.justValidateForwardSimpleMove(tooManyRows));

        String expected = "A simple move can only move one row towards the opponent";
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateBackwardSimpleMove with valid left and right moves
     */
    @Test
    public void test_ValidBackSimpleMove() {
        Validate CuT = new Validate(board);

        Piece piece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(piece);
        when(piece.getType()).thenReturn(PieceType.KING);

        String expected = "Valid simple backwards move";

        when(board.getPieceAt(START1)).thenReturn(piece);

        Move simple_back_left = new Move(START1, END3);
        Move simple_back_right = new Move(START1, END4);
        assertTrue(CuT.justValidateBackwardSimpleMove(simple_back_left));
        assertEquals(expected, CuT.getMessageString());

        assertTrue(CuT.justValidateBackwardSimpleMove(simple_back_right));
        assertEquals(expected, CuT.getMessageString());
    }
    /**
     * Testing justValidateBackwardSimpleMove move when the piece is going across too many columns
     */
    @Test
    public void test_SimpleBackMove_tooManyCols() {
        Validate CuT = new Validate(board);
        Piece piece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(piece);
        when(piece.getType()).thenReturn(PieceType.KING);

        Position tooLargeEnd = new Position(6,5);
        Move tooManyCols = new Move(START1, tooLargeEnd);
        when(board.getPieceAt(START1)).thenReturn(piece);
        assertFalse(CuT.justValidateBackwardSimpleMove(tooManyCols));

        String expected = "A simple backwards move can only move one row and one column to the left or right";
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateBackwardSimpleMove move when the piece is going across too many rows
     */
    @Test
    public void test_SimpleBackMove_tooManyRows() {
        Validate CuT = new Validate(board);

        Piece piece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(piece);
        when(piece.getType()).thenReturn(PieceType.KING);

        Position tooLargeEnd = new Position(7,3);
        Move tooManyRows = new Move(START1, tooLargeEnd);
        when(board.getPieceAt(START1)).thenReturn(piece);
        assertFalse(CuT.justValidateBackwardSimpleMove(tooManyRows));

        String expected = "A simple backwards move can only move one row towards you";
        assertEquals(expected, CuT.getMessageString());
    }
    /**
     * Testing justValidateBackwardSimpleMove with a single piece
     */
    @Test
    public void test_SingleBackSimpleMove() {
        Validate CuT = new Validate(board);

        Piece piece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(piece);
        when(board.getPieceAt(START1)).thenReturn(piece);
        when(piece.getType()).thenReturn(PieceType.SINGLE);

        String expected = "Only King pieces can move towards you";

        Move simple_back_left = new Move(START1, END3);
        Move simple_back_right = new Move(START1, END4);
        assertFalse(CuT.justValidateBackwardSimpleMove(simple_back_left));
        assertEquals(expected, CuT.getMessageString());

        assertFalse(CuT.justValidateBackwardSimpleMove(simple_back_right));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateForwardJumpMove with valid left and right moves
     */
    @Test
    public void test_ValidForwardJumpMove() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        String expected = "Valid jump move";

        Position end_left = new Position(3, 0);
        Position end_right = new Position(3, 4);
        Move jump_left = new Move(START1, end_left);
        Move jump_right = new Move(START1, end_right);

        Position middle_left = new Position(4, 1);
        Position middle_right = new Position(4, 3);

        Piece piece = mock(Piece.class);
        when(board.getPieceAt(middle_left)).thenReturn(piece);
        when(board.getPieceAt(middle_right)).thenReturn(piece);
        when(piece.getColor()).thenReturn(PieceColor.WHITE);

        assertTrue(CuT.justValidateForwardJumpMove(jump_left));
        assertEquals(expected, CuT.getMessageString());

        assertTrue(CuT.justValidateForwardJumpMove(jump_right));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateForwardJumpMove with jumping over no piece
     */
    @Test
    public void test_ForwardJumpMoveNoPiece() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        String expected = "A jump move must be made over an opponents piece";

        Position end = new Position(3, 0);
        Move jump = new Move(START1, end);

        Position middle_left = new Position(4, 1);

        when(board.getPieceAt(middle_left)).thenReturn(null);

        assertFalse(CuT.justValidateForwardJumpMove(jump));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateForwardJumpMove with jumping over your own piece
     */
    @Test
    public void test_ForwardJumpMoveOwnPiece() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        String expected = "A jump move must be made over an opponents piece";

        Position end = new Position(3, 0);
        Move jump = new Move(START1, end);

        Position middle_left = new Position(4, 1);

        Piece piece = mock(Piece.class);
        when(board.getPieceAt(middle_left)).thenReturn(piece);
        when(piece.getColor()).thenReturn(PieceColor.RED);

        assertFalse(CuT.justValidateForwardJumpMove(jump));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateForwardJumpMove with jumping over too many rows
     */
    @Test
    public void test_ForwardJumpMoveTooManyRows() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        String expected = "A jump move can only jump over one row";

        Position end = new Position(2, 5);
        Move jump = new Move(START1, end);

        assertFalse(CuT.justValidateForwardJumpMove(jump));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateBackwardJumpMove with valid left and right moves
     */
    @Test
    public void test_ValidBackwardJumpMove() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        String expected = "Valid jump move";

        Piece movingPiece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(movingPiece);
        when(movingPiece.getType()).thenReturn(PieceType.KING);

        Position end_left = new Position(7, 0);
        Position end_right = new Position(7, 4);
        Move jump_left = new Move(START1, end_left);
        Move jump_right = new Move(START1, end_right);

        Position middle_left = new Position(6, 1);
        Position middle_right = new Position(6, 3);

        Piece piece = mock(Piece.class);
        when(board.getPieceAt(START1)).thenReturn(movingPiece);
        when(board.getPieceAt(middle_left)).thenReturn(piece);
        when(board.getPieceAt(middle_right)).thenReturn(piece);
        when(piece.getColor()).thenReturn(PieceColor.WHITE);

        assertTrue(CuT.justValidateBackwardJumpMove(jump_left));
        assertEquals(expected, CuT.getMessageString());

        assertTrue(CuT.justValidateBackwardJumpMove(jump_right));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateBackwardJumpMove with jumping over no piece
     */
    @Test
    public void test_BackwardJumpMoveNoPiece() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        Piece movingPiece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(movingPiece);
        when(movingPiece.getType()).thenReturn(PieceType.KING);

        String expected = "A jump move must be made over an opponents piece";

        Position end = new Position(7, 0);
        Move jump = new Move(START1, end);

        Position middle_left = new Position(6, 1);

        when(board.getPieceAt(middle_left)).thenReturn(null);
        when(board.getPieceAt(START1)).thenReturn(movingPiece);

        assertFalse(CuT.justValidateBackwardJumpMove(jump));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateBackwardJumpMove with jumping over your own piece
     */
    @Test
    public void test_BackwardJumpMoveOwnPiece() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        String expected = "A jump move must be made over an opponents piece";

        Piece movingPiece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(movingPiece);
        when(movingPiece.getType()).thenReturn(PieceType.KING);

        Position end = new Position(7, 0);
        Move jump = new Move(START1, end);

        Position middle_left = new Position(6, 1);

        Piece piece = mock(Piece.class);
        when(board.getPieceAt(middle_left)).thenReturn(piece);
        when(board.getPieceAt(START1)).thenReturn(movingPiece);
        when(piece.getColor()).thenReturn(PieceColor.RED);

        assertFalse(CuT.justValidateBackwardJumpMove(jump));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateBackwardJumpMove with jumping over too many rows
     */
    @Test
    public void test_BackwardJumpMoveTooManyRows() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        String expected = "A jump move can only jump over one row";

        Piece movingPiece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(movingPiece);
        when(movingPiece.getType()).thenReturn(PieceType.KING);

        Position start = new Position(2, 1);
        Position end = new Position(5, 4);
        Move jump = new Move(start, end);
        when(board.getPieceAt(start)).thenReturn(movingPiece);

        assertFalse(CuT.justValidateBackwardJumpMove(jump));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * Testing justValidateBackwardJumpMove when jumping backwards with a single piece
     */
    @Test
    public void test_BackwardJumpMoveSingle() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        String expected = "Only King pieces can move towards you";

        Piece movingPiece = mock(Piece.class);
        when(testSpace.getPiece()).thenReturn(movingPiece);
        when(movingPiece.getType()).thenReturn(PieceType.SINGLE);

        Position end = new Position(7, 0);
        Move jump = new Move(START1, end);
        when(board.getPieceAt(START1)).thenReturn(movingPiece);

        assertFalse(CuT.justValidateBackwardJumpMove(jump));
        assertEquals(expected, CuT.getMessageString());
    }

    /**
     * testing if any forward jumps available when the piece can't move forward
     */
    @Test
    public void test_forwardJumpAvailableAtEndRow() {
        Validate CuT = new Validate(board);
        Position row0 = new Position(0, 3);
        assertFalse(CuT.forwardJumpAvailable(row0));
    }

    /**
     * testing if any forward jumps available when the piece can't jump forward
     */
    @Test
    public void test_forwardJumpAvailableLandAtEndRow() {
        Validate CuT = new Validate(board);
        Position row1 = new Position(1, 2);
        assertFalse(CuT.forwardJumpAvailable(row1));
    }

    /**
     * testing if any forward jumps available when there is a left jump available
     */
    @Test
    public void test_forwardJumpAvailableValidLeft() {
        Validate CuT = new Validate(board);
        Position validLeft = new Position(4, 3);
        Position LeftDiagonal = new Position(3, 2);
        Position RightDiagonal = new Position(3, 4);
        Position LeftDiagonalEnd = new Position(2, 1);

        when(board.containsInactivePiece(RightDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(LeftDiagonalEnd)).thenReturn(true);
        assertTrue(CuT.forwardJumpAvailable(validLeft));
    }

    /**
     * testing if any forward jumps available when there is a right jump available
     */
    @Test
    public void test_forwardJumpAvailableValidRight() {
        Validate CuT = new Validate(board);
        Position validLeft = new Position(4, 3);
        Position LeftDiagonal = new Position(3, 2);
        Position RightDiagonal = new Position(3, 4);
        Position RightDiagonalEnd = new Position(2,5);

        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(RightDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(RightDiagonalEnd)).thenReturn(true);
        assertTrue(CuT.forwardJumpAvailable(validLeft));
    }

    /**
     * testing if any forward jumps available when there are two jumps available
     */
    @Test
    public void test_forwardJumpAvailableValidBoth() {
        Validate CuT = new Validate(board);
        Position validLeft = new Position(4, 3);
        Position LeftDiagonal = new Position(3, 2);
        Position RightDiagonal = new Position(3, 4);
        Position LeftDiagonalEnd = new Position(2, 1);
        Position RightDiagonalEnd = new Position(2,5);

        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(LeftDiagonalEnd)).thenReturn(true);
        when(board.containsInactivePiece(RightDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(RightDiagonalEnd)).thenReturn(true);
        assertTrue(CuT.forwardJumpAvailable(validLeft));
    }

    /**
     * testing if any backward jumps available when the piece can't move backward
     */
    @Test
    public void test_backwardJumpAvailableAtEndRow() {
        Validate CuT = new Validate(board);
        Position row7 = new Position(7, 2);
        assertFalse(CuT.backwardJumpAvailable(row7));
    }
    /**
     * testing if any backward jumps available when the piece can't jump backward
     */
    @Test
    public void test_backwardJumpAvailableLandAtEndRow() {
        Validate CuT = new Validate(board);
        Position row6 = new Position(6, 1);
        assertFalse(CuT.backwardJumpAvailable(row6));
    }

    /**
     * testing if any backward jumps available when there is a left jump available
     */
    @Test
    public void test_backwardJumpAvailableValidLeft() {
        Validate CuT = new Validate(board);
        Position validLeft = new Position(4, 3);

        Position LeftDiagonal = new Position(5, 2);
        Position RightDiagonal = new Position(5, 4);
        Position LeftDiagonalEnd = new Position(6, 1);

        when(board.containsInactivePiece(RightDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(LeftDiagonalEnd)).thenReturn(true);
        assertTrue(CuT.backwardJumpAvailable(validLeft));
    }

    /**
     * testing if any backward jumps available when there is a right jump available
     */
    @Test
    public void test_backwardJumpAvailableValidRight() {
        Validate CuT = new Validate(board);
        Position validLeft = new Position(4, 3);
        Position LeftDiagonal = new Position(5, 2);
        Position RightDiagonal = new Position(5, 4);
        Position RightDiagonalEnd = new Position(6,5);

        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(RightDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(RightDiagonalEnd)).thenReturn(true);
        assertTrue(CuT.backwardJumpAvailable(validLeft));
    }

    /**
     * testing if any backward jumps available when there are two jumps available
     */
    @Test
    public void test_backwardJumpAvailableValidBoth() {
        Validate CuT = new Validate(board);
        Position validBoth = new Position(4, 3);
        Position LeftDiagonal = new Position(5, 2);
        Position RightDiagonal = new Position(5, 4);
        Position LeftDiagonalEnd = new Position(6, 1);
        Position RightDiagonalEnd = new Position(6,5);

        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(LeftDiagonalEnd)).thenReturn(true);
        when(board.containsInactivePiece(RightDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(RightDiagonalEnd)).thenReturn(true);
        assertTrue(CuT.backwardJumpAvailable(validBoth));
    }

    /**
     * testing if any jumps are available when no jumps are available
     */
    @Test
    public void test_anyJumpsAvailableFalse() {
        Validate CuT = new Validate(board);
        when(testSpace.getPiece()).thenReturn(null);
        assertFalse(CuT.anyJumpsAvailable());
    }

    /**
     * testing if any jumps are available when a forward jump is available
     */
    @Test
    public void test_anyJumpsAvailableForwardsTrue() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        Piece active = mock(Piece.class);
        when(active.getColor()).thenReturn(PieceColor.RED);
        Piece inactive = mock(Piece.class);
        when(inactive.getColor()).thenReturn(PieceColor.WHITE);

        Space activeSpace = mock(Space.class);
        when(activeSpace.getPiece()).thenReturn(active);
        Space inactiveSpace = mock(Space.class);
        when(inactiveSpace.getPiece()).thenReturn(inactive);

        Row activeRow = mock(Row.class);
        ArrayList<Space> containsActiveSpace = new ArrayList<>();
        containsActiveSpace.add(testSpace);
        containsActiveSpace.add(testSpace);
        containsActiveSpace.add(activeSpace);
        when(activeSpace.getCellIdx()).thenReturn(2);
        when(activeRow.getSpaces()).thenReturn(containsActiveSpace);


        Row inactiveRow = mock(Row.class);
        ArrayList<Space> containsInactiveSpace = new ArrayList<>();
        containsActiveSpace.add(testSpace);
        containsActiveSpace.add(inactiveSpace);
        when(inactiveSpace.getCellIdx()).thenReturn(1);
        containsActiveSpace.add(testSpace);
        when(inactiveRow.getSpaces()).thenReturn(containsInactiveSpace);

        testRowList.add(2, inactiveRow);
        when(inactiveRow.getIndex()).thenReturn(2);
        testRowList.add(3, activeRow);
        when(activeRow.getIndex()).thenReturn(3);


        when(testSpace.getPiece()).thenReturn(null);
        Position leftDiagonal = new Position(activeRow.getIndex()-1, activeSpace.getCellIdx()-1);
        Position leftDiagonalEnd = new Position(activeRow.getIndex()-2, activeSpace.getCellIdx()-2);
        when(board.containsInactivePiece(leftDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(leftDiagonalEnd)).thenReturn(true);

        assertTrue(CuT.anyJumpsAvailable());
        verify(board).getActivePlayer();
        verify(board).containsInactivePiece(leftDiagonal);
        verify(board).noPieceInLanding(leftDiagonalEnd);
    }

    /**
     * testing if any jumps are available when a backward jump is available
     */
    @Test
    public void test_anyJumpsAvailableBackwardsTrue() {
        Validate CuT = new Validate(board);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        Piece active = mock(Piece.class);
        when(active.getColor()).thenReturn(PieceColor.RED);
        when(active.getType()).thenReturn(PieceType.KING);
        Piece inactive = mock(Piece.class);
        when(inactive.getColor()).thenReturn(PieceColor.WHITE);

        Space activeSpace = mock(Space.class);
        when(activeSpace.getPiece()).thenReturn(active);
        Space inactiveSpace = mock(Space.class);
        when(inactiveSpace.getPiece()).thenReturn(inactive);

        Row activeRow = mock(Row.class);
        ArrayList<Space> containsActiveSpace = new ArrayList<>();
        containsActiveSpace.add(testSpace);
        containsActiveSpace.add(testSpace);
        containsActiveSpace.add(activeSpace);
        when(activeSpace.getCellIdx()).thenReturn(2);
        when(activeRow.getSpaces()).thenReturn(containsActiveSpace);


        Row inactiveRow = mock(Row.class);
        ArrayList<Space> containsInactiveSpace = new ArrayList<>();
        containsActiveSpace.add(testSpace);
        containsActiveSpace.add(inactiveSpace);
        when(inactiveSpace.getCellIdx()).thenReturn(1);
        containsActiveSpace.add(testSpace);
        when(inactiveRow.getSpaces()).thenReturn(containsInactiveSpace);

        testRowList.add(4, inactiveRow);
        when(inactiveRow.getIndex()).thenReturn(4);
        testRowList.add(3, activeRow);
        when(activeRow.getIndex()).thenReturn(3);

        when(testSpace.getPiece()).thenReturn(null);
        Position leftDiagonal = new Position(activeRow.getIndex()+1, activeSpace.getCellIdx()-1);
        Position leftDiagonalEnd = new Position(activeRow.getIndex()+2, activeSpace.getCellIdx()-2);
        when(board.containsInactivePiece(leftDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(leftDiagonalEnd)).thenReturn(true);

        assertTrue(CuT.anyJumpsAvailable());
        verify(board).getActivePlayer();
        verify(board).containsInactivePiece(leftDiagonal);
        verify(board).noPieceInLanding(leftDiagonalEnd);
    }

}
