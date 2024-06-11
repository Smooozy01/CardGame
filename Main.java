import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {

        ArrayList<Card> Deck = new ArrayList<>();
        CardDeck.CreateDeck(Deck);


        ArrayList<Card> player1 = new ArrayList<>();
        CardDeck.SetRandomDeck6(player1, Deck);

        ArrayList<Card> player2 = new ArrayList<>();
        CardDeck.SetRandomDeck6(player2, Deck);


        LinkedHashMap<Card, Card> desk = new LinkedHashMap<>();


        PlayGame(Deck, player1, player2, desk);
        

    }

    public static void PlayGame(ArrayList<Card> cards, ArrayList<Card> player1, ArrayList<Card> player2, LinkedHashMap<Card, Card> desk) {

        Collections.shuffle(cards);

        boolean Move1 = true;

        while (!player1.isEmpty() || !player2.isEmpty()) {

            if (Move1) PlayerMove(player1, player2, cards, desk);
            else PlayerMove(player2, player1, cards, desk);

            Move1 = PlayerMove(player1, player2, cards, desk);


        }


    }


    public static boolean PlayerMove(ArrayList<Card> player1,ArrayList<Card> player2, ArrayList<Card> Deck, LinkedHashMap<Card, Card> Desk) {


        CardDeck.PlayFirstCard(player1, Deck, Desk);
        boolean ContinueGame = CardDeck.Respond(player2, Desk);
        CardDeck.Respond(player2, Desk);

        if (!ContinueGame) return true;


        int counter = 0;

        while (ContinueGame || counter > 4) {

            CardDeck.AddCardOnDesk(player1, Deck, Desk);
            if (!CardDeck.AddCardOnDesk(player1, Deck, Desk)) {break;}
            CardDeck.Respond(player2, Desk);

            ContinueGame = CardDeck.Respond(player2, Desk);
            counter++;
        }

        return !ContinueGame;

    }





}






















