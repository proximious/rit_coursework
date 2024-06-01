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

import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles submitting the activePlayers turn and then changing the state.
 * It will check and validate the move to make sure no other move is available.
 * If another move is available it will make the user backup and take the other move.
 *
 * It will then submit the move and update the board and switch the activePlayer to
 * the other player.
 *
 * The UI controller for post Submit Turn Route
 */
public class PostSubmitTurnRoute implements Route {
    private String gameID;
    private Board gameBoard;

    private final Gson gson;
    private final GameCenter gameCenter;

    /**
     * Constructor for the class
     * @param gson: gson that must not be null
     * @param gameCenter: GameCenter that must not be null
     */
    public PostSubmitTurnRoute(Gson gson, GameCenter gameCenter) {
        Objects.requireNonNull(gson, "gson must not be null");
        Objects.requireNonNull(gameCenter, "game center must not be null");
        this.gson = gson;
        this.gameCenter = gameCenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();

        this.gameID = request.queryParams("gameID");
        this.gameBoard = gameCenter.getBoard(gameID);
        Validate validate = new Validate(gameBoard);

        ArrayList<Move> moves = session.attribute("moveArray");
        ArrayList<MoveType> moveTypes = session.attribute("moveTypes");

        // if the last move is a jump, we must verify if another jump can not be made to extend the turn
        MoveType lastMoveType = moveTypes.get(moveTypes.size()-1);
        if (lastMoveType == MoveType.JUMP_MOVE) {
            Move lastMove = moves.get(moves.size() - 1);
            Position endPosition = lastMove.getEnd();
            if (validate.forwardJumpAvailable(endPosition) || validate.backwardJumpAvailable(endPosition)) {
                // turn is invalid
                String errorMessage = "A turn can not be submitted while more jumps are available with the same piece";
                Message message = Message.error(errorMessage);
                response.type("ERROR");
                response.body(errorMessage);
                return gson.toJson(message);
            }
        }

        // Check that each move made this turn are valid in the context they were made
        MoveType thisMoveType;
        for (int i = 0; i < moves.size(); i++) {
            thisMoveType = moveTypes.get(i);
            if (thisMoveType == MoveType.SIMPLE_MOVE || thisMoveType == MoveType.SIMPLE_MOVE_BACK){
                if (i == 0){
                    if(validate.anyJumpsAvailable()){
                        String errorMessage = "A simple move can not be made when a jump is available";
                        Message message = Message.error(errorMessage);
                        response.type("ERROR");
                        response.body(errorMessage);

                        return gson.toJson(message);
                    }
                    else {
                        gameBoard.movePiece(moves.get(i).getStart(), moves.get(i).getEnd());
                    }
                } else {
                    String errorMessage = "A simple move can only be made as the first move in a turn";
                    Message message = Message.error(errorMessage);
                    response.type("ERROR");
                    response.body(errorMessage);
                    return gson.toJson(message);
                }
            } else if (moveTypes.get(i) == MoveType.JUMP_MOVE) {
                Position start = moves.get(i).getStart();
                if(validate.forwardJumpAvailable(start) || validate.backwardJumpAvailable(start)){
                    gameBoard.jumpWithPiece(moves.get(i).getStart(), moves.get(i).getEnd());
                } else {
                    String errorMessage = "The jump you were trying to make is no longer valid";
                    Message message = Message.error(errorMessage);
                    response.type("ERROR");
                    response.body(errorMessage);
                    return gson.toJson(message);
                }

            }
        }

        // toggles the active player
        if(gameBoard.getActivePlayer() == PieceColor.RED){
            gameBoard.setActivePlayer(PieceColor.WHITE);
        } else{
            gameBoard.setActivePlayer(PieceColor.RED);
        }

        // updates the board in the gameCenter to reflect the turn
        gameCenter.updateBoard(gameID, gameBoard);

        // removes session information relating to this turn
        session.removeAttribute("moveArray");
        session.removeAttribute("moveTypes");

        String infoMessage = "Turn has been submitted";
        Message message = Message.info(infoMessage);
        response.type("INFO");
        response.body(infoMessage);
        return gson.toJson(message);
    }

}
