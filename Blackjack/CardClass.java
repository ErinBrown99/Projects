public class CardClass implements Card {
    private String suit;
    private String value;
    
    //constructor, takes in two strings for value and suit
    public CardClass(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String getValue() {
        return value;
    }
    
    //return score of card
    public int getScore(){
        if (isAce()) {
            return 1;
        }
        if (value.equals("J") || value.equals("Q") ||
            value.equals("K")) {
            return 10;
        }
        return Integer.parseInt(value);
    }
    
    //is Ace?
    public boolean isAce() {
        if (value == null) {
            throw new IllegalStateException("ugh");
        }
        if (value.equals("A")) {
            return true;
        }
        return false;
    }
    
    //checks if two cards has same value and suit
    public boolean equals(Card card){
        if (suit.equals(card.getSuit()) && 
            value.equals(card.getValue())){
            return true;
        }
        return false;
    }
    
    //writes as String
    public String toString(){
        return value + " of " + suit;
    }
    
}