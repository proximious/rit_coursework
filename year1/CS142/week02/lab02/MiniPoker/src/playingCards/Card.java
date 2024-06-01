// Alex Iacob
// ai9388@rit.edu

package playingCards;

public class Card {
    // creating private variables
    private Suit suit;
    private Rank rank;

    // constructor
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // declaring methods

    // gets the rank of the card
    public Rank getRank(){
        return this.rank;
    }

    // gets the suit of the card
    public Suit getSuit(){
        return this.suit;
    }

    // gets the shortened name of the card
    // Ace of Spades would be 'AS'
    // 3 of Diamonds would be '3D'
    public String getShortName(){
        String shortName = "";
        shortName += rank.getShortName();
        shortName += suit.getShortName();
        return shortName;
    }

    @Override
    // turns the given card into a string
    // when given (Ace, Spades) the function returns 'Ace of Spades'
    public String toString(){
        return (rank + " of " + suit);
    }

    // gets the numerical value of the card
    public int value(){
        return rank.getValue();
    }
}
