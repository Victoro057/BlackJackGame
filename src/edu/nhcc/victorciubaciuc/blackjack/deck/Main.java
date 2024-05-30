package edu.nhcc.victorciubaciuc.blackjack.deck;

public class Main {
    public static void main(String[] args) {
        Suit[] allSuits = Suit.values();
        Rank[] allRanks = Rank.values();
        Card[] allCards = new Card[52]; //52 cards in a deck

        int i = 0;

        for(Suit s: allSuits){
            for(Rank r: allRanks){
                allCards[i] = new Card(s, r);
                i++;
            }
        }

        for(Card c : allCards){
            System.out.println(c);
        }


    }

}
