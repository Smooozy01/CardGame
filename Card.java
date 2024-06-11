import java.util.LinkedHashMap;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;


enum Suit {
    Piki, Bubny, Kresti, Chervi
}

public class Card {


    // Constructor
    private final String value;
    private final Suit suit;
    boolean trump;

    public Card(String value, Suit suit){
        this.value = value; this.suit = suit; this.trump = false;
    }


    // Getters
    public String getCard(){
        String isTrump = trump ? " + Trump" : "";
        return suit + " " + value + isTrump;
    }


    public Suit getSuit() { return suit; }


    public String getValueString(){ return value; }


    public int getValueInt(){

        return switch (value) {
            case "J" -> 11;
            case "Q" -> 12;
            case "K" -> 13;
            case "A" -> 14;
            default -> Integer.parseInt(value);
        };

    }


}



class CardDeck {


    // A method to create a deck of 52 cards.
    public static void CreateDeck(ArrayList<Card> cards) {

        String[] validValues = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (Suit suit : Suit.values()) {
            for (String value : validValues) {
                cards.add(new Card(value, suit));
            }
        }

        int rnd = new Random().nextInt(4);
        for (int i = rnd * 13; i < rnd * 13 + 13; i++){

            cards.get(i).trump = true;

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


    // A method to check if a responder has a card to respond on a given card.
    public static boolean CheckDeckForResponse(ArrayList<Card> cards, Card card) {


        for (Card Response : cards) {

            if (CheckCardForResponse(Response, card)) { return true; }

        }

        return false;

    }


    // A method to check if a card is able to respond on a given card.
    public static boolean CheckCardForResponse(Card CardResponding, Card CardLying){

        if (CardLying.trump){

            if (CardResponding.trump && CardResponding.getValueInt() > CardLying.getValueInt()){

                return true;

            }

        }

        else {

            if (CardResponding.trump) {

                return true;

            }

            else {

                if (CardLying.getSuit() == CardResponding.getSuit() && CardResponding.getValueInt() > CardLying.getValueInt()) {

                    return true;

                }}}


        return false;

    }


    // Check if player have cards to throw them as an addition on table.
    public static boolean CheckForAddingCardOnDesk(ArrayList<Card> PlayerCards, LinkedHashMap<Card, Card> Desk, HashSet<String> Values) {

        HashSet<String> ValuesOnTable = new HashSet<>();

        for (Card card : Desk.keySet()){

            ValuesOnTable.add(card.getValueString());
            if (Desk.get(card) != null){ ValuesOnTable.add(Desk.get(card).getValueString()); }

        }

        Values.addAll(ValuesOnTable);

        for (Card card : PlayerCards){

            if (ValuesOnTable.contains(card.getValueString())){ return true; }

        }


        return false;

    }


    // Drawing a card from a deck of cards.
    public static void DrawCard(ArrayList<Card> PlayerCards, ArrayList<Card> deck) {

        if (deck.isEmpty()){return;}

        int rnd = new Random().nextInt(deck.size());
        PlayerCards.add(deck.get(rnd));
        deck.remove(rnd);

    }


    // Method to make a responder take a card that he's been given to
    public static void TakeGivenCard(ArrayList<Card> PlayerCards, LinkedHashMap<Card, Card> desk) {

        PlayerCards.addAll(desk.values());
        PlayerCards.addAll(desk.keySet());
        desk.clear();

    }


    // A method to put a player's chosen card onto a desk.
    public static void PlayFirstCard(ArrayList<Card> PlayerCards, ArrayList<Card> Deck, LinkedHashMap<Card, Card> Desk) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < PlayerCards.size(); i++) System.out.println((i + 1) + ": " + PlayerCards.get(i).getCard());
        System.out.println("Make a First Move: ");

        int CardNum = scanner.nextInt();
        while (CardNum < 1 || CardNum > PlayerCards.size()){

            System.out.println("Please enter a number between 1 and " + PlayerCards.size());
            CardNum = scanner.nextInt();


        }


        Desk.put(PlayerCards.get(CardNum - 1), null);
        PlayerCards.remove(CardNum - 1);

        DrawCard(PlayerCards, Deck);


    }


    // A method to respond on a given card.
    public static boolean Respond(ArrayList<Card> PlayerCards, LinkedHashMap<Card, Card> Desk) {

        if (Desk.lastEntry().getValue() != null){ return false; }

        if (!CheckDeckForResponse(PlayerCards, Desk.lastEntry().getKey())){

            System.out.println("No Response!!!");
            TakeGivenCard(PlayerCards, Desk);
            return false;

        }


        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < PlayerCards.size(); i++) System.out.println((i + 1) + ": " + PlayerCards.get(i).getCard());

        System.out.println("Respond to a card " + Desk.lastEntry().getKey().getCard() + ": ");
        System.out.println("Or write 0 if you want to take a card.");


        int cardNum = scanner.nextInt();

        if (cardNum == 0){
            TakeGivenCard(PlayerCards, Desk);
            return false;
        }

        while (!CheckCardForResponse(PlayerCards.get(cardNum - 1), Desk.lastEntry().getKey())){

            if (cardNum == 0){
                TakeGivenCard(PlayerCards, Desk);
                return false;
            }

            System.out.println("Write a valid card or write 0 if you want to take a card.");
            cardNum = scanner.nextInt();


        }

        Desk.put(Desk.lastEntry().getKey(), PlayerCards.get(cardNum - 1));


        return true;

    }


    // Put an additional card on a desk
    public static boolean AddCardOnDesk(ArrayList<Card> PlayerCards, ArrayList<Card> Deck, LinkedHashMap<Card, Card> Desk) {

        HashSet<String> ValuesOnTable = new HashSet<>();

        if (CheckForAddingCardOnDesk(PlayerCards, Desk, ValuesOnTable)){


            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < PlayerCards.size(); i++) System.out.println((i + 1) + ": " + PlayerCards.get(i).getCard());
            System.out.println("Pick a card or write 0 if you want to fall: " + "\n");
            for (String value : ValuesOnTable) System.out.print(value + " ");

            int CardNum = scanner.nextInt();
            if (CardNum == 0){ return false; }


            while (CardNum < 0 || CardNum > PlayerCards.size()){

                CardNum = scanner.nextInt();

                if (CardNum == 0){ return false; }

            }

            Desk.put(PlayerCards.get(CardNum - 1), null);
            PlayerCards.remove(CardNum - 1);

            DrawCard(PlayerCards, Deck);
            return true;

        }


        else{ System.out.println("No card to add"); }

        return false;


    }


}

















