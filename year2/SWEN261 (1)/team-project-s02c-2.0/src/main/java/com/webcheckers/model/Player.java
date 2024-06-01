package com.webcheckers.model;

/**
 * Contains code for the player object.
 * Creates players and has methods that can change attributes
 * of the player like setting its opponent to another player.
 * Has a few methods that can get player information.
 */
public class Player {
    private String name;
    private boolean inGame;
    private Player opponent;
    private PieceColor color;

    /**
     * constructor for the Player
     * @param name: the username of the player
     */
    public Player(String name){
        this.name = name;
        color = null;
    }

    /**
     * getter for the username of the player
     * @return the username as the String
     */
    public String getName() {return name;}

    /**
     * boolean value for checking if the player is in a game
     * @return boolean value that checks if the player is in a game
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * getter for the opponent of the player
     * @return opponent player
     */
    public Player getOpponent() {
        return opponent;
    }

    /**
     * setter for the boolean value of the
     * player if it's in a game
     * @param value: the value to set the boolean to
     */
    public void setInGame(boolean value){
        inGame = value;
    }

    /**
     * setter for the opponent of the player
     * @param player: the opponent you want to set it to
     */
    public void setOpponent(Player player){
        opponent = player;
    }

    /**
     * setter for the color of the player
     * @param color: the color you want to set it to
     */
    public void setColor(PieceColor color) {
        this.color = color;
    }

    /**
     * getter for the color of the player
     * @return the players color
     */
    public PieceColor getColor() {
        return color;
    }

}
