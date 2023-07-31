import java.util.ArrayList;
public class Hand {
  ArrayList<Card> hand;

//Construct empty Hand array
public Hand() {
  hand = new ArrayList<>();
}

//Hand as String
public String toString() {

  StringBuilder aString = new StringBuilder();
  for (Card card: hand) {
    aString.append(card).append("\n");
  }
  return aString.toString();
}

//Draw card from deck
  public void drawCardFromDeck(Deck deck) {

    Card theCard = deck.removeCardFromDeck();
    hand.add(theCard);

  }

//Gets The value of the hand. Accounts for Ace 1 or 11
public int getValueOfHand() {
  int handValue = 0;

  for (Card card : hand) {
    String cardValue = card.getNameAndValue().nameOfCard;
    handValue += card.getNameAndValue().valueOfCard;

    if (handValue < 11 && cardValue.equals("Ace")) {
      handValue += 10;
    }
  }
  return handValue;
}

   //Get rid of current hand : Before starting new game
  public void discardHand() {
  while (hand.size() != 0) {
    hand.remove(0);
  }

  }

  public ArrayList<Card> getHand() {
    return hand;
  }

}
