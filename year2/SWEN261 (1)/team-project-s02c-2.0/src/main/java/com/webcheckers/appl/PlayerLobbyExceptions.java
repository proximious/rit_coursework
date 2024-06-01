package com.webcheckers.appl;

/**
 * PlayerLobbyExceptions
 * Creates exceptions for the PlayerLobby class
 */
public class PlayerLobbyExceptions extends RuntimeException {

    /**
     * Creates a PlayerLobbyException to handle things like
     * taken username.
      * @param message: Passes the message to the exception
     */
    PlayerLobbyExceptions(String message){
        super(message);
    }
}
