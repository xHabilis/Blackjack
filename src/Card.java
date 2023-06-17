public class Card {
  private NameAndValue nameAndValue;
  private Suit suit;

  public Card(NameAndValue nameAndValue, Suit suit) {
    this.nameAndValue = nameAndValue;
    this.suit = suit;
  }

  //Returns readable: [10]King of Hearts
  public String toString() {
    return String.format("[%d]-[", nameAndValue.valueOfCard) + nameAndValue + " of " + suit + "]";
  }

}
