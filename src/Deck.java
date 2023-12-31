import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
  private final ArrayList<Card> deck;

  //Deck constructor creates deck/s.
  public Deck(int numberOfDecks) {
    deck = new ArrayList<>();

    for (Suit suit : Suit.values()) {
      for (NameAndValue nameAndValue : NameAndValue.values()) {
        for (int i = 0; i < numberOfDecks; i++)
          deck.add(new Card(nameAndValue, suit));
      }
    }
  }

  //Print Deck [10] King of Hearts \n
  public String toString() {

    StringBuilder textFormat = new StringBuilder();
    for (Card card : deck) {
      textFormat.append(card.toString()).append("\n");
    }
    System.out.println("Deck has " + deck.size());
    return textFormat.toString();
  }

  //Removes a card from the deck
  public Card removeCardFromDeck() {
    return deck.remove(0);

  }

  //Shuffle Cards
  public void shuffleCardDeck() {
    Collections.shuffle(deck, new Random());
  }

  public int getDeckSize() {
    return deck.size();
  }

}


