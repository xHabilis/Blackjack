public enum Suit {

  //Constants: The 4 Suit types in Cards
  CLUBS("Clubs"),
  DIAMONDS("Diamonds"),
  HEARTS("Hearts"),
  SPADES("Spades");

  private final String suitName;

  Suit(String suitName) {
    this.suitName = suitName;
  }

  public String toString() {
    return suitName;
  }

}
