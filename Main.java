import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {

        // Create a deck of 52 cards
        ArrayList<Card> Deck = new ArrayList<>();
        CardDeck.CreateDeck(Deck);


        // Create 2 players and give them 6 random cards from a deck
        // that we previously created.
        ArrayList<Card> player1 = new ArrayList<>();
        CardDeck.SetRandomDeck6(player1, Deck);

        ArrayList<Card> player2 = new ArrayList<>();
        CardDeck.SetRandomDeck6(player2, Deck);


        // Create a desk where the play is played.
        LinkedHashMap<Card, Card> desk = new LinkedHashMap<>();


        PlayGame(Deck, player1, player2, desk);


    }

    public static void PlayGame(ArrayList<Card> cards, ArrayList<Card> player1, ArrayList<Card> player2, LinkedHashMap<Card, Card> desk) {

        Collections.shuffle(cards);


        while (!player1.isEmpty() || !player2.isEmpty()) {

            CardDeck.PlayFirstCard(player1, cards, desk);
            CardDeck.Respond(player2, desk);

        }


    }

}
