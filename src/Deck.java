import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
  private ArrayList<Card> deck;

  //Deck constructor creates deck/s.
  public Deck(int numberOfDecks) {
    deck = new ArrayList<>();

    for (Suit suit : Suit.values()) {
      for (NameAndValue nameAndValue : NameAndValue.values()) {
        for (int i = 0; i < numberOfDecks; i++)
          deck.add(new Card(nameAndValue, suit));
      }
    }
    System.out.println(deck.size() + " Cards in Deck [" + numberOfDecks + "]");
  }


  //Print Deck [10] King of Hearts \n
  public String toString() {

    StringBuilder textFormat = new StringBuilder();
    for (Card card : deck) {
      textFormat.append(card.toString()).append("\n");
    }
    return textFormat.toString();
  }

  //AddCard
  public void addCard(Card card) {
    deck.add(card);
  }


  //Shuffle Cards
  public void shuffleCardDeck() {
    Collections.shuffle(deck, new Random());
  }

}


