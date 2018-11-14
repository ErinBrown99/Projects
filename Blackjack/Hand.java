public interface Hand {
    //Add card to your hand
    public void addCard(Card card);
    
    //Get score with aces treated as ones
    public int hardScore();
    
    //Get score with first ace treated as eleven
    public int softScore();
    
    //Get highest valid score
    public int getScore();
    
    //is bust?
    public boolean isBust();
    
    //how many cards in hand?
    public int numCards();
    
    //Write hand as String
    public String toString();
    
    public Card getFirst();
}