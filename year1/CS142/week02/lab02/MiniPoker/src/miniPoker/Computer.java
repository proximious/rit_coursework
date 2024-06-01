// Alex Iacob
// ai9388@rit.edu

package miniPoker;

import playingCards.Card;

import playingCards.Hand;


public class Computer {
    private Hand hand;

    Computer(){
        this.hand = new Hand();
    }

    public void addCard(Card c) {
        this.hand.fillHand(c);
    }

    // clears the current hand
    public void newHand(){
        this.hand = new Hand();
    }

    // prints the computer's hand in a more readable format
    public void printHand(){
        System.out.print("Computer's" + hand.printRespectiveHand());
    }

    // determines whether the computer should stand or fold
    // the computer will always stand(true) on chances of victory >= 50%
    // and will fold(false) on anything under 50%
    public boolean stand(){
        if (hand.calculateValue() < 179){
            return false;
        } else{
            return true;
        }
    }

    // checks the computer's hand total numerical value
    public int value(){
        return hand.calculateValue();
    }

}
