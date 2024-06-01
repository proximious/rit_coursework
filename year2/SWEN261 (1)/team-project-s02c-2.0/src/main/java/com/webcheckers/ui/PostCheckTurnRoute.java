package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.Player;
import com.webcheckers.model.Board;
import com.webcheckers.util.Message;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.Objects;

/**
 * The UI controller to POST message after each turn
 */
public class PostCheckTurnRoute implements Route {

    private GameCenter gameCenter;
    private Gson gson;

    public PostCheckTurnRoute(Gson gson, GameCenter gameCenter){
        Objects.requireNonNull(gameCenter, "gameCenter must not be null");
        Objects.requireNonNull(gson, "gson must not be null");

        this.gameCenter = gameCenter;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) {
        Session session = request.session();
        Player currentUser = session.attribute("currentUser");

        String gameID = request.queryParams("gameID");
        Board currentBoard = gameCenter.getBoard(gameID);

        Message message;
        if (currentBoard.getActivePlayer() == currentUser.getColor()){
            message = Message.info("true");
        }
        else{
            message = Message.info("false");
        }
        response.type("INFO");
        response.body(message.getText());

        return gson.toJson(message);
    }
}
