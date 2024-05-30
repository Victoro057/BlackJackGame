package edu.nhcc.victorciubaciuc.blackjack.deck;

public enum Suit {
        HEARTS("hearts"), DIAMONDS("diamonds"),
        SPADES("spades"), CLUBS("clubs");

        Suit(String n){
                NAME = n;
        }

        private final String NAME;
        public String getNAME(){
                return NAME;
        }


}
