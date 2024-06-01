package com.webcheckers.appl;

import com.webcheckers.model.Board;
import com.webcheckers.model.Move;

import java.util.List;

/**
 * Contains code for PlayerServices. Stores information like the GameCenter and the board
 */
public class PlayerServices {
    private final GameCenter gameCenter;
    private Board board;
    private List<Move> moves;
    private boolean isGameOver;
    private boolean wongame;
    private int num_moves;

    /**
     * Constructor for PlayerServices
     * @param board: A Board
     * @param gameCenter: A GameCenter
     */
    public PlayerServices(Board board, GameCenter gameCenter){
        this.board = board;
        this.gameCenter = gameCenter;
    }

    /**
     * Gets the board
     * @return: The board
     */
    public Board getBoard(){
        return board;
    }

    /**
     * Sets the board
     * @param board: The board being set
     */
    public void setBoard(Board board){
        this.board=board;
    }

}
