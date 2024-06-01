package com.webcheckers.ui;

import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerLobbyExceptions;
import com.webcheckers.util.Message;

import spark.*;

import java.util.*;

/**
 * The UI controller to POST displays on sign -ins.
 * Handle all cases from Acceptance Criteria.
 */
public class PostSigninRoute implements Route {
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;

    static final String USER_PARAM = "currentUser";
    /**
     * The constructor for the {@code POST /signin} route handler.
     * @param templateEngine template engine to use for rendering HTML page
     *
     * @throws NullPointerException
     *    when the {@code templateEngine} parameter is null
     */
    public PostSigninRoute(PlayerLobby playerLobby, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handle(Request request, Response response) {
        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");
        // display a user message in the Home page
        vm.put("message", GetHomeRoute.WELCOME_MSG);
        // retrieve the game object
        final Session session = request.session();
        // retrieve request parameter
        final String username = request.queryParams(USER_PARAM);

        ModelAndView mv;
        try{
            Player thisPlayer = playerLobby.newPlayer(username);
            session.attribute("currentUser", thisPlayer);

            int num_players=playerLobby.getActivePlayerCount();
            session.attribute("numPlayers", num_players);

            ArrayList<String> playersOnline = playerLobby.playersOnline();
            session.attribute("playersOnline", playersOnline);
            vm.put("playersOnline", playersOnline);

            vm.put(GetHomeRoute.NUM_PLAYERS, num_players);
            vm.put(GetHomeRoute.CURRENT_USER_ATTR, thisPlayer);
            mv = new ModelAndView(vm, "home.ftl");
            response.redirect("/");

        } catch(PlayerLobbyExceptions e){
            mv = error(vm, e.getMessage());
        }

        return templateEngine.render(mv);
    }

    /**
     * Creates a new ModelAndView to display with an error message
     * @param vm the hashmap of view model objects
     * @param message the error message
     * @return the created ModelAndView
     */
    private ModelAndView error(final Map<String, Object> vm, final String message) {
        vm.put("title", "Sign-in");

        Message ERROR_MESSAGE = Message.error(message);

        vm.put("message", ERROR_MESSAGE);
        return new ModelAndView(vm, "signin.ftl");
    }

}
