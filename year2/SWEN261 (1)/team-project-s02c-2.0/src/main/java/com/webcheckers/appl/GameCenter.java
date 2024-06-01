package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.PieceColor;
import com.webcheckers.model.Player;

import java.util.HashMap;

/**
 * Contains code for the GameCenter. Handles all the players that
 * are currently in a game. Creates games and adds players to them.
 * It also will update the board when changes are made to it.
 */
public class GameCenter {
    private HashMap<String,Boolean> playersInGame;

    private HashMap<String, Board> boardsInUse;
    private HashMap<String, String> playerPairs;

    /**
     * constructor for the GameCenter
     */
    public GameCenter(){
        playersInGame = new HashMap<String, Boolean>();
        boardsInUse = new HashMap<String, Board>();
        playerPairs = new HashMap<String, String>();
    }

    /**
     * creates a new game and puts all the involving players into the hashmap
     * for the players in the game
     * @param redPlayer: the player that will be the red pieces
     * @param whitePlayer: the player that will be the white pieces
     * @return the ID of the board
     */
    public String newGame(String redPlayer, String whitePlayer){
        playersInGame.put(redPlayer, true);
        playersInGame.put(whitePlayer, true);

        playerPairs.put(redPlayer, whitePlayer);
        playerPairs.put(whitePlayer, redPlayer);

        Board board = new Board(redPlayer, whitePlayer);
        String boardID = redPlayer + whitePlayer;
        boardsInUse.put(boardID, board);
        return boardID;
    }

    /**
     * getter for the board being used
     * @param boardId: the ID of the board we want to get
     * @return the Board that corresponds to the ID
     */
    public Board getBoard(String boardId){
        return boardsInUse.get(boardId);
    }

    /**
     * getter for the board being used
     * @param player: one of the players currently using the desired board
     *              Precondition: the player must be in a game
     * @return the Board that the specified player is using
     */
    public Board getBoard(Player player){
        String playerName = player.getName();
        String opponentName = playerPairs.get(playerName);

        String id1 = playerName + opponentName;
        if (boardsInUse.containsKey(id1)) {
            return boardsInUse.get(id1);
        } else{
            String id2 = opponentName + playerName;
            return boardsInUse.get(id2);
        }
    }

    /**
     * Takes the current player and the current players opponent and figures out their board id
     * @param currentPlayer: A player that is the current player
     * @param opponent: A player that is the current players opponent
     * @return A string that is the boardID
     */
    public String getBoardID(Player currentPlayer, Player opponent){
        String currentPlayerName = currentPlayer.getName();
        String opponentName = opponent.getName();
        String boardID;
        if(currentPlayer.getColor() == PieceColor.RED){
            boardID = currentPlayerName + opponentName;
        }else{
            boardID = opponentName + currentPlayerName;
        }
        return boardID;
    }

    public HashMap<String, Boolean> getPlayersInGame() {
        return playersInGame;
    }

    /**
     * the boolean that checks if the player is in a game
     * @param player: the player to check
     * @return true if in game, false otherwise
     */
    public boolean isInGame(String player){
        if(!playersInGame.containsKey(player)){
            playersInGame.put(player, false);
            return false;
        }
        else{
            return playersInGame.get(player);
        }
    }

    /**
     * getter for the opponent player
     * @param player: the player whose opponent we want
     * @return the username for the opponent
     */
    public String getOpponent(String player){
        return playerPairs.get(player);
    }

    /**
     * Updates the board stored in gameCenter at the given ID
     * @param boardID the ID of the board to update
     * @param board the new version of the board
     */
    public void updateBoard(String boardID, Board board){
        boardsInUse.put(boardID, board);
    }

}
