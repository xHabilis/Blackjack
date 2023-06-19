public class User {
  private String name;
  private Hand hand;


  public User() {
    this.name = "";
    this.hand = new Hand();
  }

  //Returns Players hand and Value
  public void showHand() {
    System.out.println(getName() + " Has " + hand.getValueOfHand() + "\n" + getHand());

  }

  //If hand value is 21  = true
  public boolean hasBlackjack() {
    return hand.getValueOfHand() == 21;
  }

  //Draw a new card from the deck
  public void hitMe(Deck deck) {
    hand.drawCardFromDeck(deck);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Hand getHand() {
    return hand;
  }

}

class Dealer extends User {

  public Dealer() {
    super.setName("Dealer");

  }

  //Dealer: First hand, first card is face down, second card is face up
  public void showOpeningHand() {

    Card first =  super.getHand().getHand().get(0);
    Card second = super.getHand().getHand().get(1);

    System.out.println("Dealer is showing: \n" + first + "\n" + "2nd Card: [Face Down]" + "\n");
  }

}

class Player extends User {
  public Player() {
    super.setName("Player");
  }

  public void hitOrStand() {
    //Dealer/Player name?

  }



}

