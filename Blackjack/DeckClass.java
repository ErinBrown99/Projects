import java.util.ArrayList;
import java.util.Collections;

public class DeckClass implements Deck {
    private ArrayList<Card> deck;
    
    //constructor, takes in a Array of Cards
    public DeckClass(){
        deck = new ArrayList<Card>();
        reset();
    }
    
    public void reset(){
        for (int i=2; i<11; i++) {
            deck.add(new CardClass("hearts", i + ""));
            deck.add(new CardClass("clubs", i + ""));
            deck.add(new CardClass("diamonds", i + ""));
            deck.add(new CardClass("spades", i + ""));
        }
        String[] faces = {"J", "Q", "K", "A"};
        for (int i=0; i<faces.length; i++) {
            deck.add(new CardClass("hearts", faces[i]));
            deck.add(new CardClass("clubs", faces[i]));
            deck.add(new CardClass("diamonds", faces[i]));
            deck.add(new CardClass("spades", faces[i]));
            
        }
    }
    
    public void shuffle(){
        Collections.shuffle(deck);
    }
    
    public Card draw() {
        if (deck.size() == 0) {
            throw new IllegalStateException("Deck is empty");
        }
        Card bye = deck.get(0);
        deck.remove(0);
        return bye;
    }
    
    public String toString() {
        return "Deck has " + deck.size() + " cards";
    }
}