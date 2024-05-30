package edu.nhcc.victorciubaciuc.blackjack.deck;

public class Card {
    private Suit suit;
    private Rank rank;

    public Card(Suit s, Rank r){
        suit = s;
        rank = r;
    }

    public int getRankValue(){
        return rank.getVALUE();
    }
    public String getRankName(){
        return rank.getNAME();
    }

    public String getSuitName(){
        return suit.getNAME();
    }

    @Override
    public String toString(){
        return rank + " of " + suit;
    }
}
