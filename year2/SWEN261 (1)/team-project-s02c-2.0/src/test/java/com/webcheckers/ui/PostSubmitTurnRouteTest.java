package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("UI-Tier")
public class PostSubmitTurnRouteTest {
    private Request request;
    private Session session;
    private Response response;
    private GameCenter gameCenter;
    private Gson gson;
    private ArrayList<Move>moves;
    private ArrayList<MoveType>moveTypes;
    private Board board;

    private PostSubmitTurnRoute CuT;

    private static final String GAME_ID = "JackJill";

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);

        board = mock(Board.class);
        gameCenter = mock(GameCenter.class);
        gson = new Gson();

        moves = new ArrayList<>();
        moveTypes = new ArrayList<>();

        CuT = new PostSubmitTurnRoute(gson, gameCenter);

        when(request.queryParams("gameID")).thenReturn(GAME_ID);
        when(request.session()).thenReturn(session);

        when(session.attribute("moveArray")).thenReturn(moves);
        when(session.attribute("moveTypes")).thenReturn(moveTypes);
        when(gameCenter.getBoard(GAME_ID)).thenReturn(board);
    }

    /**
     * Tests making a jump move when more jumps are available
     */
    @Test
    public void test_StopDuringJump(){
        when(session.attribute("moveArray")).thenReturn(moves);
        when(session.attribute("moveTypes")).thenReturn(moveTypes);
        moveTypes.add(MoveType.JUMP_MOVE);
        moveTypes.add(MoveType.JUMP_MOVE);

        Move move = mock(Move.class);
        moves.add(move);
        moves.add(move);

        Position end = new Position(4,3);

        Position LeftDiagonal = new Position(3, 2);
        Position RightDiagonal = new Position(3, 4);
        Position LeftDiagonalEnd = new Position(2, 1);

        when(board.containsInactivePiece(RightDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(LeftDiagonalEnd)).thenReturn(true);

        when(move.getEnd()).thenReturn(end);
        String returnObject = (String) CuT.handle(request, response);

        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected ="A turn can not be submitted while more jumps are available with the same piece";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.ERROR, returnMessage.getType() );

    }

    /**
     * Tests making a simple move when a jump is available
     */
    @Test
    public void test_SimpleMoveJumpAvailable(){
        when(session.attribute("moveArray")).thenReturn(moves);
        when(session.attribute("moveTypes")).thenReturn(moveTypes);

        moveTypes.add(MoveType.SIMPLE_MOVE);
        moves.add(mock(Move.class));

        Space testSpace = mock(Space.class);
        ArrayList<Row> testRowList = new ArrayList<>();
        Row testRow = mock(Row.class);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);

        BoardView testBoardView = mock(BoardView.class);
        when(testBoardView.getRows()).thenReturn(testRowList);
        when(board.getActiveBoardView()).thenReturn(testBoardView);

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

        String returnObject = (String) CuT.handle(request, response);

        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "A simple move can not be made when a jump is available";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.ERROR, returnMessage.getType() );
    }

    /**
     * Tests making a valid simple move as the red player
     */
    @Test
    public void test_ValidSimpleMoveRed(){
        when(session.attribute("moveArray")).thenReturn(moves);
        when(session.attribute("moveTypes")).thenReturn(moveTypes);

        moveTypes.add(MoveType.SIMPLE_MOVE);
        moves.add(mock(Move.class));

        Space testSpace = mock(Space.class);
        ArrayList<Row> testRowList = new ArrayList<>();
        Row testRow = mock(Row.class);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);

        BoardView testBoardView = mock(BoardView.class);
        when(testBoardView.getRows()).thenReturn(testRowList);
        when(board.getActiveBoardView()).thenReturn(testBoardView);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        when(testSpace.getPiece()).thenReturn(null);

        String returnObject = (String) CuT.handle(request, response);

        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "Turn has been submitted";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.INFO, returnMessage.getType() );
    }

    /**
     * Tests making a valid simple move as the white player
     */
    @Test
    public void test_ValidSimpleMoveWhite(){
        when(session.attribute("moveArray")).thenReturn(moves);
        when(session.attribute("moveTypes")).thenReturn(moveTypes);

        moveTypes.add(MoveType.SIMPLE_MOVE);
        moves.add(mock(Move.class));

        Space testSpace = mock(Space.class);
        ArrayList<Row> testRowList = new ArrayList<>();
        Row testRow = mock(Row.class);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);

        BoardView testBoardView = mock(BoardView.class);
        when(testBoardView.getRows()).thenReturn(testRowList);
        when(board.getActiveBoardView()).thenReturn(testBoardView);
        when(board.getActivePlayer()).thenReturn(PieceColor.WHITE);

        when(testSpace.getPiece()).thenReturn(null);

        String returnObject = (String) CuT.handle(request, response);

        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "Turn has been submitted";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.INFO, returnMessage.getType() );
    }

    /**
     * Tests making a simple move as the second move in the turn
     */
    @Test
    public void test_SimpleMoveSecond(){
        when(session.attribute("moveArray")).thenReturn(moves);
        when(session.attribute("moveTypes")).thenReturn(moveTypes);

        moveTypes.add(MoveType.SIMPLE_MOVE);
        moves.add(mock(Move.class));
        moveTypes.add(MoveType.SIMPLE_MOVE);
        moves.add(mock(Move.class));

        Space testSpace = mock(Space.class);
        ArrayList<Row> testRowList = new ArrayList<>();
        Row testRow = mock(Row.class);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);
        testRowList.add(testRow);

        BoardView testBoardView = mock(BoardView.class);
        when(testBoardView.getRows()).thenReturn(testRowList);
        when(board.getActiveBoardView()).thenReturn(testBoardView);
        when(board.getActivePlayer()).thenReturn(PieceColor.WHITE);

        when(testSpace.getPiece()).thenReturn(null);

        String returnObject = (String) CuT.handle(request, response);
        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "A simple move can only be made as the first move in a turn";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.ERROR, returnMessage.getType() );
    }

    /**
     * Tests making a valid jump move
     */
    @Test
    public void test_ValidJump(){
        when(session.attribute("moveArray")).thenReturn(moves);
        when(session.attribute("moveTypes")).thenReturn(moveTypes);

        moveTypes.add(MoveType.JUMP_MOVE);
        Move move = mock(Move.class);
        moves.add(move);

        Position start = new Position(4, 3);
        when(move.getStart()).thenReturn(start);

        Position LeftDiagonal = new Position(3, 2);
        Position RightDiagonal = new Position(3, 4);
        Position RightDiagonalEnd = new Position(2,5);
        when(move.getEnd()).thenReturn(RightDiagonalEnd);

        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(RightDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(RightDiagonalEnd)).thenReturn(true);


        String returnObject = (String) CuT.handle(request, response);

        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "Turn has been submitted";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.INFO, returnMessage.getType() );
    }

    /**
     * Tests making an invalid jump move
     */
    @Test
    public void test_InvalidJump(){
        when(session.attribute("moveArray")).thenReturn(moves);
        when(session.attribute("moveTypes")).thenReturn(moveTypes);

        moveTypes.add(MoveType.JUMP_MOVE);
        Move move = mock(Move.class);
        moves.add(move);

        Position start = new Position(4, 3);
        when(move.getStart()).thenReturn(start);

        Position LeftDiagonal = new Position(3, 2);
        Position RightDiagonal = new Position(3, 4);
        Position RightDiagonalEnd = new Position(2,5);
        when(move.getEnd()).thenReturn(RightDiagonalEnd);

        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(RightDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(RightDiagonalEnd)).thenReturn(false);

        String returnObject = (String) CuT.handle(request, response);

        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "The jump you were trying to make is no longer valid";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.ERROR, returnMessage.getType() );
    }
}
