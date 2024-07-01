import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) {

        ArrayList<Card> Deck = new ArrayList<>();
        CardDeck.CreateDeck36(Deck);
        

        ArrayList<Card> player1 = new ArrayList<>();
        CardDeck.SetPlayerCards(player1, Deck);

        ArrayList<Card> player2 = new ArrayList<>();
        CardDeck.SetPlayerCards(player2, Deck);


        LinkedHashMap<Card, Card> desk = new LinkedHashMap<>();


        PlayGame(Deck, player1, player2, desk);
        

    }

    public static void PlayGame(ArrayList<Card> cards, ArrayList<Card> player1, ArrayList<Card> player2, LinkedHashMap<Card, Card> desk) {

        Collections.shuffle(cards);

        boolean Move1 = true;

        while (!player1.isEmpty() || !player2.isEmpty()) {

            if (Move1) Move1 = MoveLap(player1, player2, cards, desk);
            else Move1 = MoveLap(player2, player1, cards, desk);

        }


    }


    public static boolean MoveLap(ArrayList<Card> player1, ArrayList<Card> player2, ArrayList<Card> Deck, LinkedHashMap<Card, Card> Desk) {


        CardDeck.PlayFirstCard(player1, Deck, Desk);
        boolean ContinueGame = CardDeck.CheckDeckForResponse(player2, Desk.lastEntry().getKey()); // Can player2 respond?
        
        if (!CardDeck.Respond(player2, Desk)) { // If can't, he takes cards on desk, and it's player1's move again
            return true;
        }

        if (CardDeck.CheckForAddingCardOnDesk(player1, Desk)) {
            
            int counter = 0;

            while (ContinueGame || counter > 4) {

                
                if (!CardDeck.AddCardOnDesk(player1, Deck, Desk)) { // Бито
                    return false;
                }

                
                ContinueGame = CardDeck.CheckDeckForResponse(player2, Desk.lastEntry().getKey());
                if (!ContinueGame) {
                    CardDeck.TakeGivenCard(player2, Desk);
                    return true;
                }
            
                
                if (!CardDeck.Respond(player2, Desk)) return true;
                else {
                    counter++;
                }
                
                
            }
            
        }
        
        
        return !ContinueGame;
        
    }
    

}






















