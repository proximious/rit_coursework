package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameCenter;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import com.webcheckers.util.Validate;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles move validation. One part of the making move process.
 * Checks if a move is valid through a series of checks.
 * Will send a INFO message if the move is valid and ERROR if it is invalid
 *
 * The UI controller for post Validate move route
 */
public class PostValidateMoveRoute implements Route {
    private Board gameBoard;
    private Move actionData;

    private final Gson gson;
    private final GameCenter gameCenter;

    /**
     * Constructor for the class
     * @param gson: A gson that must not be null
     * @param gameCenter: A GameCenter that must not be null
     */
    public PostValidateMoveRoute(Gson gson, GameCenter gameCenter) {
        Objects.requireNonNull(gson, "gson must not be null");
        Objects.requireNonNull(gameCenter, "gameCenter must not be null");
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object handle(Request request, Response response) {
        String gameID = request.queryParams("gameID");
        String moveJSON = request.queryParams("actionData");
        actionData = gson.fromJson(moveJSON, Move.class);
        gameBoard = gameCenter.getBoard(gameID);
        Validate validate = new Validate(gameBoard);

        Session session = request.session();
        ArrayList<Move>moveArray=session.attribute("moveArray");
        if (moveArray == null) {
            moveArray = new ArrayList<>();
        }
        ArrayList<MoveType>moveTypeArrayList=session.attribute("moveTypes");
        if (moveTypeArrayList == null) {
            moveTypeArrayList = new ArrayList<>();
        }
        MoveType type = actionData.determineMoveType();
        Message message=null;
        switch (type){
            case SIMPLE_MOVE:
                if(validate.justValidateForwardSimpleMove(actionData)){
                    moveArray.add(actionData);
                    moveTypeArrayList.add(MoveType.SIMPLE_MOVE);
                    message=  Message.info(validate.getMessageString());
                    response.type("INFO");
                }
                break;
            case SIMPLE_MOVE_BACK:
                if(validate.justValidateBackwardSimpleMove(actionData)){
                    moveArray.add(actionData);
                    moveTypeArrayList.add(MoveType.SIMPLE_MOVE_BACK);
                    message=  Message.info(validate.getMessageString());
                    response.type("INFO");
                }
                break;
            case JUMP_MOVE:
                if(validate.justValidateForwardJumpMove(actionData)){
                    moveArray.add(actionData);
                    moveTypeArrayList.add(MoveType.JUMP_MOVE);
                    message=  Message.info(validate.getMessageString());
                    response.type("INFO");
                }
                break;
            case JUMP_MOVE_BACK:
                if(validate.justValidateBackwardJumpMove(actionData)){
                    moveArray.add(actionData);
                    moveTypeArrayList.add(MoveType.JUMP_MOVE_BACK);
                    message=  Message.info(validate.getMessageString());
                    response.type("INFO");
                }
                break;
        }
        if(message==null){
            message=Message.error(validate.getMessageString());
            response.type("ERROR");
        }else{
            session.attribute("moveArray",moveArray);
            session.attribute("moveTypes",moveTypeArrayList);
        }
        response.body(validate.getMessageString());
        return gson.toJson(message);
    }
}
