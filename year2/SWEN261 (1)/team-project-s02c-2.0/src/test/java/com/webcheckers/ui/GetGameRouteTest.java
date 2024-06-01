package com.webcheckers.ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobbyExceptions;
import com.webcheckers.model.Board;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import spark.HaltException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.util.HashMap;

/**
 * The unit test suite for the {@link GetGameRoute} component.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
@Tag("UI-tier")
public class GetGameRouteTest {

    /**
     * The component-under-test (CuT).
     */
    private GetGameRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private Player player;
    private Player player2;

    /**
     * Setup new mock objects for each test.
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);
        playerLobby=mock(PlayerLobby.class);
        gameCenter=new GameCenter();
        player=new Player("player");
        player2=new Player("player2");

        // create a unique CuT for each test
        CuT = new GetGameRoute(engine, playerLobby, gameCenter);
    }

    /**
     * Test that the Game view will create a new game if none exists.
     */
    @Test
    public void new_game() {
        // Arrange the test scenario: The session holds a game.
        final GameCenter center = new GameCenter();
        String string=center.newGame(player.getName(),player2.getName());
        when(session.attribute("currentUser")).thenReturn(player);
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        // Invoke the test (ignore the output)
        CuT.handle(request, response);

        // Analyze the content passed into the render method
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute(GetGameRoute.TITLE_ATTR, GetGameRoute.TITLE);
        testHelper.assertViewModelAttribute("viewMode", ViewMode.PLAY);
        testHelper.assertViewModelAttribute("redPlayer",playerLobby.getPlayer(center.getBoard(string).getRedPlayer()));
        testHelper.assertViewModelAttribute("whitePlayer",playerLobby.getPlayer(center.getBoard(string).getWhitePlayer()));
        // test view name
        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }

    @Test
    public void test_otherPlayer_ingame(){
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        GameCenter mockgc = mock(GameCenter.class);

        when(session.attribute("currentUser")).thenReturn(player);


        String player1Name = "juan";
        String player2Name = player2.getName();

        HashMap <String,Boolean> map=new HashMap<>();
        map.put(player1Name, true);
        map.put(player2Name, true);

        when(mockgc.getPlayersInGame()).thenReturn(map);

        when(request.queryParams("name")).thenReturn(player2.getName());
        when(request.queryParams("gameID")).thenReturn(null);

        // Invoke the test (ignore the output)

        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        GetGameRoute CuT = new GetGameRoute(engine, playerLobby, mockgc);
        CuT.handle(request, response);

        // test view name
        testHelper.assertViewName("home.ftl");
    }
  @Test
    //this test is for when the currentUser is null which means they are the challenged player
    public void test_nullPlayer(){
        String boardID= gameCenter.newGame(player.getName(),player2.getName());
        Board board=gameCenter.getBoard(boardID);
        Player nullPlayer= null;
        when(session.attribute(GetHomeRoute.CURRENT_USER_ATTR)).thenReturn(nullPlayer);
        when(session.attribute("gameID")).thenReturn(boardID);
        assertEquals(board.getWhitePlayer(),player2.getName());
    }

}
