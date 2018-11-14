import java.util.ArrayList;

public class HandClass implements Hand {
    private ArrayList<Card> hand;
    
    //constructor, takes in nothing
    public HandClass(){
        hand = new ArrayList<Card>();
    }
    
    //Add card to your hand
    public void addCard(Card card){
        hand.add(card);
    }
    
    //Get score with aces treated as ones
    public int hardScore(){
        int score = 0;
        for (int i = 0; i < hand.size(); i++){
            score += hand.get(i).getScore();
        }
        return score;
    }
    
    //Get score with first ace treated as eleven
    public int softScore(){
        if (hand.size() == 0) {
            throw new IllegalStateException("empty hand");
        }
        for (int i = 0; i < hand.size(); i++){
            if (hand.get(i).isAce()) {
                return hardScore() + 10;
            }
        }
        return hardScore();
    }
    
    //returns the softscore if valid, hardscore if not
    public int getScore() {
        if (softScore() <= 21) {
            return softScore();
        }
        return hardScore();
    }
    
    //is bust?
    public boolean isBust() {
        if (getScore() > 21) {
            return true;
        }
        return false;
    }
    
    //how many cards in hand?
    public int numCards() {
        return hand.size();
    }
    
    public String toString() {
        String cards = "";
        for (int i = 0; i < hand.size() - 1; i++) {
            cards += hand.get(i).toString()+", ";
        }
        cards += hand.get(hand.size() - 1).toString();
        return cards;
    }
    
    public Card getFirst() {
        if (hand.size() == 0) {
            throw new IllegalStateException("Hand is empty");
        }
        return hand.get(0);
    }
}