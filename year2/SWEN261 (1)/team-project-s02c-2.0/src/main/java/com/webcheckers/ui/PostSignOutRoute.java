package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.appl.PlayerLobbyExceptions;
import com.webcheckers.model.Board;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The UI controller for PostSignOutRoute
 */
public class PostSignOutRoute implements Route{
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    /**
     * The constructor for the {@code POST /signin} route handler.
     *
     * @param gameCenter the game center
     * @param templateEngine template engine to use for rendering HTML page
     *
     * @throws NullPointerException
     *    when the {@code templateEngine} parameter is null
     */
    public PostSignOutRoute(PlayerLobby playerLobby, GameCenter gameCenter, TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        //
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
        this.templateEngine = templateEngine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String handle(Request request, Response response) {
        // start building the View-Model
        final Map<String, Object> vm = new HashMap<>();
        // retrieve the game object
        final Session session = request.session();
        ModelAndView mv;
        try{
            Player player = session.attribute("currentUser");
            if(gameCenter.isInGame(player.getName())){
                vm.put("title", GetGameRoute.TITLE);
                String oppName = gameCenter.getOpponent(player.getName());
                Player opponent = playerLobby.getPlayer(oppName);
                String boardID = player.getName() + opponent.getName();
                String boardID2 = opponent.getName() + player.getName();
                vm.put("currentUser", player);
                String message = "You can't sign out while you are in a game, you must resign first";
                Message ERROR_MESSAGE = Message.error(message);
                if(gameCenter.getBoard(boardID) != null){
                    Board board = gameCenter.getBoard(boardID);
                    vm.put("gameID",boardID);
                    if(player.getColor()==board.getActivePlayer()) {
                        vm.put("board", board.getActiveBoardView());
                    }else{
                        vm.put("board", board.getInactiveBoardView());
                    }
                    vm.put("redPlayer", playerLobby.getPlayer(board.getRedPlayer()));
                    vm.put("whitePlayer", playerLobby.getPlayer(board.getWhitePlayer()));
                    vm.put("activeColor", board.getActivePlayer());
                    vm.put("viewMode", ViewMode.PLAY);
                    vm.put("message", ERROR_MESSAGE);
                }
                else if(gameCenter.getBoard(boardID2) != null){
                    Board board = gameCenter.getBoard(boardID2);
                    if(player.getColor()==board.getActivePlayer()) {
                        vm.put("board", board.getActiveBoardView());
                    }else{
                        vm.put("board", board.getInactiveBoardView());
                    }
                    vm.put("gameID",boardID2);
                    vm.put("redPlayer", playerLobby.getPlayer(board.getRedPlayer()));
                    vm.put("whitePlayer", playerLobby.getPlayer(board.getWhitePlayer()));
                    vm.put("activeColor", board.getActivePlayer());
                    vm.put("viewMode", ViewMode.PLAY);
                    vm.put("message", ERROR_MESSAGE);
                }
                mv = new ModelAndView(vm, "game.ftl");
            }
            else{
                session.removeAttribute("currentUser");
                playerLobby.removePlayer(player.getName());
                vm.put("title", "Welcome!");
                // display a user message in the Home page
                vm.put("message", GetHomeRoute.WELCOME_MSG);
                int num_players = playerLobby.getActivePlayerCount();
                vm.put(GetHomeRoute.NUM_PLAYERS, num_players);
                mv = new ModelAndView(vm, "home.ftl");
                response.redirect("/");
            }
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
        vm.put("title", "Sign-out");

        Message ERROR_MESSAGE = Message.error(message);

        vm.put("message", ERROR_MESSAGE);
        return new ModelAndView(vm, "signout.ftl");
    }
}
