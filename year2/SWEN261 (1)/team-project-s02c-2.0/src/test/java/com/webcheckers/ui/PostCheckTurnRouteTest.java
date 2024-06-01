package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Board;
import com.webcheckers.model.PieceColor;
import com.webcheckers.model.Player;

import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("UI-Tier")
public class PostCheckTurnRouteTest {
    private Request request;
    private Session session;
    private Response response;
    private GameCenter gameCenter;
    private Gson gson;

    private Player player;
    private Board board;

    private PostCheckTurnRoute CuT;

    private static final String GAME_ID = "JackJill";

    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        response = mock(Response.class);

        player = mock(Player.class);
        board = mock(Board.class);

        gameCenter = mock(GameCenter.class);
        gson = new Gson();

        when(request.queryParams("gameID")).thenReturn(GAME_ID);
        when(request.session()).thenReturn(session);
        when(session.attribute("currentUser")).thenReturn(player);
        when(gameCenter.getBoard(GAME_ID)).thenReturn(board);

        CuT = new PostCheckTurnRoute(gson, gameCenter);
    }

    /**
     * Tests the handle function when it is not the calling players turn yet
     */
    @Test
    public void isNotPlayersTurn(){
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);
        when(player.getColor()).thenReturn(PieceColor.WHITE);

        String returnObject = (String) CuT.handle(request, response);

        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        assertEquals(returnMessage.getType(), Message.Type.INFO);
        assertEquals(returnMessage.getText(), "false");

        verify(request, times(1)).session();
        verify(session, times(1)).attribute("currentUser");
        verify(request, times(1)).queryParams("gameID");

        verify(board, times(1)).getActivePlayer();
        verify(player, times(1)).getColor();
    }

    /**
     * Tests the handle function when it is now the calling players turn
     */
    @Test
    public void isPlayersTurn(){
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);
        when(player.getColor()).thenReturn(PieceColor.RED);

        String returnObject = (String) CuT.handle(request, response);

        gson.fromJson(returnObject, Message.class);
        Message returnMessage = gson.fromJson(returnObject, Message.class);

        assertEquals(returnMessage.getType(), Message.Type.INFO);
        assertEquals(returnMessage.getText(), "true");

        verify(request, times(1)).session();
        verify(session, times(1)).attribute("currentUser");
        verify(request, times(1)).queryParams("gameID");

        verify(board, times(1)).getActivePlayer();
        verify(player, times(1)).getColor();
    }
}
