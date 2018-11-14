public interface Card {
    
    //return suit of card
    public String getSuit();
    
    //return value of card
    public String getValue();
    
    //return score of card
    public int getScore();
    
    //is Ace?
    public boolean isAce();
    
    //checks if two cards has same value and suit
    public boolean equals(Card card);
    
    //writes as String
    public String toString();
}