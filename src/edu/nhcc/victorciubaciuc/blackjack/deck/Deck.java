package edu.nhcc.victorciubaciuc.blackjack.deck;

import java.util.ArrayList;

public class Deck {     //Class that consists of a deck of cards
    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<>();
        loadDeck();
        shuffle();
    }

    public void resetDeck(){  //clears the deck and creates a new shuffled one
        deck.clear();
        loadDeck();
        shuffle();
    }
    public void printDeck(){    // This method prints the deck elements
        for (Card c : deck){
            System.out.println(c.toString());
        }
    }
    public void printCardInDeck(int c){
        if(c < 52) {
            System.out.println("Card under index " + c +" is " +deck.get(c));
        }
        else{
            System.out.println("Need to enter a number between 0 and 51");
        }
    }

    public Card dealCard(int cardNumber){
        Card temp = deck.get(cardNumber);
        deck.remove(cardNumber); //Picks the card from the beginning(top) of the deck
        return temp;    //returns a card now, which was dealt just a line above
    }

    public void loadDeck(){  //fills the array list with all possible card
        for (Rank r : Rank.values()){
            for(Suit s : Suit.values()){
                int i = (int)(Math.random() * deck.size());     // selects a random spot in the deck
                deck.add(i, new Card(s, r));    // inputs the card in the deck
            }
        }
    }

    public void shuffle(){


         //for each item in the deck
         //generate a random in current item index + 1 - size() - 1
         //swap current item with item at random index generated

        for (int i = 0; i < deck.size()-1; i++){
            //full traversal to the second to last index

            //generate random from i+1 - last element (51)
            //swap index i with random index
            //try doing swap algorithm all in one line
        }


        for (int i = 0; i < deck.size(); i++){
            int min = i + 1;
            int max = deck.size() ;
            //System.out.println("The max is: " + max);
            int r = min + (int)(Math.random() * (max - min) - 1);   // generates a random int within the range
            //System.out.println(r);
            //swapping
            Card temp = deck.get(i);
            deck.set(i, deck.get(r));
            deck.set(r, temp);
        }
    }

    public ArrayList<Card> getDeck(){   // returns the arrayList of cards in the deck
        return deck;
    }
}
