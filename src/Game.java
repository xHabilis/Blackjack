public class Game {
  private Player player;
  private Deck deck;
  private Dealer dealer;
  private int numberOfDecks;
  private int draws, playerwins, dealerWins;

  public Game(int numberOfDecks) {

    //Number of decks in game
    this.numberOfDecks = numberOfDecks;

    //Create and shuffle the deck
    this.deck = new Deck(1);
    this.deck.shuffleCardDeck();

    //Creates all users in the game
    this.player = new Player();
    this.dealer = new Dealer();

    //1. Starts a round of blackjack
    startNewGame();

    //2. Game Sequence: Check for player/dealer blackjack. if false: Player makes a move first.
    openingHandCheck();

    //3. If neither has blackjack: Player decides hitOrStand
    player.hitOrStand();


  }
  public void startNewGame() {

    //Display Game Rules
    System.out.println("Welcome to Blackjack \n");

    //Dealer gets cards
    dealer.getHand().drawCardFromDeck(deck);
    dealer.getHand().drawCardFromDeck(deck);


    //Player gets cards
    player.getHand().drawCardFromDeck(deck);
    player.getHand().drawCardFromDeck(deck);

    //Show cards to table
    dealer.showOpeningHand();
    player.showHand();

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

        dealerWins++;
        scoreGame();

      }
      //Discard all hands before starting new game
      dealer.getHand().discardHand();
      player.getHand().discardHand();
      startNewGame();

    }

    if (player.hasBlackjack()) {
      System.out.println("Player Has BlackJack: " + player.getName() + " WINS!");

      playerwins++;

      //If Player Wins: All users discard hand before starting new game
      player.getHand().discardHand();
      dealer.getHand().discardHand();

      startNewGame();

    }

  }

  public void scoreGame() {
    System.out.println("Game Score: \n" +
      "Dealer Wins - [ " + dealerWins + " ]\n" +
      "Player Wins - [ " + playerwins + " ]\n" +
      "Draws - [ " + draws + " ]\n"
    );
  }

  public int getDraws() {
    return draws;
  }

  public int getPlayerwins() {
    return playerwins;
  }

  public int getDealerWins() {
    return dealerWins;
  }


}

