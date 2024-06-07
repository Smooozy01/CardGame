import java.util.LinkedHashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

enum Suit {
    Piki, Bubny, Kresti, Chervi
}

public class Card {


    // Constructor
    private final String value;
    private final Suit suit;

    public Card(String value, Suit suit){
        this.value = value; this.suit = suit;
    }

    // Getters
    public String getCard(){ return (suit + " " + value); }

    public Suit getSuit() { return suit; }

    public String getValue(){ return value; }


}



class CardDeck {

    static Scanner scanner = new Scanner(System.in);


    // A method to create a deck of 52 cards.
    public static void CreateDeck(ArrayList<Card> cards) {

        String[] validValues = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String value : validValues) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(value, suit));
            }
        }

    }


    // A method to put 6 random cards into player's deck.
    public static void SetRandomDeck6(ArrayList<Card> playerCards, ArrayList<Card> deck) {

        for (int i = 0; i < 6; i++) {

            int rnd = new Random().nextInt(deck.size());
            playerCards.add(deck.get(rnd));
            deck.remove(rnd);

        }

    }


    // A method to put a player's chosen card onto a deck.
    public static void PlayFirstCard(ArrayList<Card> playerCards, LinkedHashMap<Card, Card> desk) {

        for (int i = 0; i < playerCards.size(); i++) System.out.println((i + 1) + ": " + playerCards.get(i).getCard());
        System.out.println("Pick a card: ");

        int cardNum;
        do {
            cardNum = scanner.nextInt();

        } while (cardNum < 1 || cardNum > playerCards.size());


        desk.put(playerCards.get(cardNum - 1), null);
        playerCards.remove(cardNum - 1);

    }

    public static void PlaySecondCard(ArrayList<Card> playerCards, LinkedHashMap<Card, Card> desk) {

        for (int i = 0; i < playerCards.size(); i++) System.out.println((i + 1) + ": " + playerCards.get(i).getCard());
        System.out.println("Respond to a card " + desk.lastEntry().getKey() + ": ");

        int cardNum;
//        do {
//            cardNum = scanner.nextInt();
//
//        } while (cardNum < 1 || cardNum > playerCards.size());






    }


}






