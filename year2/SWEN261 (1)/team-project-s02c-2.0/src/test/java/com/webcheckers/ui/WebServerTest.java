package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static spark.Spark.staticFileLocation;

@Tag("UI-tier")
public class WebServerTest {
    public static final String HOME_URL = "/";
    public static final String SIGNIN_URL = "/signin";
    public static final String GAME_URL = "/game";
    public static final String NAV_BAR_URL = "/nav-bar";
    public static final String PUBLIC_URL = "/public";

    private Request req;
    private Session ses;

    private TemplateEngine te;
    private PlayerLobby pl;
    private GameCenter gc;
    private Gson gson;

    @BeforeEach
    public void setup() {
        req = mock(Request.class);
        ses = mock(Session.class);
        when(req.session()).thenReturn(ses);
        te = mock(TemplateEngine.class);
        pl = mock(PlayerLobby.class);
        gc = mock(GameCenter.class);
        gson = new Gson();
    }


    @Test
    public void create_webServer(){
        WebServer CuT = new WebServer(pl, te, gc, gson);
        assertNotNull(CuT);
    }

    @Test
    public void test_initialize() {

    }
}