import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {

        // Create a deck of 52 cards
        ArrayList<Card> cards = new ArrayList<>();
        CardDeck.CreateDeck(cards);


        // Create 2 players and give them 6 random cards from a deck
        // that we previously created.
        ArrayList<Card> player1 = new ArrayList<>();
        CardDeck.SetRandomDeck6(player1, cards);
        ArrayList<Card> player2 = new ArrayList<>();
        CardDeck.SetRandomDeck6(player2, cards);


        // Create a desk where the play is played.
        // Key-Value function is essential in a card game.
        LinkedHashMap<Card, Card> desk = new LinkedHashMap<>();


        System.out.println("Cards of a player 1: ");
        for (int i = 0; i < player1.size(); i++) System.out.println((i + 1) + ": " + player1.get(i).getCard());

        System.out.println();

        System.out.println("Cards of a player 2: ");
        for (int i = 0; i < player2.size(); i++) System.out.println((i + 1) + ": " + player2.get(i).getCard());
        System.out.println();


    }

    public static void PlayGame(ArrayList<Card> cards, ArrayList<Card> player1, ArrayList<Card> player2, ArrayList<Card> desk) {

        Collections.shuffle(cards);
        for (int i = 0; i < cards.size(); i++) System.out.println((i + 1) + ": " + cards.get(i).getCard());


        while (!cards.isEmpty()) {



        }


    }
}

