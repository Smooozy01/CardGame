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


    // Drawing a card from a deck of cards.
    public static void DrawCard(ArrayList<Card> PlayerCards, ArrayList<Card> deck) {

        if (deck.isEmpty()){return;}

        int rnd = new Random().nextInt(deck.size());
        PlayerCards.add(deck.get(rnd));
        deck.remove(rnd);

    }


    // Method to make a responder take a card that he's been given to
    public static void TakeGivenCard(ArrayList<Card> PlayerCards, LinkedHashMap<Card, Card> desk) {

        PlayerCards.add(desk.lastEntry().getKey());
        desk.remove(desk.lastEntry().getKey());

    }


    // A method to put a player's chosen card onto a desk.
    public static void PlayFirstCard(ArrayList<Card> playerCards, ArrayList<Card> deck, LinkedHashMap<Card, Card> desk) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < playerCards.size(); i++) System.out.println((i + 1) + ": " + playerCards.get(i).getCard());
        System.out.println("Pick a card: ");

        int cardNum = scanner.nextInt();
        while (cardNum < 1 || cardNum > playerCards.size()){

            System.out.println("Please enter a number between 1 and " + playerCards.size());
            cardNum = scanner.nextInt();


        }


        desk.put(playerCards.get(cardNum - 1), null);
        playerCards.remove(cardNum - 1);

        DrawCard(playerCards, deck);


    }


    // A method to respond on a given card.
    public static void Respond(ArrayList<Card> PlayerCards, LinkedHashMap<Card, Card> desk) {


        if (CheckDeckForResponse(PlayerCards, desk.lastEntry().getKey()) == false){

            System.out.println("No Response!!!");
            TakeGivenCard(PlayerCards, desk);
            return;

        }


        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < PlayerCards.size(); i++) System.out.println((i + 1) + ": " + PlayerCards.get(i).getCard());

        System.out.println("Respond to a card " + desk.lastEntry().getKey().getCard() + ": ");
        System.out.println("Or write 0 if you want to draw a card.");


        int cardNum = scanner.nextInt();

        if (cardNum == 0){
            TakeGivenCard(PlayerCards, desk);
            return;
        }

        while (!CheckCardForResponse(PlayerCards.get(cardNum - 1), desk.lastEntry().getKey())){

            if (cardNum == 0){
                TakeGivenCard(PlayerCards, desk);
                return;
            }

            System.out.println("Write a valid card or write 0 if you want to draw a card.");
            cardNum = scanner.nextInt();


        }

        desk.put(desk.lastEntry().getKey(), PlayerCards.get(cardNum - 1));




    }


}






