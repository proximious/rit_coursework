// Alex Iacob
// ai9388@rit.edu

package playingCards;

public class Hand {
    private Card[] cardArray;

    public Hand(){
        // cardArray is [null, null] prior to being filled with 2 cards
        cardArray = new Card[2];
    }

    // generalized function to add cards into the respective hand
    public void fillHand(Card card) {
        // checks whether the hand location has a card in it or not
        // if the first location is null, then the card gets placed there
        // otherwise it gets placed in the other available spot
        if (cardArray[0] == null) {
            cardArray[0] = card;
        } else {
            cardArray[1] = card;
        }

        if (cardArray[0] != null && cardArray[1] != null) {
            if (cardArray[1].value() > cardArray[0].value()) {
                Card temp = cardArray[0];
                cardArray[0] = cardArray[1];
                cardArray[1] = temp;
            }
        }
    }

    // prints the user's hand in a more viewing friendly way
    public String printRespectiveHand(){
        String output = " hand: " + cardArray[0].toString()
                + " and "+ cardArray[1].toString() + ". ";
        return output;
    }

    public int calculateValue(){
        int totalValue = 0;
        // getting value for plain high card
        totalValue += (cardArray[0].value() * 14) + cardArray[1].value();

        // checking to see whether the hand contains a pair
        if (cardArray[0].getRank() == cardArray[1].getRank()){
            totalValue += 420;
        // checking to see whether hand contains a flush
        } else if (cardArray[0].getSuit() == cardArray[1].getSuit()){
            totalValue += 210;
        }

        // if hand contains neither, total does not get updated and simply gets returned
        return totalValue;
    }

}