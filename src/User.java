import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
  private String name;
  private Hand hand;


  public User() {
    this.name = "";
    this.hand = new Hand();
  }

  //HIT: Draws card from deck and shows displays it
  public void hit(Deck deck) {
    this.getHand().drawCardFromDeck(deck);
    this.showHand();
  }

  //Returns Players hand and Value
  public void showHand() {
    System.out.println(getName() + " Has " + hand.getValueOfHand() + "\n" + getHand());

  }

  //If hand value is 21  = true
  public boolean hasBlackjack() {
    return hand.getValueOfHand() == 21;
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
    super.setName("Dealer ");

  }

  //Dealer: First hand, first card is face down, second card is face up
  public void showOpeningHand() {

    Card firstCard = super.getHand().getHand().get(0);

    System.out.println("Dealer is showing: \n" + firstCard + "\n" + "2nd Card: [Face Down]" + "\n");
  }

}

class Player extends User {

  private int wallet = 100;
  private int bet = 0;

  public Player() {
    super.setName("Player ");
  }

  public void placeBet() {


    Scanner scanner = new Scanner(System.in);
    boolean validEntry = true;
    int bet = 0;

    //Ask the player to place a bet
    System.out.println("Place a bet between [ $0 - " + "$" + getWallet() + " ]");

    do {
      try {
        bet = scanner.nextInt();
        validEntry = false;

      } catch (InputMismatchException exception) {

        System.out.println("""
          Invalid:
            Please enter a numeric amount.
          """
        );
        scanner.next();
      }


    } while (validEntry);
    this.bet = bet;

    if (!isValidBet()) {
      placeBet();
    }


  }

  public boolean isValidBet() {
    return bet <= getWallet();
  }

  public void hitOrStand(Deck deck) {

    Scanner scanner = new Scanner(System.in);
    boolean validEntry = true;
    int choice = 0;

    //Inform player of options: Prompt for a choice: Check data validity
    System.out.println("""
      Use the keyboard to make your next move:

      Enter [ 1 ] HIT
      Enter [ 0 ] STAND
      """);

    do {
      try {
        choice = scanner.nextInt();
        validEntry = false;

      } catch (InputMismatchException exception) {

        System.out.println("""
          Invalid Entry:

          Enter Number [ 1 ] to: HIT
          Enter Number [ 0 ] to: STAND
          """
        );
        scanner.next();
      }

    } while (validEntry);

    //If player HITS
    if (choice == 1) {

      this.hit(deck);

      //If player Stands
    }
    if (choice == 0) {
      playerStands();
    }

  }

  public void playerStands() {
    System.out.println("STAND");

  }

  public int getWallet() {
    return wallet;
  }

  public void winsBet(int bet) {
    this.wallet += bet;
  }

  public void losesBet(int bet) {
    this.wallet -= bet;
  }

  public int getBet() {
    return bet;
  }
}





