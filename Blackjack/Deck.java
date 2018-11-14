public interface Deck {
    //Generate new Deck
    public void reset();
    
    //Shuffle deck
    public void shuffle();
    
    //Take one card from the deck
    public Card draw();
    
    //Write out how many cards left as String
    public String toString();
    
}