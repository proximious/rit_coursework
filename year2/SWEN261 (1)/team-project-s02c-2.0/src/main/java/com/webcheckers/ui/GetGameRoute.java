package com.webcheckers.ui;

import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.PieceColor;
import com.webcheckers.model.Player;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Board;
import com.webcheckers.util.Message;
import spark.*;

import java.util.*;

/**
 * GetGameRoute
 * Returns the current state of a game for the current user
 * and that user must be one of the two players of that game.
 */
public class GetGameRoute implements Route{
    public static final String TITLE_ATTR = "title";
    public static final String TITLE = "Game";
    public static final String VIEW_NAME = "game.ftl";

    private PlayerLobby playerLobby;
    private GameCenter gameCenter;
    private final TemplateEngine templateEngine;

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    GetGameRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameCenter gameCenter) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(gameCenter, "gameCenter must not be null");

        this.templateEngine = templateEngine;
        this.playerLobby = playerLobby;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) {
        //build the View-model
        final Map<String, Object> vm = new HashMap<>();
        vm.put(TITLE_ATTR, TITLE);

        vm.put("viewMode", ViewMode.PLAY);

        final Session session = request.session();
        Player currentUser = session.attribute("currentUser");

        // asserting that the current user exists so that we can get the current user's name
        assert currentUser != null;
        String currentUserName = currentUser.getName();
        // putting the current user in the hash map
        vm.put("currentUser", currentUser);
        String redName, whiteName;

        // getting the query params for the challenged player
        whiteName = request.queryParams("name");
        String gameID = request.queryParams("gameID");

        if(gameID != null){
            whiteName = gameCenter.getBoard(gameID).getWhitePlayer();
        }

        if(whiteName == null){
            redName = gameCenter.getOpponent(currentUserName);
            currentUser.setOpponent(playerLobby.getPlayer(redName));
            currentUser.setColor(PieceColor.WHITE);

            whiteName = gameCenter.getOpponent(redName);

        } else if(gameCenter.getPlayersInGame().get(whiteName) && gameID == null) {
            String message = "This player is already in a game";
            Message ERROR_MESSAGE = Message.error(message);
            vm.put("message2", ERROR_MESSAGE);

            int num_players=playerLobby.getActivePlayerCount();
            session.attribute("numPlayers",num_players);

            ArrayList<String> playersOnline = playerLobby.playersOnline();
            playersOnline.remove(currentUserName);
            session.attribute("playersOnline", playersOnline);

            vm.put("playersOnline", playersOnline);

            vm.put(GetHomeRoute.NUM_PLAYERS,num_players);
            return templateEngine.render(new ModelAndView(vm, "home.ftl"));
        } else if(gameID == null){
            redName = currentUserName;
            currentUser.setColor(PieceColor.RED);
            currentUser.setOpponent(playerLobby.getPlayer(whiteName));
        } else{
            redName = gameCenter.getBoard(gameID).getRedPlayer();
        }

        String boardID = redName + whiteName;

        Board currentBoard = gameCenter.getBoard(boardID);
        if(currentBoard == null){
            boardID = gameCenter.newGame(redName, whiteName);
            currentBoard = gameCenter.getBoard(boardID);
        }

        // put the gameID into the hash map
        vm.put("gameID", boardID);

        // putting the current user in the hash map
        vm.put("currentUser", currentUser);
        Player redPlayer = playerLobby.getPlayer(currentBoard.getRedPlayer());
        Player whitePlayer = playerLobby.getPlayer(currentBoard.getWhitePlayer());

        // entering the two players as red and white
        vm.put("redPlayer", redPlayer);
        vm.put("whitePlayer", whitePlayer);
        vm.put("activeColor", currentBoard.getActivePlayer());

        // flipping the board for the opposing side
        if (currentUser.getColor() == currentBoard.getActivePlayer()) {
            vm.put("board", currentBoard.getActiveBoardView());
        } else {
            vm.put("board", currentBoard.getInactiveBoardView());
        }

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
