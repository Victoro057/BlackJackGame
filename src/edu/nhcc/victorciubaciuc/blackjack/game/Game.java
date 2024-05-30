package edu.nhcc.victorciubaciuc.blackjack.game;
import edu.nhcc.victorciubaciuc.blackjack.deck.Deck;
import edu.nhcc.victorciubaciuc.blackjack.deck.Player;
import edu.nhcc.victorciubaciuc.blackjack.deck.Card;
import java.util.ArrayList;
import java.util.Scanner;


    //Money wasn't used in this version of blackjack because I am Christian and don't support gambling
        //Enjoy!


public class Game {
    // deal cards, evaluate hands, compare them, offer to hit or stand, determine the winner

    //      Notes: Once the player made his choice, the dealer reveals the second card. If this card is 16 or under, they have to take another card. If its 17 or higher, they have to stay
    //If dealers busts everyone gets 2x of their bets.
    // Figure out how to have the ace be 1 instead of 11. Question, would I need to be using a setter setValue when doing this?

    // Reveal the 2 cards to the person and 1 of the dealers cards.
    //After this, the player has a choice to hit(Get another card) or stand(keep what they have.)

    //Create methods for actions like Hitting, Standing, Splitting, and Doubling Down
    //Hitting - Request an additional card from the deck
    //Standing - End your turn and keep the cards dealt--



    private int in = 0;
    private ArrayList<Player> table;
    private Player house;
    private Deck deck;
    private int score;
    public Scanner runGameInput = new Scanner(System.in);
    int runGameIntInput = -1;   //set to -1 because later on it will need to be changed to either 1 or 0
    public Game(ArrayList<Player> t, Deck d){  // Constructor
                                // t for table

        house = new Player("Dealer", 0);
        table = t;
        deck = d;
    }

    public void runGame(){
            startGameInput();   //Method on ln 81

            if(runGameIntInput == 1) {
                //Game components begin
                while(runGameIntInput != 0) {
                    resetScoresAndCards();  //Method on ln 65
                    dealCardsToEveryone();  //Deals cards to the players and the dealer     Method on ln 232
                    revealPlayerCards();    //Method on ln 73
                    revealDealer2ndCard();  //Method on ln 80
                    checkForBlackjackInPlayer();    //check for blackjack to catch the situation when a personal got a natural blackjack    Method on ln 192
                    hitOrStand();   //have the person choose to stand or to hit, checks for blackjack at the end    Method on ln 153
                    revealDealer1stCard();  //Method on ln 87
                    checkForBlackjackInPlayer();    //check if player got blackJack     Method on ln 192
                    checkAndDealCardToDealerIfNeeded(); //Check and deal cards to dealer if needed      Method on ln 118
                    checkAndPrintResultSituations();    //check which of the result situation the game falls under and prints the result        Method on ln 129
                    //Game components end

                    playAgainInput();   //Asks the user if they want to play again      Method on ln 106
                    if(runGameIntInput == 0){
                        System.out.println("\nBye! Thanks for playing!");
                    }
                }
            }
            else{
                System.out.println("You chose not to play blackjack. Good choice! Better not start");
            }

    }

    private void resetScoresAndCards(){     //resets the scores of the game. Will be used at the beginning of every game
        in = 0;
        table.get(0).numOfAces = 0; //reset the ace number so there would be no bugs in future
        runGameIntInput = -1;
        deck.resetDeck();   //Resets the deck
        table.get(0).emptyHand();   //Removes the previous cards from the hand of the one player that's playing
        house.emptyHand();  //removes the previous cards from the hand of the dealer

    }
    private void revealPlayerCards(){    //This reveals the cards of the players and shows the value of the cards in hand
        for (Player p : table) {  //prints the hands of the players
            System.out.print(p.getName() + "'s cards are: \n");
            p.printHand();
            System.out.println("The value of the cards is: " + p.getValueOfCardsInHand());
        }
    }
    private void revealDealer2ndCard(){  //This method reveals the dealer's second card and shows its value.
            //This is done in the beginning after all the players received their cards. The dealer only shows his 2nd card in the beginning

        System.out.print("\nThe dealer's second card is: ");
        house.printOneCardInHand(1); //This prints the 2nd card of the dealer, the second one is hidden for now
        System.out.println("The card's value is: " + house.getCardValue(1) + "\n");    //Gets the value of the second card
    }
    private void revealDealer1stCard(){ //This method reveals the dealer's 1st card.
                //This card was hidden in the beginning of the game, but after the player makes a move the dealer should reveal their second card

        if (checkForBustPlayer() != true) {    //if the player doesn't have a bust, reveal dealer's 2nd card.
            System.out.print("\nThe dealer's first card is: ");
            house.printOneCardInHand(0); //Reveals the dealer's 1st card.
            System.out.println("The card's value is: " + house.getCardValue(0));    //Gets the value of the first card
            System.out.println("Dealer's cards value is: " + house.getValueOfCardsInHand() + "\n");
        }
    }
    public void startGameInput(){   //This method will be used in the beginning of the game to ask the user if they are ready to start the game
        System.out.println("READY TO START THE GAME? 1 - Yes 0 - No");
        runGameIntInput = runGameInput.nextInt();

        while(runGameIntInput != 1 && runGameIntInput != 0){    //input validation for options 1 or 0
            System.out.println("Please enter either 1 or 0");
            runGameIntInput = runGameInput.nextInt();
        }
    }
    public void playAgainInput(){   // This method will be used at the end of the game, and will ask the user if they want to play again?
        System.out.println("\nPLAY AGAIN? 1 = Yes 0 = No\n");
        runGameIntInput = runGameInput.nextInt();

        while(runGameIntInput != 1 && runGameIntInput != 0){    //input validation for play again options
            System.out.println("Please enter either 1 or 0");
            runGameIntInput = runGameInput.nextInt();
            if(runGameIntInput == 1){
                break;
            }
        }
    }
    public void checkAndDealCardToDealerIfNeeded(){ //this method checks if the dealer has to draw a card or not and decides to give dealer a card or not
        while (house.getValueOfCardsInHand() <= 16) { //if the value of dealer's cards is lower than or equal 16
            if (checkForBustPlayer() == false) {
                System.out.println("The dealer has to draw a card...");
                deal1Card(house, deck);
                house.printHand();
                System.out.println("Dealer's cards value is: " + house.getValueOfCardsInHand() + "\n");
            }
            else break;
        }
    }
    public void checkAndPrintResultSituations(){
        if(checkForBustDealer()){   //If the dealer got a bust
            System.out.println("The dealer got a bust. The player wins!");
        }
        else if(checkForBustPlayer()){   // if the player gets a bust
            System.out.println("The player got a bust. Dealer wins!");
        }
        else if(checkForBlackjackInPlayer() && !checkForBlackjackDealer()){  // if the player gets a blackjack and the dealer doesn't
            System.out.println("The player got blackjack. The player wins!");
        }
        else if(checkForBlackjackDealer() && !checkForBlackjackInPlayer()){  // if the dealer gets a blackjack and the player doesn't
            System.out.println("The dealer got blackjack. The dealer wins!");
        }
        else if(table.get(0).getValueOfCardsInHand() > house.getValueOfCardsInHand() && table.get(0).getValueOfCardsInHand() < 21 ){   //If the player got more than the dealer
            System.out.println("The player got closer to 21. Player wins!");
        }
        else if(table.get(0).getValueOfCardsInHand() < house.getValueOfCardsInHand() && house.getValueOfCardsInHand() < 21 ){   //if the dealer got more than the player
            System.out.println("The dealer got closer to 21. Dealer wins!");
        }
        else if(table.get(0).getValueOfCardsInHand() == house.getValueOfCardsInHand()){   //if the dealer got more than the player
            System.out.println("Its a tie. No winner!");
        }
        else{
            System.out.println("This situation wasn't accounted for");
        }
    }

