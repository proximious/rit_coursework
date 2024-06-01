package com.webcheckers.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import spark.*;

import com.webcheckers.util.Message;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());
  static final String TITLE_ATTR = "Welcome";
  static final Message WELCOME_MSG = Message.info("Welcome to the world of online Checkers.");
  static final String NUM_PLAYERS = "numPlayers";
  static final String NEW_PLAYER_ATTR = "newPlayer";
  static final String CURRENT_USER_ATTR = "currentUser";
  static final String PLAYER_LOBBY = "playerLobby";

  private PlayerLobby playerLobby;
  private GameCenter gameCenter;

  private final TemplateEngine templateEngine;

  /**
   * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameCenter gameCenter) {
    this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
    this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
    this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    LOG.finer("GetHomeRoute is invoked.");
    //
    Map<String, Object> vm = new HashMap<>();
    vm.put("title", TITLE_ATTR);

    // display a user message in the Home page
      vm.put("message", WELCOME_MSG);

    final Session session = request.session();
    Player currentUser = session.attribute("currentUser");

    vm.put(PLAYER_LOBBY,playerLobby);
    int activePlayers = playerLobby.getActivePlayerCount();
    vm.put(NUM_PLAYERS,activePlayers);

    if(currentUser != null){
        if(gameCenter.isInGame(currentUser.getName())){
            response.redirect(WebServer.GAME_URL);
        }
        else{
            vm.put(CURRENT_USER_ATTR, currentUser);

            ArrayList<String> playersOnline = playerLobby.playersOnline();
            playersOnline.remove(currentUser.getName());
            if(activePlayers == 1){
                playersOnline.add("There are no other players available to play at this time.");
            }

            vm.put("playersOnline", playersOnline);
        }
    }

    // render the View
    return templateEngine.render(new ModelAndView(vm, "home.ftl"));
  }
}
