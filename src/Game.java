import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
  private final Player player;
  private final Deck deck;
  private final Dealer dealer;
  private final int numberOfDecks;
  private int draws;
  private int wins;
  private int losses;


  public Game(int numberOfDecks, String playerName, int startingWallet) {

    //Number of decks in game
    this.numberOfDecks = numberOfDecks;

    //Create and shuffle the deck
    this.deck = new Deck(numberOfDecks);
    this.deck.shuffleCardDeck();

    //Creates all users in the game
    this.player = new Player(playerName);
    this.dealer = new Dealer();

    //Set starting wallet size
    player.setWallet(startingWallet);

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
    if (deck.getDeckSize() >= 5) {

      //Display Game Information
      welcomeScreen();


      //Dealer gets cards
      dealer.getHand().drawCardFromDeck(deck);
      dealer.getHand().drawCardFromDeck(deck);


      //Player gets cards
      player.getHand().drawCardFromDeck(deck);
      player.getHand().drawCardFromDeck(deck);


      //Show dealer and player cards to table
      dealer.showOpeningHand();
      player.showHand();

      //Player places a Bet
      player.placeBet();

      //Check both player and dealer's hands for blackJack
      openingHandCheck();

      //Player Moves
      player.hitOrStand(deck);

      //Check if player Busted
      checkIfPlayerBusted();

      //Dealer shows hand then Dealer Moves: Hits until 17
      dealer.showHand();
      while (dealer.getHand().getValueOfHand() < 17) {
        dealer.hit(deck);
      }

      //Check for Winner
      checkForWinner();

      //Discard Hands Before starting a new game
      discardAllHands();
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
        //Dealer Wins
        dealer.showHand();
        System.out.println("Dealer has Blackjack: " + dealer.getName() + " Dealer WINS! \n");

        losses++;

        player.losesBet(player.getBet());

        scoreGame();

      }
      //Discard all hands before starting new game
      discardAllHands();
      startNewGame();

    }

    if (player.hasBlackjack()) {
      System.out.println("Player Has BlackJack: " + player.getName() + " WINS!");

      wins++;
      player.winsBet(player.getBet());
      scoreGame();

      //If Player Wins: All users discard hands before starting new game
      discardAllHands();
      startNewGame();

    }

  }

  public void checkForWinner() {

    //Check for Winner
    if (dealer.getHand().getValueOfHand() > 21) {
      System.out.println(dealer.getName() + " Busted!");

      wins++;

      player.winsBet(player.getBet());

      scoreGame();

    } else if (dealer.getHand().getValueOfHand() > player.getHand().getValueOfHand()) {
      System.out.println(player.getName() + " Lost!");

      losses++;

      player.losesBet(player.getBet());

      scoreGame();

    } else if (player.getHand().getValueOfHand() > dealer.getHand().getValueOfHand()) {
      System.out.println(player.getName() + " Wins!");

      wins++;

      player.winsBet(player.getBet());

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
    int numberOfDecks = 0;

    System.out.println("""
      The Deck is Empty!
        Starting a new Game...
          How many decks would you like to use in this game?
              Enter Number using Keyboard.
      """);
    while (validEntry) {
      try {

        numberOfDecks = scanner.nextInt();
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

    System.out.println("New Game: Number of Decks =  " + numberOfDecks);

    //Create New Deck
    Game game = new Game(numberOfDecks, player.getName(), player.getWallet());

    game.startNewGame();

  }

  public void checkIfPlayerBusted() {

    if (player.getHand().getValueOfHand() > 21) {

      System.out.println(player.getName() + " Busted! " + "[" + player.getHand().getValueOfHand() + "]");

      losses++;

      player.losesBet(player.getBet());

      scoreGame();

      discardAllHands();

      startNewGame();
    }
  }

  public void scoreGame() {
    System.out.println("Game Score: \n" +
      "Player Loses - [ " + losses + " ]\n" +
      "Player Wins - [ " + wins + " ]\n" +
      "Draws - [ " + draws + " ]\n" +
      "Wallet: $" + player.getWallet() + "\n"
    );
  }

  public void welcomeScreen() {
    System.out.println("-".repeat(30));

    System.out.println("""
    -----------------------------------------------
  |             WELCOME TO BLACKJACK               |
  |                                                |
  |         [1] - HIT - Request additional Card    |
  |         [2] - STAND - No More Cards            |
    ________________________________________________
        """);
    System.out.println("Number of Decks in Game: " + numberOfDecks);

    System.out.println("-".repeat(30));
  }

  public void discardAllHands() {

    player.getHand().discardHand();
    System.out.println(player.getName() + "'s hand discarded.");

    dealer.getHand().discardHand();
    System.out.println("Dealer's hand discarded.");

  }

}

