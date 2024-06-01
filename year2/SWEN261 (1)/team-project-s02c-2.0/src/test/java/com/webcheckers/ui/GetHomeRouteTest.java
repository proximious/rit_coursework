package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

@Tag("UI-Tier")
public class GetHomeRouteTest {
    private GetHomeRoute CuT;

    // friendly objects
    private GameCenter gameCenter;

    // mock objects
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private PlayerLobby playerLobby;
    private Player player;
    private ArrayList<String> playersOnline;
    /**
     * Setup new mock objects for each test
     */
    @BeforeEach
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        playerLobby = new PlayerLobby();
        engine = mock(TemplateEngine.class);
        player=new Player("player");
        // create a unique CuT for each test
        // the GameCenter is friendly but the engine mock will need configuration
        gameCenter = new GameCenter();
        CuT = new GetHomeRoute(engine,playerLobby,gameCenter);
    }

    /**
     * Test that CuT shows the Home view when the session is brand new.
     */
    @Test
    public void new_session() {
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester tester = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());
        when(session.attribute("currentUser")).thenReturn(player);
        //invoke the test
        CuT.handle(request,response);
        // Analyze the results:
        //   * model is a non-null Map
        tester.assertViewModelExists();
        tester.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        tester.assertViewModelAttribute("title", GetHomeRoute.TITLE_ATTR);
        tester.assertViewModelAttribute("message", GetHomeRoute.WELCOME_MSG);
        tester.assertViewModelAttribute("playerLobby",playerLobby);
        tester.assertViewModelAttribute(GetHomeRoute.NUM_PLAYERS,playerLobby.getActivePlayerCount());
        tester.assertViewModelAttribute(GetHomeRoute.CURRENT_USER_ATTR,player);
        tester.assertViewModelAttribute("playersOnline",playerLobby.playersOnline());
    }
    @Test
    public void playerIngame(){
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester tester = new TemplateEngineTester();
        Player player2=new Player("player2");
        gameCenter.newGame(player.getName(),player2.getName());
        assertTrue(gameCenter.isInGame(player.getName()));
        when(engine.render(any(ModelAndView.class))).thenAnswer(tester.makeAnswer());
        when(session.attribute("currentUser")).thenReturn(player);
        CuT.handle(request,response);
        verify(response).redirect(WebServer.GAME_URL);
    }
}
