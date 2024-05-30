package edu.nhcc.victorciubaciuc.blackjack.deck;
import edu.nhcc.victorciubaciuc.blackjack.deck.Deck;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private String name;
    private double wallet;
    public int numOfAces = 0;

    public Player (String n, double m){
        name = n;
        wallet = m;
        this.hand = new ArrayList<>();
    }

    public int getValueOfCardsInHandWithoutAceProb(){

        int totalValueInHand = 0;

        for (Card c : hand){
            totalValueInHand = totalValueInHand + c.getRankValue();
            if(c.getRankName().equals("ace")){ //when looking at the values inside a hand, if there is a card that has a string same as "ace" in the rank of the card
                numOfAces++;    //adds to the numOfAces variable to show that there is at least one ace in the player's hand
            }
        }

        return totalValueInHand;
    }
    public int getValueOfCardsInHand(){     //This is the final getValue of cards in the hand of the player method because it accounts the ace exceptions
        int totalValue = 0;
        totalValue = getValueOfCardsInHandWithoutAceProb();
        if(getValueOfCardsInHandWithoutAceProb() > 21 && numOfAces > 0){
            numOfAces--;
            totalValue -= 10;
        }

        return totalValue;
    }
    public void emptyHand(){
        hand.clear();
    }


    public void takeCard(Card c){   //Takes the card as parameter and adds it to the hand array list
        hand.add(c);    //adds the card to the hand
    }

    public void printHand(){
        for (Card c : hand){
            System.out.println("       " + c.toString());
        }
    }
    public void printOneCardInHand(int cardNum){
        System.out.println(hand.get(cardNum));
    }
    public int getCardValue(int cardNumber){    //Gets the card value of the cardNumber mentioned
        int value = 0;
        Card tempCard = hand.get(cardNumber);
        value = value + tempCard.getRankValue();
        return value;
    }
    public void totalHand(){
        int total = 0;
        for (int i = 0; i < hand.size(); i++){

        }
    }

    /*
    * Todo: create a method int totalHand() that will
    *  return the value of the hand. Look up the rules
    *  for evaluating a hand total in Blackjack
    *
    *  make getters and setters
    *  make a method to print hand
    * */

    public String getName() {
        return name;
    }
    public double getWallet(){
        return wallet;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }
}
