package com.webcheckers.ui;

import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerLobbyExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.Mockito.*;

@Tag("UI-Tier")
public class PostSignInRouteTest {
    private Request request;
    private Session session;
    private Response response;
    private TemplateEngine engine;
    private PlayerLobby playerLobby;
    private PlayerLobbyExceptions playerLobbyException;
    private PostSigninRoute CuT;

    private Player player1;

    private static final String PLAYER1_NAME = "May";
    private static final String PLAYER_BAD_NAME = "!!!INVALID***";
    private static final String PLAYER_LOBBY_EXCEPTION = "exception";

    /**
     * Setup mocks for each test
     */
    @BeforeEach
    public void setup(){
        request = mock(Request.class);
        session = mock(Session.class);
        engine = mock(TemplateEngine.class);
        response = mock(Response.class);

        playerLobby = mock(PlayerLobby.class);
        playerLobbyException = mock(PlayerLobbyExceptions.class);
        player1 = new Player(PLAYER1_NAME);

        when(request.session()).thenReturn(session);
        CuT = new PostSigninRoute(playerLobby, engine);
    }

    /**
     * Checks a valid sign in
     */
    @Test
    public void signInValid(){
        when(session.attribute("Player")).thenReturn(player1);
        when(request.queryParams("name")).thenReturn(PLAYER1_NAME);
        when(request.session()).thenReturn(session);
        CuT.handle(request, response);
        verify(response, times(1)).redirect(WebServer.HOME_URL);
    }

    /**
     * Checks an invalid sign in attempt
     */
    @Test
    public void signInInvalidPlayer(){
        TemplateEngineTester helper = new TemplateEngineTester();
        try {
            when(playerLobbyException.getMessage()).thenReturn(PLAYER_LOBBY_EXCEPTION);
            when(request.queryParams("name")).thenReturn(PLAYER_BAD_NAME);
            when(playerLobby.newPlayer(PLAYER_BAD_NAME)).thenThrow(playerLobbyException);

            when(engine.render(any(ModelAndView.class))).thenAnswer(helper.makeAnswer());
            CuT.handle(request, response);
        }catch (PlayerLobbyExceptions e){
            helper.assertViewModelAttribute("message", PLAYER_LOBBY_EXCEPTION);
        }
    }

}