    public void hitOrStand(){   // This method asks that user if they want to hit or stand (make a move)
         while (in !=2){    // do this until the player chooses 2
             if (checkForBlackjackInPlayer()){  //checks if the player has blackjack before giving them a choice, if so, kick out of the loop
                 System.out.println("Blackjack!");
                 //System.out.println("YOU WIN!");
                 break;
             }
             else if(checkForBustPlayer()){   //check if the player got a bust and if so kick out of the loop
                 System.out.println("Its a bust!");
                 //System.out.println("YOU LOST!");
                 break;
             }

             printAndGetUserInputForHitOrStand();
        }
    }

    public void printAndGetUserInputForHitOrStand(){
            if(table.get(0).getValueOfCardsInHand() < 21) {
            System.out.println("You have 2 choices: Hit(1) or Stand(2)");
            System.out.println("Please select your choice option by inputting the action number.");
            Scanner input = new Scanner(System.in);
            in = input.nextInt();
            while (in < 1 && in > 2) {
                System.out.println("Please enter a valid input!");
                in = input.nextInt();
            }
            if (in == 1) {   // If the user enters 1, then they are dealt a card
                hitting(table.get(0));  // deals a card to the 0th element of the ArrayList. For now the game only has 1 player and a dealer
                System.out.println("You chose to hit, a card was dealt to you. Your cards now are: ");
                table.get(0).printHand();   //Prints the cards in the hand of the 0th element(person)
                System.out.println(table.get(0).getValueOfCardsInHand());   //prints the total value in hand after hitting
            } else {
                System.out.println("You chose to stand, no cards added");
            }
        }
    }


    public boolean checkForBlackjackInPlayer(){    //This method checks if the player either got blackjack or went over. if value is less that 21, it does't do anything
        boolean blackjack = false;

        if (table.get(0).getValueOfCardsInHand() == 21){   //If the person has a value of 21, they win
            blackjack = true;
        }
        return blackjack;
    }
    public boolean checkForBlackjackDealer(){   //Checks if the dealer got a blackjack
        boolean blackjack = false;

        if(house.getValueOfCardsInHand() == 21){
            blackjack = true;
        }

        return blackjack;
    }
    public boolean checkForBustDealer(){
        boolean bust = false;
        if(house.getValueOfCardsInHand() > 21){
            bust = true;
        }

        return bust;
    }
    public boolean checkForBustPlayer(){
        boolean bust = false;

        if (table.get(0).getValueOfCardsInHand() > 21){    //if a person get a value over 21, they lose
            bust = true;
        }

        return bust;
    }

    public void hitting(Player p){  //if a player chose to hit, they receive another card
        deal1Card(p, deck);
    }


    public void dealCardsToEveryone(){  //This includes the dealer getting cards too

        for(int i = 0; i < 2; i++) {
            for (Player p : table) {     //get each player from the table
                deal1Card(house, deck); //Deals card to the dealer
                deal1Card(p, deck); //gives the players each 1 card
                //After everyone gets one, card the cycle goes again
            }
        }



    }


    //Create a method that deals 2 cards to the people
    public void deal2CardsToEachPerson(Player p, Deck d){
        for (Player player : table){
            deal1Card(player, deck);
        }
    }

    public void deal1Card(Player p, Deck d){
        p.takeCard(d.dealCard(0));
    }


}
