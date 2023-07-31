public enum Suit {

  //Constants: The 4 Suit types in Cards
  CLUBS("Clubs", '♣'),
  DIAMONDS("Diamonds", '♦'),
  HEARTS("Hearts", '❤'),
  SPADES("Spades", '♠');

  private final String suitName;
  private final char suitImage;


  Suit(String suitName, char suitImage) {
    this.suitName = suitName;
    this.suitImage = suitImage;
  }

  public String toString() {
    return suitName + "[" + suitImage;

  }

}
