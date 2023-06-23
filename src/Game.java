import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
  private Player player;
  private Deck deck;
  private Dealer dealer;
  private int numberOfDecks;
  private int draws, playerwins, playerLoses;

  public Game(int numberOfDecks) {

    //Number of decks in game
    this.numberOfDecks = numberOfDecks;

    //Create and shuffle the deck
    this.deck = new Deck(numberOfDecks);
    this.deck.shuffleCardDeck();

    //Creates all users in the game
    this.player = new Player();
    this.dealer = new Dealer();

    //1. Starts a round of blackjack
    startNewGame();

    //2. Game Sequence Starts: Check for player/dealer blackjack. if both false: Player makes a move first.
    openingHandCheck();

    //3. Player decides to hit or stand as needed
    player.hitOrStand(deck);

    startNewGame();


  }
  public void startNewGame() {

    //If Deck is NOT empty
    if (deck.getDeckSize() > 5) {

      //Display Game Information
      welcomeScreen();


      //Dealer gets cards
      dealer.getHand().drawCardFromDeck(deck);
      dealer.getHand().drawCardFromDeck(deck);


      //Player gets cards
      player.getHand().drawCardFromDeck(deck);
      player.getHand().drawCardFromDeck(deck);


      //Show cards to table
      dealer.showOpeningHand();
      player.showHand();

      //Check both hands for blackJack
      openingHandCheck();

      //Player Moves
      player.hitOrStand(deck);

      //Check if player Busted
      checkIfPlayerBusted();

      //Dealer hits until 17
      dealer.showHand();
      while (dealer.getHand().getValueOfHand() < 17) {
        dealer.hit(deck);
      }

      //Check for Winner
      checkForWinner();

      //Discard Hands Before starting a new game
      System.out.println("Player " + player.getHand().discardHand());
      System.out.println("Dealer " + dealer.getHand().discardHand());
      startNewGame();

    } else {
      createNewDecksUserInput();
    }


  }

  public void openingHandCheck() {

    //Check if Dealer or Player has Blackjack: If true, assign wins/losses/draws: Start new Game
    if (dealer.hasBlackjack()) {
        dealer.showHand();

      if (player.hasBlackjack()) {

        System.out.println("This round is a Draw");
        draws++;
        scoreGame();

      } else {
        dealer.showHand();
        System.out.println("Dealer has Blackjack: " + dealer.getName() + " Dealer WINS! \n");

        playerLoses++;
        scoreGame();

      }
      //Discard all hands before starting new game
      discardPlayerAndDealerHand();
      startNewGame();

    }

    if (player.hasBlackjack()) {
      System.out.println("Player Has BlackJack: " + player.getName() + " WINS!");

      playerwins++;
      scoreGame();

      //If Player Wins: All users discard hands before starting new game
      discardPlayerAndDealerHand();

      startNewGame();

    }

  }

  public void checkForWinner() {

    //Check for Winner
    if (dealer.getHand().getValueOfHand() > 21) {
      System.out.println(dealer.getName() + " Busted!");
      playerwins++;
      scoreGame();

    } else if (dealer.getHand().getValueOfHand() > player.getHand().getValueOfHand()) {
      System.out.println(player.getName() + " Lost!");
      playerLoses++;
      scoreGame();

    } else if (player.getHand().getValueOfHand() > dealer.getHand().getValueOfHand()) {
      System.out.println(player.getName() + " Wins!");
      playerwins++;
      scoreGame();

    } else {
      System.out.println("This is a Draw");
      draws++;
      scoreGame();
    }
  }

  public void createNewDecksUserInput() {

    Scanner scanner = new Scanner(System.in);
    boolean validEntry = true;
    int choice = 0;

    System.out.println("""
      The Deck is Empty!
        Starting a new Game...
          How many decks would you like to use in this game?
              Enter Number using Keyboard.
      """);
    while (validEntry) {
      try {

        choice = scanner.nextInt();
        validEntry = false;

      } catch (InputMismatchException exception) {

        System.out.println("""
          Invalid Entry:

         Enter valid Number.
          """
        );
        scanner.next();
      }

    }
    System.out.println("Final: ");
    scoreGame();

    System.out.println("New Game: Number of Decks =  " + choice);
    Game game = new Game(choice);
    game.startNewGame();

  }

  public void checkIfPlayerBusted() {

    if (player.getHand().getValueOfHand() > 21) {
      System.out.println(player.getName() + " Busted! " + "[" + player.getHand().getValueOfHand() + "]");
      playerLoses++;
      scoreGame();

      discardPlayerAndDealerHand();

      startNewGame();
    }
  }


  public void scoreGame() {
    System.out.println("Game Score: \n" +
      "Player Loses - [ " + playerLoses + " ]\n" +
      "Player Wins - [ " + playerwins + " ]\n" +
      "Draws - [ " + draws + " ]\n\n"
    );
  }

  public void welcomeScreen() {
    System.out.println("-".repeat(30));
    System.out.println("""

         WELCOME TO BLACKJACK

            [1] - HIT - Request additional Card

            [2] - Stand - No More Cards

        """);
    System.out.println("-".repeat(30));
  }

  public void discardPlayerAndDealerHand() {
    System.out.println(player.getName() + player.getHand().discardHand());
    System.out.println(dealer.getName() + dealer.getHand().discardHand());
  }



}

