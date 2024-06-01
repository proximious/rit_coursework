package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.model.PieceColor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.Mockito.*;

@Tag("UI-tier")
public class PostSignOutRouteTest {

    private PostSignOutRoute CuT;

    private TemplateEngine templateEngine;
    private TemplateEngineTester helper;
    private Request request;
    private Session session;
    private Response response;
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;

    private Player Red;
    private Player White;
    private Board board;

    /**
     * Sets up mocks for testing
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        response = mock(Response.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        White = mock(Player.class);
        Red = mock(Player.class);
        templateEngine = mock(TemplateEngine.class);
        helper = new TemplateEngineTester();
        gameCenter = mock(GameCenter.class);
        playerLobby = mock(PlayerLobby.class);

        CuT = new PostSignOutRoute(playerLobby, gameCenter, templateEngine);
    }

    /**
     * Tests the sign out feature outside of a game
     */
    @Test
    public void testSignOut(){
        when(Red.getName()).thenReturn("Red");
        when(session.attribute("currentUser")).thenReturn(Red);
        when(gameCenter.getBoard("RedWhite")).thenReturn(board);
        when(playerLobby.isPlayerInLobby(Red)).thenReturn(true);
        CuT.handle(request, response);
        verify(response, times(1)).redirect(WebServer.HOME_URL);
    }

    /**
     * Testing signing out during game using the 1st board id
     */
    @Test
    public void testSignOutDuringGameFirstID(){
        board = mock(Board.class);
        String redName = "Red";
        String whiteName = "White";
        when(session.attribute("currentUser")).thenReturn(Red);
        when(Red.getName()).thenReturn(redName);
        when(gameCenter.isInGame(redName)).thenReturn(true);

        when(gameCenter.getOpponent(redName)).thenReturn(whiteName);
        when(playerLobby.getPlayer(whiteName)).thenReturn(White);
        when(White.getName()).thenReturn(whiteName);

        String boardID = redName + whiteName;
        when(gameCenter.getBoard(boardID)).thenReturn(board);
        when(board.getRedPlayer()).thenReturn(redName);
        when(board.getWhitePlayer()).thenReturn(whiteName);
        when(playerLobby.getPlayer(redName)).thenReturn(Red);
        when(playerLobby.getPlayer(whiteName)).thenReturn(White);

        BoardView boardView = mock(BoardView.class);
        when(board.getActiveBoardView()).thenReturn(boardView);

        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(helper.makeAnswer());
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        PostSignOutRoute CuT = new PostSignOutRoute(playerLobby, gameCenter, templateEngine);
        CuT.handle(request, response);

        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();
        helper.assertViewModelAttribute("title", "Game");
        helper.assertViewModelAttribute("currentUser", Red);
        helper.assertViewModelAttribute("redPlayer", Red);
        helper.assertViewModelAttribute("whitePlayer", White);
        helper.assertViewModelAttribute("activeColor", PieceColor.RED);
        helper.assertViewModelAttribute("viewMode", ViewMode.PLAY);
    }

    /**
     * Testing signing out during game using the 2nd board id
     */
    @Test
    public void testSignOutDuringGameSecondID(){
        board = mock(Board.class);
        String redName = "Red";
        String whiteName = "White";
        when(session.attribute("currentUser")).thenReturn(Red);
        when(Red.getName()).thenReturn(redName);
        when(gameCenter.isInGame(redName)).thenReturn(true);

        when(gameCenter.getOpponent(redName)).thenReturn(whiteName);
        when(playerLobby.getPlayer(whiteName)).thenReturn(White);
        when(White.getName()).thenReturn(whiteName);

        String boardID1 = redName + whiteName;
        String boardID2 = whiteName + redName;
        when(gameCenter.getBoard(boardID1)).thenReturn(null);
        when(gameCenter.getBoard(boardID2)).thenReturn(board);
        when(board.getRedPlayer()).thenReturn(redName);
        when(board.getWhitePlayer()).thenReturn(whiteName);
        when(playerLobby.getPlayer(redName)).thenReturn(Red);
        when(playerLobby.getPlayer(whiteName)).thenReturn(White);

        BoardView boardView = mock(BoardView.class);
        when(board.getActiveBoardView()).thenReturn(boardView);

        when(templateEngine.render(any(ModelAndView.class))).thenAnswer(helper.makeAnswer());
        when(board.getActivePlayer()).thenReturn(PieceColor.RED);

        PostSignOutRoute CuT = new PostSignOutRoute(playerLobby, gameCenter, templateEngine);
        CuT.handle(request, response);

        helper.assertViewModelExists();
        helper.assertViewModelIsaMap();
        helper.assertViewModelAttribute("title", "Game");
        helper.assertViewModelAttribute("currentUser", Red);
        helper.assertViewModelAttribute("redPlayer", Red);
        helper.assertViewModelAttribute("whitePlayer", White);
        helper.assertViewModelAttribute("activeColor", PieceColor.RED);
        helper.assertViewModelAttribute("viewMode", ViewMode.PLAY);
    }

}
