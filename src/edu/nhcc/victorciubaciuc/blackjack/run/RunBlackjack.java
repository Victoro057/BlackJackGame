package edu.nhcc.victorciubaciuc.blackjack.run;

import edu.nhcc.victorciubaciuc.blackjack.deck.Deck;
import edu.nhcc.victorciubaciuc.blackjack.deck.Player;
import edu.nhcc.victorciubaciuc.blackjack.game.Game;
import java.util.ArrayList;

public class RunBlackjack {
    public static void main(String[] args) {
        Player p = new Player("Charlie", 100);  //Create new player
        Deck d = new Deck();    //create new deck
        ArrayList<Player> players = new ArrayList<>();  //Create new list of players
        players.add(p); //add player to player ArrayList
        Game g = new Game(players,d);   //Create new game


        g.runGame();




    }
}
