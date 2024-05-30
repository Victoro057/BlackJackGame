package edu.nhcc.victorciubaciuc.blackjack.deck;

public enum Rank {
    ACE("ace", 11), TWO("two" ,2), THREE("three", 3), FOUR("four", 4),
    FIVE("five", 5), SIX("six", 6), SEVEN("seven", 7), EIGHT("eight", 8),
    NINE("nine", 9), TEN("ten", 10), JACK("jack", 10), QUEEN("queen", 10),
    KING("king", 10);

    Rank(String name, int value){
        NAME = name;
        VALUE = value;
    }

    private final String NAME;
    private final int VALUE;

    public String getNAME(){
        return NAME;
    }
    public int getVALUE(){
        return VALUE;
    }


}
