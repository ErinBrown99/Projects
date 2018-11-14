import java.util.Scanner;

public class Blackjack {
    static Scanner sc;
    
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        playRound();
        while(getYesOrNo("Play again?")) {
            playRound();
        }
    }
    
    public static boolean playRound(){
        //create shuffled deck
        Deck deck = new DeckClass();
        deck.shuffle();
        //set up hands
        Hand playerHand = new HandClass();
        playerHand.addCard(deck.draw());
        playerHand.addCard(deck.draw());
        Hand dealerHand = new HandClass();
        dealerHand.addCard(deck.draw());
        dealerHand.addCard(deck.draw());
        //start playing
        if (playerHand.getScore() == 21) {
            System.out.println("Your hand is: " + 
                               playerHand.toString());
            System.out.println("Blackjack! You win!");
            return true;
        }
        else if (dealerHand.getScore() == 21) {
            System.out.println("Dealer has Blackjack! You lose.");
            return false;
        }
        else if (dealerHand.isBust()) {
            System.out.println("Dealer's Hand is: " +
                               dealerHand.toString());
            System.out.println("Dealer busted! You win!");
            return true;
        }
        System.out.println("Your hand is: " + 
                           playerHand.toString());
        System.out.println("The dealer is showing: " + 
                           dealerHand.getFirst().toString());
        //to hit or not to hit
        while(getYesOrNo("Hit?")) {
            playerHand.addCard(deck.draw());
            System.out.println("Your hand is: " + playerHand.toString());
            if (playerHand.isBust()) {
                //System.out.println(playerHand.toString());
                System.out.println("You busted!");
                return false;
            }
        }
        //no more hits for player, dealer's turn
        System.out.println(dealerHand.toString());
        while (dealerHit(dealerHand)) {
            dealerHand.addCard(deck.draw());
            System.out.println("Dealer hits");
            System.out.println(dealerHand.toString());
        }
        if (dealerHand.isBust()) {
            System.out.println("Dealer busts! You win!");
            return true;
        }
        if (dealerHand.getScore() > playerHand.getScore()) {
            System.out.println("Dealer's "+dealerHand.getScore()+
        " beats your "+playerHand.getScore()+". You lose.");
            return false;
        }
        if (dealerHand.getScore() == playerHand.getScore()) {
            System.out.println("Both hands equal " + 
                               dealerHand.getScore() + 
                               ". Tie.");
            return false;
        }
        System.out.println("Your "+playerHand.getScore()+
        " beats Dealer's "+dealerHand.getScore()+". You win!");
        return true;
    }
    
    public static boolean dealerHit (Hand dealerHand) {
        if (dealerHand.softScore() <= 17) {
            return true;
        }
        if (dealerHand.getScore() <= 16) {
            return true;
        }
        return false;
    }
    
    public static boolean getYesOrNo(String q){
        while (true) {
            System.out.print(q);
            String input = sc.next();
            if (input.toLowerCase().startsWith("y")){
                return true;
            }
            else if (input.toLowerCase().startsWith("n")){
                return false;
            }
            else {
                System.out.println("Invalid entry. Please enter yes or no");
            }
        }
    }
}
