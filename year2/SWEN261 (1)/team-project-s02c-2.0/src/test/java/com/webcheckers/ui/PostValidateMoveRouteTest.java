package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.Tag;
import spark.Request;
import spark.Response;
import spark.Session;

import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("UI-Tier")
public class PostValidateMoveRouteTest {
    private Request request;
    private Session session;
    private Response response;
    private GameCenter gameCenter;
    private Gson gson;

    private Board board;
    private BoardView boardView;

    private static final String gameID = "JackJill";

    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        when(request.queryParams("gameID")).thenReturn(gameID);

        response = mock(Response.class);
        gameCenter = mock(GameCenter.class);
        gson = new Gson();

        board = mock(Board.class);
        boardView = mock(BoardView.class);
        when(board.getActiveBoardView()).thenReturn(boardView);
        when(gameCenter.getBoard(gameID)).thenReturn(board);
    }

    /**
     * Tests that the constructor creates an object
     */
    @Test
    public void test_constructor(){
        PostValidateMoveRoute CuT = new PostValidateMoveRoute(gson, gameCenter);
        assertNotNull(CuT);
    }

    /**
     * Tests making a valid simple move
     */
    @Test
    public void testSimpleMove(){
        PostValidateMoveRoute CuT = new PostValidateMoveRoute(gson, gameCenter);
        Position start = new Position(4, 1);
        Position end = new Position(3, 0);
        Move simpleMove = new Move(start, end);

        when(request.queryParams("actionData")).thenReturn(gson.toJson(simpleMove));
        when(session.attribute("moveArray")).thenReturn(null);
        when(session.attribute("moveTypes")).thenReturn(null);

        String returnObject = (String) CuT.handle(request, response);
        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "Valid simple move";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.INFO, returnMessage.getType() );
    }

    /**
     * Tests making a valid simple backwards move
     */
    @Test
    public void testSimpleBackMove(){
        PostValidateMoveRoute CuT = new PostValidateMoveRoute(gson, gameCenter);
        Position end = new Position(4, 1);
        Position start = new Position(3, 0);
        Move simpleMove = new Move(start, end);

        Piece piece = mock(Piece.class);
        when(piece.getType()).thenReturn(PieceType.KING);
        when(board.getPieceAt(start)).thenReturn(piece);

        when(request.queryParams("actionData")).thenReturn(gson.toJson(simpleMove));
        when(session.attribute("moveArray")).thenReturn(null);
        when(session.attribute("moveTypes")).thenReturn(null);

        String returnObject = (String) CuT.handle(request, response);
        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "Valid simple backwards move";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.INFO, returnMessage.getType() );
    }

    /**
     * Tests making a valid forward jump move
     */
    @Test
    public void testForwardJumpMove(){
        PostValidateMoveRoute CuT = new PostValidateMoveRoute(gson, gameCenter);

        Position start = new Position(4, 3);
        Position LeftDiagonal = new Position(3, 2);
        Position RightDiagonal = new Position(3, 4);
        Position end = new Position(2,5);
        Move jumpMove = new Move(start, end);

        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(RightDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(end)).thenReturn(true);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        Piece piece = mock(Piece.class);
        when(board.getPieceAt(RightDiagonal)).thenReturn(piece);
        when(piece.getColor()).thenReturn(PieceColor.WHITE);

        when(request.queryParams("actionData")).thenReturn(gson.toJson(jumpMove));
        when(session.attribute("moveArray")).thenReturn(null);
        when(session.attribute("moveTypes")).thenReturn(null);

        String returnObject = (String) CuT.handle(request, response);
        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "Valid jump move";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.INFO, returnMessage.getType() );
    }

    /**
     * Tests making a valid backward jump move
     */
    @Test
    public void testBackwardJumpMove(){
        PostValidateMoveRoute CuT = new PostValidateMoveRoute(gson, gameCenter);

        Position end = new Position(4, 3);
        Position LeftDiagonal = new Position(3, 2);
        Position RightDiagonal = new Position(3, 4);
        Position start = new Position(2,5);
        Move jumpMove = new Move(start, end);

        when(board.containsInactivePiece(LeftDiagonal)).thenReturn(false);
        when(board.containsInactivePiece(RightDiagonal)).thenReturn(true);
        when(board.noPieceInLanding(end)).thenReturn(true);
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        Piece piece = mock(Piece.class);
        when(board.getPieceAt(RightDiagonal)).thenReturn(piece);
        when(piece.getColor()).thenReturn(PieceColor.WHITE);

        Piece active = mock(Piece.class);
        when(board.getPieceAt(start)).thenReturn(active);
        when(active.getType()).thenReturn(PieceType.KING);

        when(request.queryParams("actionData")).thenReturn(gson.toJson(jumpMove));
        when(session.attribute("moveArray")).thenReturn(null);
        when(session.attribute("moveTypes")).thenReturn(null);

        String returnObject = (String) CuT.handle(request, response);
        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "Valid jump move";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.INFO, returnMessage.getType() );
    }

    /**
     * Tests making an invalid move
     */
    @Test
    public void testInvalidMove(){
        PostValidateMoveRoute CuT = new PostValidateMoveRoute(gson, gameCenter);
        Position start = new Position(4, 1);
        Position end = new Position(3, 4);
        Move simpleMove = new Move(start, end);

        when(request.queryParams("actionData")).thenReturn(gson.toJson(simpleMove));
        when(session.attribute("moveArray")).thenReturn(null);
        when(session.attribute("moveTypes")).thenReturn(null);

        String returnObject = (String) CuT.handle(request, response);
        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        String expected = "A simple move can only move one row and one column to the left or right";
        assertEquals(expected, returnMessage.getText());
        assertEquals(Message.Type.ERROR, returnMessage.getType() );
    }
}
