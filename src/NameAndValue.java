public enum NameAndValue {

  //Constants 13 Card Types with Blackjack Values
  ACE("Ace", 1),
  TWO("Two", 2),
  THREE("Three", 3),
  FOUR("Four", 4),
  FIVE("Five", 5),
  SIX("Six", 6),
  SEVEN("Seven", 7),
  EIGHT("Eight", 8),
  NINE("Nine", 9),
  TEN("Ten", 10),
  JACK("Jack", 10),
  QUEEN("Queen", 10),
  KING("King", 10);

  final String nameOfCard;
  final int valueOfCard;

  //Creates Object with Name and Value
  NameAndValue(String nameOfCard, int valueOfCard) {
    this.nameOfCard = nameOfCard;
    this.valueOfCard = valueOfCard;
  }

  public String toString() {
    return nameOfCard;
  }
}
