// Alex Iacob
// ai9388@rit.edu

package miniPoker;

import playingCards.Card;

import playingCards.Hand;

import java.util.Scanner;

public class Human {
    //creating private variables
    private Scanner in;
    private Hand hand;

    public Human(Scanner in) {
        this.in = in;
        this.hand = new Hand();
    }

    // adds card to respective hand
    public void addCard(Card c) {
        this.hand.fillHand(c);
    }

    // clears the current hand
    public void newHand(){
        this.hand = new Hand();
    }

    // prints the user's hand in a more readable format
    public void printHand(){
        System.out.print("Your" + hand.printRespectiveHand());
    }

    // asks the user if they would like to stand
    public boolean stand(){
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to stand or fold? \n " +
                "Please enter 'y' to stand or 'n' to fold.");
        String standBool = input.next();

        boolean standingBool = false;

        if (standBool.equals("y")){
            standingBool = true;
        } else if(standBool.equals("n")){
            standingBool = false;
        } else{
            System.out.println("Invalid input.");
            stand();
        }
        return standingBool;
    }

    // calculates the value for the hand
    public int value(){
        return hand.calculateValue();
    }

    // compares user's hand to the computer's hand and returns an integer
    // if the integer is a negative number, then the cpu won
    // if the integer is 0, then it is a draw
    // if the integer is a positive number, then the human won
    public int compareTo(Computer computer){
        return value() - computer.value();
    }

}
